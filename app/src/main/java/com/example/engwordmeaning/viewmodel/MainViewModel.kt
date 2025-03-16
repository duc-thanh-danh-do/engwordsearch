package com.example.engwordmeaning.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.engwordmeaning.R
import com.example.engwordmeaning.model.WordResponse
import com.example.engwordmeaning.repository.ApiResult
import com.example.engwordmeaning.repository.DictionaryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DictionaryRepository(application.applicationContext)

    private val _searchResult = MutableStateFlow<List<WordResponse>?>(null)
    val searchResult: StateFlow<List<WordResponse>?> = _searchResult

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun searchWord(word: String) {
        if (word.isBlank()) {
            _errorMessage.value = getApplication<Application>().getString(R.string.enter_word_to_search)
            _searchResult.value = null
            return
        }

        viewModelScope.launch {
            _loading.value = true
            _errorMessage.value = null
            _searchResult.value = null

            repository.searchWord(word).collect { result ->
                _loading.value = false
                when (result) {
                    is ApiResult.Success -> {
                        _searchResult.value = result.data
                        _errorMessage.value = null
                    }
                    is ApiResult.Error -> {
                        _errorMessage.value = result.message
                    }
                }
            }
        }
    }
}
