package com.arsenaleksandrian.volvocarsassignment.util

import android.text.TextUtils
import android.widget.ImageView
import com.arsenaleksandrian.volvocarsassignment.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/*
    Helper method to load images into an ImageView using the Picasso library
 */
fun ImageView.loadImg(imageUrl: String?) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}

/*
    Helper method to return tomorrows date in a requested format
 */
fun getTomorrowsDateFormatted(pattern: String): String {
    val tomorrow = LocalDate.now().plusDays(1)
    return tomorrow.format(DateTimeFormatter.ofPattern(pattern))
}
