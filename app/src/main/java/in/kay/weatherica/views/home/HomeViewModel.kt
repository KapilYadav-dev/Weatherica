package `in`.kay.weatherica.views.home

import `in`.kay.weatherica.data.api.model.WeatherData
import `in`.kay.weatherica.data.repository.WeatherRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepo: WeatherRepo) : ViewModel() {
    private val _state = MutableStateFlow(WeatherData())

    fun getData(city:String):MutableStateFlow<WeatherData> {
        viewModelScope.launch {
            val weather = weatherRepo.getWeather(city)
            _state.value = weather
        }
        return _state
    }
}