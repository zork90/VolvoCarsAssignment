package com.arsenaleksandrian.volvocarsassignment.adapter

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.arsenaleksandrian.volvocarsassignment.R
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var weatherIcon: ImageView = itemView.findViewById(R.id.weather_icon) as ImageView
    var weatherStateName: TextView = itemView.findViewById(R.id.weather_state) as TextView
    var theTemp: TextView = itemView.findViewById(R.id.the_temp) as TextView
    var locationName: TextView = itemView.findViewById(R.id.city_name) as TextView

    var windSpeed: TextView = itemView.findViewById(R.id.wind) as TextView
    var humidity: TextView = itemView.findViewById(R.id.humidity) as TextView
    var airPressure: TextView = itemView.findViewById(R.id.air_pressure) as TextView
    var maxTemp: TextView = itemView.findViewById(R.id.max_temp) as TextView
    var minTemp: TextView = itemView.findViewById(R.id.min_temp) as TextView

    var card: CardView = itemView.findViewById(R.id.weather_card) as CardView
}