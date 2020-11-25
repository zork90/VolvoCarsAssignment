package com.arsenaleksandrian.volvocarsassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.arsenaleksandrian.volvocarsassignment.R
import com.arsenaleksandrian.volvocarsassignment.model.WeatherLocation
import com.arsenaleksandrian.volvocarsassignment.util.Constants
import com.arsenaleksandrian.volvocarsassignment.util.getTomorrowsDateFormatted
import com.arsenaleksandrian.volvocarsassignment.util.loadImg
import kotlin.math.roundToInt

class WeatherAdapter(
        private val context: Context?,
        private val weatherLocationList: List<WeatherLocation>?
): RecyclerView.Adapter<WeatherViewHolder>() {

    lateinit var listener: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)

        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherLocationList!![position].consolidated_weather.find { it.applicable_date == getTomorrowsDateFormatted("yyyy-MM-dd") }

        val abbreviation: String? = weather?.weather_state_abbr
        holder.weatherIcon.loadImg(Constants.BASE_URL + "/static/img/weather/png/64/$abbreviation.png")
        holder.weatherStateName.text = weather?.weather_state_name
        holder.theTemp.text = context?.getString(R.string.temperature_centigrade, weather?.the_temp?.roundToInt())

        holder.maxTemp.text = context?.getString(R.string.temperature, weather?.max_temp?.roundToInt())
        holder.minTemp.text = context?.getString(R.string.temperature, weather?.min_temp?.roundToInt())

        holder.humidity.text = context?.getString(R.string.humidity, weather?.humidity)
        holder.airPressure.text = context?.getString(R.string.air_pressure, weather?.air_pressure?.roundToInt())

        holder.windSpeed.text = context?.getString(R.string.wind_speed, weather?.wind_speed?.roundToInt(), weather?.wind_direction_compass)

        holder.locationName.text = weatherLocationList[position].title

/*        holder.card.setOnClickListener {
            listener.invoke(weatherLocationList[position].woeid)
        }*/
    }

    override fun getItemCount(): Int {
        return weatherLocationList!!.size
    }
}