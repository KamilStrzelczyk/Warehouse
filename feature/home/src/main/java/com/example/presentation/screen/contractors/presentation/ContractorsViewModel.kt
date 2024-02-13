package com.example.presentation.screen.contractors.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Contractor
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
) : ViewModel() {
    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    init {
        viewModelScope.launch {
            _state.update {
                _state.value.copy(
                    contractors = getAllContractor()
                )
            }
        }
    }

    data class ViewModelState(
        val contractors: List<Contractor> = emptyList(),
    )
}
