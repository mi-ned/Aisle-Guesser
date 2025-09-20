package com.example.aisleguessr.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.aisleguessr.data.GlossaryEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class GlossaryViewModel(
    private val glossaryEntries: List<GlossaryEntry>
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val filteredEntries: StateFlow<List<GlossaryEntry>> = searchQuery
        .map { query ->
            val regex = Regex("\\b$query", RegexOption.IGNORE_CASE)
            glossaryEntries.filter { entry ->
                val combinedText = "${entry.aisle}: ${entry.content}"
                query.isBlank() || combinedText.contains(regex)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = glossaryEntries
        )

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    companion object {
        fun provideFactory(
            glossaryEntries: List<GlossaryEntry>
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return GlossaryViewModel(glossaryEntries) as T
            }
        }
    }

}