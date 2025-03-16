package com.example.engwordmeaning.repository

import android.content.Context
import com.example.engwordmeaning.R
import com.example.engwordmeaning.model.WordResponse
import com.example.engwordmeaning.network.RetrofitInstance.api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DictionaryRepository {
    fun searchWord(word: String, context: Context): Flow<Result<List<WordResponse>>> = flow {
        if (word.isBlank()) {
            emit(Result.failure(Exception(context.getString(R.string.enter_word_to_search))))
            return@flow
        }

        try {
            val response = api.searchWord(word)
            if (response.isNotEmpty()) {
                emit(Result.success(response))
            } else {
                emit(Result.failure(Exception(context.getString(R.string.word_not_found))))
            }
        } catch (e: HttpException) {
            emit(Result.failure(Exception(context.getString(R.string.server_error))))
        } catch (e: IOException) {
            emit(Result.failure(Exception(context.getString(R.string.network_error))))
        } catch (e: Exception) {
            emit(Result.failure(Exception(context.getString(R.string.unexpected_error))))
        }
    }
}
