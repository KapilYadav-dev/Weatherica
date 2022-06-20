package `in`.kay.weatherica.views

import `in`.kay.weatherica.data.api.model.WeatherModel
import `in`.kay.weatherica.ui.theme.WeathericaTheme
import `in`.kay.weatherica.views.home.HomeViewModel
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            WeathericaTheme {
                val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
                val state by homeViewModel.state.collectAsState()
                val model = WeatherModel(
                    "${state.main.temp}°C",
                    state.name,
                    "strDate",
                     "",
                    "${state.main.pressure} hcpa",
                    "",
                    "${state.main.humidity} %",
                    "${state.wind.speed} km/h",
                    "${state.wind.speed} km/h",
                    (state.visibility / 1000).toString() + " km"
                )
                Home(model)
            }
        }
    }

    @Composable
    @Preview
    fun Preview() {
        val strTemp = remember {
            "28°C"
        }
        val strCity = remember {
            "Ghaziabad"
        }
        val strDate = remember {
            "19th June 2022, 28°C/40°C"
        }

        val strPrediction = remember {
            "Sunny Sky"
        }
        val strPressue = remember {
            "800hcpa"
        }
        val strPrecipitation = remember {
            "2 mm"
        }
        val strHumidity = remember {
            "56%"
        }
        val strAir = remember {
            "34"
        }
        val strWind = remember {
            "50 km/h"
        }
        val strVisibility = remember {
            "11 km"
        }
        var model = WeatherModel(
            strTemp,
            strCity,
            strDate,
            strPrediction,
            strPressue,
            strPrecipitation,
            strHumidity,
            strAir,
            strWind,
            strVisibility
        )
        Home(model)
    }
}
