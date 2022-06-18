package `in`.kay.weatherica

import `in`.kay.weatherica.Views.Home
import `in`.kay.weatherica.ui.theme.WeathericaTheme
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            WeathericaTheme {
                Home()
            }
        }
    }

    @Composable
    @Preview
    fun Preview() {
        Home()
    }
}
