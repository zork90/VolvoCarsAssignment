package com.arsenaleksandrian.volvocarsassignment.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsenaleksandrian.volvocarsassignment.model.*
import com.arsenaleksandrian.volvocarsassignment.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val repository: Repository): ViewModel() {
    private val TAG = "HomeViewModel"

    private val locationResponse: MutableLiveData<Response<List<Location>>> = MutableLiveData()
    private val weatherLocationResponse: MutableLiveData<Response<WeatherLocation>> = MutableLiveData()
    private val weatherResponse: MutableLiveData<Response<List<Weather>>> = MutableLiveData()

    fun getLocation(): LiveData<Response<List<Location>>> = locationResponse
    fun searchLocation(location: String) {
        viewModelScope.launch {
            runCatching {
                repository.searchLocation(location)
            }.onSuccess {
                if (it.isSuccessful) locationResponse.value = it
                Log.d(TAG, it.body().toString())
            }.onFailure {
                Log.e(TAG, it.message)
            }
        }
    }

    fun getWeatherLocation(): LiveData<Response<WeatherLocation>> = weatherLocationResponse
    fun fetchWeatherLocation(woeid: Int) {
        viewModelScope.launch {
            runCatching {
                repository.fetchWeatherLocation(woeid)
            }.onSuccess { response ->
                if (response.isSuccessful) weatherLocationResponse.value = response
            }.onFailure {
                Log.e(TAG, it.message)
            }
        }
    }

    fun getWeatherLocationDay(): LiveData<Response<List<Weather>>> = weatherResponse
    fun fetchWeatherLocationDay(woeid: Int, date: String) {
        viewModelScope.launch {
            runCatching {
                repository.fetchWeatherLocationDay(woeid, date)
            }.onSuccess { response ->
                if (response.isSuccessful) weatherResponse.value = response
            }.onFailure {
                Log.e(TAG, it.message)
            }
        }
    }
}