package `in`.kay.weatherica.views.home

import `in`.kay.weatherica.R
import `in`.kay.weatherica.data.api.model.WeatherData
import `in`.kay.weatherica.ui.theme.Hint
import `in`.kay.weatherica.ui.theme.Poppins
import `in`.kay.weatherica.ui.theme.Purple
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home() {
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val data = homeViewModel.getData("Antartica").collectAsState().value
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    val date = current.format(formatter)
    val day = LocalDate.now().dayOfWeek.name.lowercase(Locale.getDefault())
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }


    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
    ) {
        val (headerSection, bottomSection) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_header),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(headerSection) {
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomSection.top, (-36).dp)
                    centerHorizontallyTo(parent)
                    height = Dimension.fillToConstraints
                }
        )



        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomSection) {
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                }
                .background(Purple, RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)),
        ) {
            val (tvTemp, tvCity, tvDate, tvPrediction, ivPrediction, viewDetails) = createRefs()
            Text(
                text = data.currentConditions.temp!!.toInt().toString() + " °C",
                fontSize = 48.sp,
                color = Color.White,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .height(80.dp)
                    .constrainAs(tvTemp) {
                        start.linkTo(parent.start, 24.dp)
                        top.linkTo(parent.top, 12.dp)
                    }, style = TextStyle(
                    shadow = Shadow(Color.Black, Offset(12f, 12f), 12f)
                )

            )
            Text(
                text = data.address,
                fontSize = 22.sp,
                color = Color.White,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.constrainAs(tvCity) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(tvTemp.bottom, 24.dp)
                }

            )
            Text(
                text = "$day, $date ".let {
                    var idx = it.lastIndexOf(',')
                    it.removeRange(idx, it.length)
                },
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.constrainAs(tvDate) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(tvCity.bottom)
                }

            )
            Text(
                text = data.currentConditions.conditions!!,
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.constrainAs(tvPrediction) {
                    end.linkTo(parent.end, 24.dp)
                    bottom.linkTo(tvDate.bottom)
                    top.linkTo(tvCity.top)
                }

            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getImage(data))
                    .crossfade(true)
                    .crossfade(1000)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentScale = ContentScale.Fit,
                contentDescription = "",
                modifier = Modifier
                    .height(140.dp)
                    .width(140.dp)
                    .constrainAs(ivPrediction) {
                        bottom.linkTo(tvPrediction.top, 2.dp)
                        centerHorizontallyTo(tvPrediction)
                    }
            )

            Column(modifier = Modifier
                .constrainAs(viewDetails) {
                    top.linkTo(tvDate.bottom, 24.dp)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    bottom.linkTo(parent.bottom, 24.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Detail("Pressure", data.currentConditions.pressure.toString())
                    Detail("Precipitation", data.currentConditions.precip.toString() + " mm")

                    Detail("Humidity", data.currentConditions.humidity.toString() + " %")
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Detail("Air Quality", "Good")
                    Detail("Wind", data.currentConditions.windspeed.toString() + " km/h")
                    Detail("Visibility", data.currentConditions.visibility.toString() + " km")
                }
            }
        }

    }
}

fun getImage(data: WeatherData): String {
    return when (data.currentConditions.icon) {
        "clear-day" -> "https://icons-for-free.com/iconfiles/png/512/sunny+temperature+weather+icon-1320196637430890623.png"
        "wind" -> "https://icons-for-free.com/iconfiles/png/512/storm+weather+wind+windy+icon-1320196635706326668.png"
        "rain" -> "https://icons-for-free.com/iconfiles/png/512/cloudy+forecast+rain+weather+icon-1320196634592211825.png"
        "snow" -> "https://icons-for-free.com/iconfiles/png/512/snow+weather+winter+icon-1320196635362560437.png"
        "cloudy" -> "https://icons-for-free.com/iconfiles/png/512/clouds+cloudy+weather+icon-1320196635828207342.png"
        "partly-cloudy-day" -> "https://icons-for-free.com/iconfiles/png/512/clouds+cloudy+weather+icon-1320196635828207342.png"
        else -> ""
    }
}

@Composable
fun Detail(title: String, value: String) {
    Column {
        Text(
            text = title,
            color = Hint,
            fontSize = 12.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis
        )
    }
}


