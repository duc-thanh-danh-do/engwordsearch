package com.example.engwordmeaning.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engwordmeaning.R
import com.example.engwordmeaning.model.WordResponse
import com.example.engwordmeaning.repository.DictionaryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DictionaryRepository) : ViewModel() {
    private val _searchResult = MutableStateFlow<List<WordResponse>?>(null)
    val searchResult: StateFlow<List<WordResponse>?> = _searchResult

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun searchWord(word: String, context: Context) {
        if (word.isBlank()) {
            _errorMessage.value = context.getString(R.string.enter_word_to_search)
            _searchResult.value = null
            return
        }

        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = null
            _searchResult.value = null

            repository.searchWord(word, context).collect { result ->
                _loading.value = false
                result.onSuccess { _searchResult.value = it }
                    .onFailure { _errorMessage.value = it.message }
            }
        }
    }
}
