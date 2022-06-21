package `in`.kay.weatherica.views

import `in`.kay.weatherica.BuildConfig.APP_CENTER_KEY
import `in`.kay.weatherica.ui.theme.WeathericaTheme
import `in`.kay.weatherica.views.home.Home
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.BuildConfig
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Config app center for CI/CD of application
        AppCenter.start(
            application, APP_CENTER_KEY,
            Analytics::class.java, Crashes::class.java
        )
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
}
