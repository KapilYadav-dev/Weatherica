package `in`.kay.weatherica.Views

import `in`.kay.weatherica.R
import `in`.kay.weatherica.ui.theme.Hint
import `in`.kay.weatherica.ui.theme.Poppins
import `in`.kay.weatherica.ui.theme.Purple
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Preview
@Composable
fun Home() {
    val strTemp = remember {
        "28°C"
    }
    val strCity = remember {
        "Banglore"
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
        "4 km/h"
    }
    val strVisibility = remember {
        "11 km"
    }
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
                    centerHorizontallyTo(parent)
                }
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bottomSection) {
                    top.linkTo(headerSection.bottom, -36.dp)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
                .background(Purple, RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)),
        ) {
            val (tvTemp, tvCity, tvDate, tvPrediction, ivPrediction, viewDetails) = createRefs()
            Text(
                text = strTemp,
                fontSize = 60.sp,
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
                text = strCity,
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
                text = strDate,
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
                text = strPrediction,
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.constrainAs(tvPrediction) {
                    end.linkTo(parent.end, 24.dp)
                    centerVerticallyTo(tvDate)
                }

            )

            Image(
                painter = painterResource(id = R.drawable.ic_sun),
                contentDescription = "",
                modifier = Modifier
                    .height(110.dp)
                    .width(110.dp)
                    .constrainAs(ivPrediction) {
                        bottom.linkTo(tvPrediction.top, 16.dp)
                        centerHorizontallyTo(tvPrediction)
                    }
            )

            Column(modifier = Modifier
                .constrainAs(viewDetails) {
                    top.linkTo(tvDate.bottom, 24.dp)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Detail("Pressue", strPressue)
                    Detail("Precipitation", strPrecipitation)

                    Detail("Humidity", strHumidity)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Detail("Air Quality", strAir)
                    Detail("Wind", strWind)
                    Detail("Visibility", strVisibility)
                }
            }
        }

    }
}

@Composable
fun Detail(title: String, value: String) {
    Column() {
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
            fontWeight = FontWeight.Normal
        )
    }
}


