package com.arsenaleksandrian.volvocarsassignment.model

data class WeatherLocation (
    val consolidated_weather : List<Weather>,
    val time : String,
    val sun_rise : String,
    val sun_set : String,
    val timezone_name : String,
    val parent : Location,
    val sources : List<Sources>,
    val title : String,
    val location_type : String,
    val woeid : Int,
    val latt_long : String,
    val timezone : String
) {
    data class Sources (
        val title : String,
        val slug : String,
        val url : String,
        val crawl_rate : Int
    )
}