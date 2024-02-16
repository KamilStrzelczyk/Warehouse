package com.example.presentation.screen.contractors.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Contractor
import com.example.domain.usecase.AddContractorUseCase
import com.example.domain.usecase.GetAllContractorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContractorsViewModel @Inject constructor(
    private val getAllContractor: GetAllContractorUseCase,
    private val addContractor: AddContractorUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    init {
        updateContractors()
    }

    private fun updateContractors() {
        viewModelScope.launch {
            _state.update {
                _state.value.copy(
                    contractors = getAllContractor(),
                )
            }
        }
    }

    fun addContractorDialogVisible(visible: Boolean) {
        _state.update {
            _state.value.copy(
                dialogVisible = visible,
            )
        }
    }

    fun onAddContractorClicked() {
        val name = _state.value.textFieldValue
        if (name.isNotEmpty() && name.isNotBlank()) {
            val contractor = Contractor(
                signature = "",
                name = name,
            )
            viewModelScope.launch {
                contractor.run {
                    addContractor(contractor)
                }
            }
            updateContractors()
            addContractorDialogVisible(false)
        }
    }

    fun onTextValueChange(string: String) {
        _state.update {
            _state.value.copy(
                textFieldValue = string,
            )
        }
    }

    data class ViewModelState(
        val contractors: List<Contractor> = emptyList(),
        val dialogVisible: Boolean = false,
        val textFieldValue: String = "",
    )
}
