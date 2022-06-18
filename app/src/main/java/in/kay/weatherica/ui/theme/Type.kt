package `in`.kay.weatherica.ui.theme

import `in`.kay.weatherica.R
import androidx.compose.material.Typography
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Poppins = FontFamily(
    Font(R.font.poppins_semi, FontWeight.SemiBold),
    Font(R.font.poppins_regular, FontWeight.Normal)
)

val Typography = Typography(
    defaultFontFamily = Poppins
)