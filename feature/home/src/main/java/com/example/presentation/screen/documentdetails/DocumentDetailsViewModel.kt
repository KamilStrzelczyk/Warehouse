package com.example.presentation.screen.documentdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Goods
import com.example.domain.usecase.GetDocumentUseCase
import com.example.domain.utils.UnitOfMeasure
import com.example.presentation.component.fakeGoods
import com.example.presentation.navigation.Destination.DocumentDetails.Companion.DOCUMENT_ID_ARGUMENT
import com.example.presentation.screen.documentdetails.DocumentDetailsViewModel.ViewModelState.GoodsTextField
import com.example.presentation.screen.documentdetails.DocumentDetailsViewModel.ViewModelState.GoodsTextField.AMOUNT
import com.example.presentation.screen.documentdetails.DocumentDetailsViewModel.ViewModelState.GoodsTextField.NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDocument: GetDocumentUseCase,
) : ViewModel() {

    private val documentId: String = checkNotNull(savedStateHandle[DOCUMENT_ID_ARGUMENT])

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    init {
        viewModelScope.launch {
            getDocument(documentId.toLong())
        }
    }

    fun onTextValueChange(string: String, goodsTextField: GoodsTextField) {
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

    data class ViewModelState(
        val goods: List<Goods> = mutableListOf(fakeGoods),
        val selectedUnitOfMeasure: UnitOfMeasure = UnitOfMeasure.Pieces,
        val textFieldValueForName: String = "",
        val textFieldValueForAmount: String = "",
        val addGoodsDialogVisible: Boolean = false,
    ) {
        enum class GoodsTextField {
            NAME,
            AMOUNT
        }
    }
}
