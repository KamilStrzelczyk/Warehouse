package com.example.presentation.screen.documents.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Contractor
import com.example.domain.model.Document
import com.example.domain.provider.LocalDateTimeProvider
import com.example.domain.usecase.AddDocumentUseCase
import com.example.domain.usecase.GenerateSignatureUseCase
import com.example.domain.usecase.GetAllContractorUseCase
import com.example.domain.usecase.GetAllDocumentUseCase
import com.example.presentation.component.fakeContractor
import com.example.presentation.screen.documents.presentation.DocumentsViewModel.Event.NavigateDocumentsDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val addDocument: AddDocumentUseCase,
    private val getAllContractor: GetAllContractorUseCase,
    private val getAllDocument: GetAllDocumentUseCase,
    private val generateSignature: GenerateSignatureUseCase,
    private val localDateTime: LocalDateTimeProvider,
) : ViewModel() {

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event

    init {
        updateContractor()
        updateDocuments()
    }

    private fun updateContractor() {
        viewModelScope.launch {
            val contractors = getAllContractor().first()
            _state.update {
                _state.value.copy(
                    contractors = contractors,
                    selectedContractor = contractors.first()
                )
            }
        }
    }

    private fun updateDocuments() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    documents = getAllDocument(),
                )
            }
        }
    }

    fun addDocumentDialogVisible(visible: Boolean) {
        _state.update {
            _state.value.copy(
                dialogVisible = visible,
            )
        }
    }

    fun onAddDocumentClicked() {
        val lastDocumentIndex = _state.value.documents.lastIndex
        val document = Document(
            date = localDateTime.getNow().toString(),
            signature = generateSignature(lastDocumentIndex),
            contractor = _state.value.selectedContractor,
            contractorName = _state.value.selectedContractor.name,
        )
        viewModelScope.launch {
            document.run {
                val documentId = addDocument(document)
                _event.emit(NavigateDocumentsDetails(documentId))
            }
        }
        updateDocuments()
        addDocumentDialogVisible(false)
    }

    fun onSelectedContractor(contractor: Contractor) {
        _state.update {
            _state.value.copy(
                selectedContractor = contractor,
            )
        }
    }

    sealed class Event {
        data class NavigateDocumentsDetails(val documentId: Long) : Event()
    }

    data class ViewModelState(
        val contractors: List<Contractor> = emptyList(),
        val dialogVisible: Boolean = false,
        val documents: List<Document> = emptyList(),
        val selectedContractor: Contractor = fakeContractor,
    )
}
