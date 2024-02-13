package com.example.documents.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ViewModelState())
    val state: StateFlow<ViewModelState> = _state

    data class ViewModelState(
        val documents: List<Document> = mutableListOf(
            Document(
                date = "12/12/2034",
                signature = "LoremIpsum",
                contractor = "LoremIpsum"
            ),
            Document(
                date = "12/12/2034",
                signature = "LoremIpsum",
                contractor = "LoremIpsum"
            ),
            Document(
                date = "12/12/2034",
                signature = "LoremIpsum",
                contractor = "LoremIpsum"
            ),
        ),
    )
}
