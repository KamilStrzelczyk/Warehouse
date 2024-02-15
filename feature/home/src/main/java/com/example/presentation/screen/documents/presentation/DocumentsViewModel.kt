package com.example.presentation.screen.documents.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Contractor
import com.example.domain.model.Document
import com.example.domain.provider.LocalDateTimeProvider
import com.example.domain.usecase.AddDocumentUseCase
import com.example.domain.usecase.GetAllContractorUseCase
import com.example.domain.usecase.GetAllDocumentUseCase
import com.example.presentation.component.fakeContractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val getAllContractor: GetAllContractorUseCase,
    private val getAllDocument: GetAllDocumentUseCase,
    private val localDateTime: LocalDateTimeProvider,
    private val addDocument: AddDocumentUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    init {
        updateContractor()
        updateDocuments()
    }

    private fun updateContractor() {
        viewModelScope.launch {
            _state.update {
                _state.value.copy(
                    contractors = getAllContractor(),
                )
            }
        }
    }

    private fun updateDocuments() {
        viewModelScope.launch {
            _state.update {
                _state.value.copy(
                    documents = getAllDocument(),
                    selectedContractor = _state.value.contractors.first()
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
        val document = Document(
            date = localDateTime.getNow().toString(),
            signature = "NewDocument",
            contractor = _state.value.selectedContractor,
            collection = "",
        )
        viewModelScope.launch {
            document.run {
                addDocument(document)
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

    data class ViewModelState(
        val selectedContractor: Contractor = fakeContractor,
        val contractors: List<Contractor> = emptyList(),
        val documents: List<Document> = emptyList(),
        val dialogVisible: Boolean = false,
    )
}
