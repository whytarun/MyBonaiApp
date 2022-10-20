package com.example.mybonaiapp.network

import com.example.mybonaiapp.model.MyRecylerList
import retrofit2.http.GET

interface RetrofitService {
    @GET("stories-test/shelf.json")
    suspend fun getDataFromApi() :MyRecylerList
}