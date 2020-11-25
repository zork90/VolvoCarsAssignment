package com.arsenaleksandrian.volvocarsassignment.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsenaleksandrian.volvocarsassignment.R
import com.arsenaleksandrian.volvocarsassignment.adapter.WeatherAdapter
import com.arsenaleksandrian.volvocarsassignment.model.*
import com.arsenaleksandrian.volvocarsassignment.repository.Repository
import com.arsenaleksandrian.volvocarsassignment.util.getTomorrowsDateFormatted
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"
    private lateinit var viewModel: HomeViewModel

    private val cities = listOf("Gothenburg", "Stockholm", "Mountain View", "London", "New York", "Berlin")
    private var weatherList = mutableListOf<WeatherLocation>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val viewModelFactory = HomeViewModelFactory(repository = Repository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.weather_list.layoutManager = LinearLayoutManager(context)
        initObservers()

        tomorrows_date.text = getTomorrowsDateFormatted("dd MMMM yyyy")
    }

    private fun initObservers() {
        val adapter = WeatherAdapter(context, weatherList)
        val recyclerView = requireView().weather_list
        recyclerView.adapter = adapter

        cities.forEach { city ->
            viewModel.searchLocation(city)
        }

        viewModel.getLocation().observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                val location = response.body()?.first()
                viewModel.fetchWeatherLocation(location!!.woeid)

                Log.d(TAG, response.body().toString())
            } else {
                Snackbar.make(requireView(), response.message() ?: "", Snackbar.LENGTH_LONG)
                Log.e(TAG,response.message())
            }
        })

        viewModel.getWeatherLocation().observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                weatherList.add(response.body()!!)
                adapter.notifyDataSetChanged()

                Log.d(TAG, response.body().toString())
            } else {
                Snackbar.make(requireView(), response.message() ?: "", Snackbar.LENGTH_LONG)
                Log.e(TAG,response.message())
            }
        })
    }
}