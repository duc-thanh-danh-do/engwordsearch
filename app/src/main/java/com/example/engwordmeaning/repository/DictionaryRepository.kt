package com.example.engwordmeaning.repository

import android.content.Context
import com.example.engwordmeaning.R
import com.example.engwordmeaning.model.WordResponse
import com.example.engwordmeaning.network.RetrofitInstance.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}

class DictionaryRepository(private val context: Context) {
    fun searchWord(word: String): Flow<ApiResult<List<WordResponse>>> = flow {
        if (word.isBlank()) {
            emit(ApiResult.Error(context.getString(R.string.enter_word_to_search)))
            return@flow
        }

        try {
            val response = api.searchWord(word)
            if (response.isNotEmpty()) {
                emit(ApiResult.Success(response))
            } else {
                emit(ApiResult.Error(context.getString(R.string.word_not_found)))
            }
        } catch (e: HttpException) {
            emit(ApiResult.Error(context.getString(R.string.server_error)))
        } catch (e: IOException) {
            emit(ApiResult.Error(context.getString(R.string.network_error)))
        } catch (e: Exception) {
            emit(ApiResult.Error(context.getString(R.string.server_error)))
        }
    }
}
