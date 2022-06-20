package `in`.kay.weatherica.data.repository

import `in`.kay.weatherica.data.api.ApiConstants
import `in`.kay.weatherica.data.api.WeatherApi
import `in`.kay.weatherica.data.api.model.WeatherData
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getWeather(city: String): WeatherData {
        return weatherApi.getWeather(apiKey = ApiConstants.apiKey, city = city)
    }
}