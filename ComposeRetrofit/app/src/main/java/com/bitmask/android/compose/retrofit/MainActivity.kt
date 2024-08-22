package com.bitmask.android.compose.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bitmask.android.compose.retrofit.model.Launch
import com.bitmask.android.compose.retrofit.ui.theme.ComposeRetrofitTheme
import com.bitmask.android.compose.retrofit.viewmodel.SpaceXViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeRetrofitTheme {
                Scaffold { innerPadding ->
                    MainScreen(modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier,viewModel: SpaceXViewModel = SpaceXViewModel()) {
    val launches by viewModel.launches.collectAsState()

    Greeting("Android", launches.toList(), onclick = {
        viewModel.getLaunches()
    })
}

@Composable
fun Greeting(name: String, launches: List<Launch>, modifier: Modifier = Modifier, onclick: () -> Unit = {}) {

    Column {

        Text(
            text = "Hello $name!"
        )

        Button(onClick = onclick) {
            Text(text = "Click me!")
        }

        if (launches.isNullOrEmpty()) {
            Text(text = "No data")
        } else {
            LazyColumn {
                launches.forEach {
                    item {
                        Text(text = "${it.id} ${it.details}")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeRetrofitTheme {
        Greeting("Android", emptyList())
    }
}