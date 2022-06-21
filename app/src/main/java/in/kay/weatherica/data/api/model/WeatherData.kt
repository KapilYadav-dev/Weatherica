package `in`.kay.weatherica.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherData(
    @Json(name = "address")
    val address: String = "",
    @Json(name = "currentConditions")
    val currentConditions: CurrentConditions = CurrentConditions(),
    @Json(name = "latitude")
    val latitude: Double = 0.0,
    @Json(name = "longitude")
    val longitude: Double = 0.0,
    @Json(name = "queryCost")
    val queryCost: Int = 0,
    @Json(name = "resolvedAddress")
    val resolvedAddress: String = "",
    @Json(name = "timezone")
    val timezone: String = "",
    @Json(name = "tzoffset")
    val tzoffset: Double = 0.0
) {
    @JsonClass(generateAdapter = true)
    data class CurrentConditions(
        @Json(name = "cloudcover")
        val cloudcover: Double? = 0.0,
        @Json(name = "conditions")
        val conditions: String? = "",
        @Json(name = "datetime")
        val datetime: String? = "",
        @Json(name = "datetimeEpoch")
        val datetimeEpoch: Long? = 0,
        @Json(name = "feelslike")
        val feelslike: Double? = 0.0,
        @Json(name = "humidity")
        val humidity: Double? = 0.0,
        @Json(name = "icon")
        val icon: String? = "",
        @Json(name = "precip")
        val precip: Double? = 0.0,
        @Json(name = "pressure")
        val pressure: Double? = 0.0,
        @Json(name = "sunrise")
        val sunrise: String? = "",
        @Json(name = "sunriseEpoch")
        val sunriseEpoch: Long? = 0,
        @Json(name = "sunset")
        val sunset: String? = "",
        @Json(name = "sunsetEpoch")
        val sunsetEpoch: Long? = 0,
        @Json(name = "temp")
        val temp: Double? = 0.0,
        @Json(name = "uvindex")
        val uvindex: Double? = 0.0,
        @Json(name = "visibility")
        val visibility: Double? = 0.0,
        @Json(name = "windspeed")
        val windspeed: Double? = 0.0
    )
}