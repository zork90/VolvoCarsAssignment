package com.arsenaleksandrian.volvocarsassignment.api

import com.arsenaleksandrian.volvocarsassignment.model.Location
import com.arsenaleksandrian.volvocarsassignment.model.WeatherLocation
import com.arsenaleksandrian.volvocarsassignment.model.Weather
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaWeatherAPI {
    @GET("/api/location/search/")
    suspend fun searchLocation(@Query("query") input: String): Response<List<Location>>

    @GET("/api/location/{woeid}")
    suspend fun fetchWeatherLocation(@Path("woeid") woeid: Int) : Response<WeatherLocation>

    @GET("/api/location/{woeid}/{date}")
    suspend fun fetchWeatherLocationDay(@Path("woeid") woeid: Int, @Path("date") formattedDate: String) : Response<List<Weather>>
}