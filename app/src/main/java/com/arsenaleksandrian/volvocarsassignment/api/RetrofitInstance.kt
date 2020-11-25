package com.arsenaleksandrian.volvocarsassignment.api

import com.arsenaleksandrian.volvocarsassignment.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val api: MetaWeatherAPI by lazy {
        retrofit.create(MetaWeatherAPI::class.java)
    }
}