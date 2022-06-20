package `in`.kay.weatherica.data.api

import `in`.kay.weatherica.BuildConfig

object ApiConstants {
    var ICON_URL="https://raw.githubusercontent.com/visualcrossing/WeatherIcons/73c8cc581d8d35076b47047088f3bc91cb1dd675/SVG/1st%20Set%20-%20Color/"
    const val BASE_URL = "https://weather.visualcrossing.com/"
    const val END_POINT = "VisualCrossingWebServices/rest/services/timeline/"
    const val apiKey = BuildConfig.API_KEY
}