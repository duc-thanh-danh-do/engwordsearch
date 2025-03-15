package com.example.engwordmeaning.repository

import com.example.engwordmeaning.model.WordResponse
import com.example.engwordmeaning.network.RetrofitInstance.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DictionaryRepository {
    fun searchWord(word: String): Flow<List<WordResponse>?> = flow {
        if (word.isBlank()) return@flow

        try {
            val response = api.searchWord(word)
            emit(if (response.isNotEmpty()) response else emptyList())
        } catch (e: HttpException) {
            emit(emptyList()) //
        } catch (e: IOException) {
            emit(emptyList()) //
        } catch (e: Exception) {
            emit(emptyList()) //
        }
    }
}
