package com.example.engwordmeaning.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engwordmeaning.model.WordResponse
import com.example.engwordmeaning.repository.DictionaryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = DictionaryRepository()

    private val _searchResult = MutableStateFlow<List<WordResponse>?>(null)
    val searchResult: StateFlow<List<WordResponse>?> = _searchResult

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun searchWord(word: String) {
        if (word.isBlank()) {
            _errorMessage.value = "Please enter a word to search"
            _searchResult.value = null
            return
        }

        viewModelScope.launch {
            _errorMessage.value = null
            _searchResult.value = null

            repository.searchWord(word).collect { response ->
                if (response.isNullOrEmpty()) {
                    _errorMessage.value = "Word not found"
                } else {
                    _searchResult.value = response
                    _errorMessage.value = null
                }
            }
        }
    }
}
