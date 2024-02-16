package com.example.presentation.screen.contractorDetails.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Contractor
import com.example.domain.usecase.DeleteContractorUseCase
import com.example.domain.usecase.GetContractorUseCase
import com.example.domain.usecase.UpdateContractorUseCase
import com.example.presentation.component.fakeContractor
import com.example.presentation.navigation.Destination.ContractorDetails.Companion.CONTRACTOR_DETAILS_ID_ARGUMENT
import com.example.presentation.screen.contractorDetails.presentation.ContractorDetailsViewModel.Event.NavigateBack
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ContractorDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getContractor: GetContractorUseCase,
    private val updateContractor: UpdateContractorUseCase,
    private val deleteContractor: DeleteContractorUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event

    private val contractorId: String = checkNotNull(savedStateHandle[CONTRACTOR_DETAILS_ID_ARGUMENT])

    init {
        updateContractors()
    }

    private fun updateContractors() {
        viewModelScope.launch {
            _state.update {
                _state.value.copy(
                    contractor = getContractor(contractorId.toLong()),
                )
            }
        }
    }

    fun editContractor() {
        viewModelScope.launch {
            val newContractor = Contractor(
                id = _state.value.contractor.id,
                name = _state.value.textFieldValue,
                signature = ""
            )
            updateContractor(newContractor)
            updateStateAfterEditContractor()
        }
    }

    private fun updateStateAfterEditContractor() {
        updateContractors()
        editContractorDialogVisible(false)
        onTextValueChange("")
    }

    fun onDeleteContractorClicked() {
        viewModelScope.launch {
            deleteContractor(_state.value.contractor)
            _event.emit(NavigateBack)
        }
    }

    fun editContractorDialogVisible(visible: Boolean) {
        _state.update {
            _state.value.copy(
                dialogVisible = visible,
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
        data object NavigateBack : Event()
    }

    data class ViewModelState(
        val contractor: Contractor = fakeContractor,
        val dialogVisible: Boolean = false,
        val textFieldValue: String = "",
    )
}
