package com.example.presentation.screen.contractors.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Contractor
import com.example.domain.usecase.AddContractorUseCase
import com.example.domain.usecase.GetAllContractorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event

    init {
        viewModelScope.launch {
            getAllContractor().collect { contractors ->
                _state.update {
                    _state.value.copy(
                        contractors = contractors,
                    )
                }
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
                signature = getRandomString(),
                name = name,
            )
            viewModelScope.launch {
                val contractorId = contractor.run {
                    addContractor(contractor)
                }
                _event.emit(Event.NavigateToContractorDetails(contractorId))
            }
            updateState()
        }
    }

    private fun getRandomString(): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..10)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun updateState() {
        addContractorDialogVisible(false)
        _state.update {
            _state.value.copy(
                textFieldValue = "",
            )
        }
    }

    fun onTextValueChange(string: String) {
        _state.update {
            _state.value.copy(
                textFieldValue = string,
            )
        }
    }

    sealed class Event {
        data class NavigateToContractorDetails(val contractorId: Long) : Event()
    }

    data class ViewModelState(
        val contractors: List<Contractor> = emptyList(),
        val dialogVisible: Boolean = false,
        val textFieldValue: String = "",
    )
}
