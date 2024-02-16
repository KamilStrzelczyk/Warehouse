package com.example.presentation.screen.documentdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Goods
import com.example.domain.repository.DocumentRepository
import com.example.domain.usecase.AddGoodsToDocumentUseCase
import com.example.domain.utils.UnitOfMeasure
import com.example.presentation.component.GoodsTextFieldType
import com.example.presentation.component.GoodsTextFieldType.AMOUNT
import com.example.presentation.component.GoodsTextFieldType.NAME
import com.example.presentation.navigation.Destination.DocumentDetails.Companion.DOCUMENT_DETAILS_ID_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addGoodsToDocument: AddGoodsToDocumentUseCase,
    private val documentRepository: DocumentRepository,
) : ViewModel() {

    private val documentId: String = checkNotNull(savedStateHandle[DOCUMENT_DETAILS_ID_ARGUMENT])

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    init {
        viewModelScope.launch {
            documentRepository.getAllAsFlow(documentId.toLong()).collect { goods ->
                _state.update {
                    _state.value.copy(
                        goods = goods
                    )
                }
            }
        }
    }

    fun onTextValueChange(
        string: String,
        goodsTextField: GoodsTextFieldType,
    ) {
        _state.update {
            when (goodsTextField) {
                NAME -> _state.value.copy(textFieldValueForName = string)
                AMOUNT -> _state.value.copy(textFieldValueForAmount = string)
            }
        }
    }

    fun addGoodsDialogVisible(visible: Boolean) {
        _state.update {
            _state.value.copy(
                addGoodsDialogVisible = visible,
            )
        }
    }

    fun onAddGoodsClicked() {
        val name = _state.value.textFieldValueForName
        val amount = _state.value.textFieldValueForAmount
        val unitOfMeasure = _state.value.selectedUnitOfMeasure

        if (name.isNotEmpty() && amount.isNotEmpty()) {
            val goods = Goods(
                amount = amount.toLong(),
                name = name,
                unitOfMeasure = unitOfMeasure,
            )
            viewModelScope.launch {
                addGoodsToDocument(goods, documentId.toLong())
            }
            addGoodsDialogVisible(false)
        }
    }

    fun onSelectedUnitOfMeasure(unitOfMeasure: UnitOfMeasure) {
        _state.update {
            _state.value.copy(
                selectedUnitOfMeasure = unitOfMeasure,
            )
        }
    }

    data class ViewModelState(
        val goods: List<Goods> = emptyList(),
        val documentId: Long = 0L,
        val selectedUnitOfMeasure: UnitOfMeasure = UnitOfMeasure.Pieces,
        val textFieldValueForName: String = "",
        val textFieldValueForAmount: String = "",
        val addGoodsDialogVisible: Boolean = false,
    )
}
