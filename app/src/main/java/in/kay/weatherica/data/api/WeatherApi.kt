package `in`.kay.weatherica.data.api

import `in`.kay.weatherica.data.api.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(ApiConstants.END_POINT)
    suspend fun getWeather(
        @Query("q") place: String,
        @Query("appid") apiKey: String,
        @Query("units") unit: String = "metric"
    ): WeatherData
}