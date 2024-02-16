package com.example.presentation.screen.goodsDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Goods
import com.example.domain.usecase.DeleteGoodsUseCase
import com.example.domain.usecase.GetGoodsUseCase
import com.example.domain.usecase.UpdateGoodsUseCase
import com.example.domain.utils.UnitOfMeasure
import com.example.presentation.component.GoodsTextFieldType
import com.example.presentation.component.GoodsTextFieldType.AMOUNT
import com.example.presentation.component.GoodsTextFieldType.NAME
import com.example.presentation.component.fakeGoods
import com.example.presentation.navigation.Destination.GoodsDetails.Companion.GOODS_DOCUMENT_ID_ARGUMENT
import com.example.presentation.navigation.Destination.GoodsDetails.Companion.GOODS_ID_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoodsDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGoods: GetGoodsUseCase,
    private val deleteGoods: DeleteGoodsUseCase,
    private val update: UpdateGoodsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event

    private val goodsId: String = checkNotNull(savedStateHandle[GOODS_ID_ARGUMENT])
    private val documentId: String = checkNotNull(savedStateHandle[GOODS_DOCUMENT_ID_ARGUMENT])

    init {
        updateGoods()
    }

    private fun updateGoods() {
        viewModelScope.launch {
            val goods = getGoods(goodsId.toLong())
            _state.update {
                it.copy(goods = goods)
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

    fun editGoodsDialogVisible(visible: Boolean) {
        _state.update {
            _state.value.copy(
                editGoodsDialogVisible = visible,
            )
        }
    }

    fun onSelectedUnitOfMeasure(unitOfMeasure: UnitOfMeasure) {
        _state.update {
            _state.value.copy(
                selectedUnitOfMeasure = unitOfMeasure,
            )
        }
    }

    fun onEditGoodsClicked() {
        val name = _state.value.textFieldValueForName
        val amount = _state.value.textFieldValueForAmount
        val unitOfMeasure = _state.value.selectedUnitOfMeasure

        if (name.isNotEmpty() && amount.isNotEmpty()) {
            val goods = Goods(
                id = _state.value.goods.id,
                amount = amount.toLong(),
                name = name,
                unitOfMeasure = unitOfMeasure,
            )
            viewModelScope.launch {
                update(goods)
            }
            updateGoods()
            editGoodsDialogVisible(false)
        }
    }

    fun onDeleteGoodsClicked() {
        viewModelScope.launch {
            deleteGoods(
                documentId = documentId.toLong(),
                goods = _state.value.goods,
            )
            _event.emit(Event.NavigateBack)
        }
    }

    sealed class Event {
        data object NavigateBack : Event()
    }

    data class ViewModelState(
        val goods: Goods = fakeGoods,
        val selectedUnitOfMeasure: UnitOfMeasure = UnitOfMeasure.Pieces,
        val textFieldValueForName: String = "",
        val textFieldValueForAmount: String = "",
        val editGoodsDialogVisible: Boolean = false,
    )
}
