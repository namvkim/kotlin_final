package com.example.jetnote.service

import com.example.jetnote.domain.model.BookModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://600f8aef6c21e1001704eda0.mockapi.io/"

interface APIService {
    @GET("books")
    suspend fun getBooks(): List<BookModel>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}