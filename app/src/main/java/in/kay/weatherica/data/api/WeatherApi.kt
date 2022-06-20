package `in`.kay.weatherica.data.api

import `in`.kay.weatherica.data.api.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("${ApiConstants.END_POINT}{city}")
    suspend fun getWeather(
        @Path("city") city: String,
        @Query("contentType") contentType: String = "json",
        @Query("key") apiKey: String,
        @Query("include") include: String = "current",
        @Query("unitGroup") unit: String = "metric"
    ): WeatherData
}