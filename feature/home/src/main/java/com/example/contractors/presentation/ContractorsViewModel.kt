package com.example.contractors.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ContractorsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    data class ViewModelState(
        val contractors: List<String> = mutableListOf(
            "LoremIpsum",
            "LoremIpsum",
        ),
    )
}
