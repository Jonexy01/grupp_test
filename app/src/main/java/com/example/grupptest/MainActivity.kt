package com.example.grupptest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grupptest.ui.theme.GruppTestTheme
import com.topwise.emv.TopWiseDevice
import com.topwise.emv.utlis.DeviceState

class MainActivity : ComponentActivity() {

    private val topWiseDevice by lazy {
        TopWiseDevice(this) {
            when (it.state) {
                DeviceState.INSERT_CARD -> {
                    Log.i("INSERT", it.message)
                }
                DeviceState.PROCESSING -> {
                    Log.i("INSERT", it.message)
                }
                DeviceState.INPUT_PIN -> {
                    Log.i("INSERT", it.message)
                }
                DeviceState.PIN_DATA -> {
                    Log.i("INSERT", it.message)
                }
                DeviceState.INFO -> {
                    Log.i("INSERT", it.message)
                }
                else -> {
                    Log.e("ERROR", it.message)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GruppTestTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(200.dp))
                        Text(
                            text = "Tap to read card",
                            fontSize = 18.sp,
                            color = Color.Blue
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Button(onClick = {
                            try {
                                topWiseDevice.configureTerminal()
                                Thread {
                                    topWiseDevice.startEmv("10.00")
                                }.start()
                            } catch (ex: Exception) {
                                Log.e("ERROR", ex.toString())
                            }

                        }) {
                            Text(text = "Proceed")
                        }
                    }
//                    Greeting("Android")
                }
            }
        }
//        setContent {
//            GruppTestTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GruppTestTheme {
        Greeting("Android")
    }
}