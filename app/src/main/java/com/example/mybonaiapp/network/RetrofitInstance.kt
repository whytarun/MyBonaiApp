package com.example.mybonaiapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        val BaseURL ="https://test-mobile-configuration-files.s3.eu-central-1.amazonaws.com/"

        fun getRetrofitInstance() :Retrofit{
            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}