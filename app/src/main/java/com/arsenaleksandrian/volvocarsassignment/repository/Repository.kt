package com.arsenaleksandrian.volvocarsassignment.repository

import com.arsenaleksandrian.volvocarsassignment.api.RetrofitInstance
import com.arsenaleksandrian.volvocarsassignment.model.*
import io.reactivex.Observable
import retrofit2.Response


class Repository {
    suspend fun searchLocation(location: String): Response<List<Location>> {
        return RetrofitInstance.api.searchLocation(location)
    }

    suspend fun fetchWeatherLocation(woeid: Int): Response<WeatherLocation> {
        return RetrofitInstance.api.fetchWeatherLocation(woeid)
    }

    suspend fun fetchWeatherLocationDay(woeid: Int, formattedDay: String): Response<List<Weather>> {
        return RetrofitInstance.api.fetchWeatherLocationDay(woeid, formattedDay)
    }

}