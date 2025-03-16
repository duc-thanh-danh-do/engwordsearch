package com.example.engwordmeaning.network

import com.example.engwordmeaning.model.WordResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun searchWord(@Path("word") word: String): List<WordResponse>
}

object RetrofitInstance {
    val api: DictionaryApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}
