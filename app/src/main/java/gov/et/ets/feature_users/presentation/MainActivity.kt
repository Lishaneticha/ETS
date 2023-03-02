package gov.et.ets.feature_users.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import gov.et.ets.ui.theme.ETSTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val service  = Retrofit.Builder()
//            .baseUrl("http://192.168.8.102:1994")
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            .build()
//            .create(HostPingService::class.java)
//        CoroutineScope(IO).launch {
//            val result = service.postPing(listOf("10.20.0.21", "10.1.11.38"))
//            Log.d("ping result", "reult: ${result.result}")
//        }
        setContent {
            ETSTheme {
                Navigation()
            }
        }
    }
}
