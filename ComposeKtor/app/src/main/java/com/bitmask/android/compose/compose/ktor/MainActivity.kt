package com.bitmask.android.compose.compose.ktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bitmask.android.compose.compose.ktor.ui.theme.ComposeKtorTheme
import com.bitmask.android.compose.compose.ktor.viewmodel.SpaceXViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeKtorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier,viewModel: SpaceXViewModel = SpaceXViewModel()) {
    val launches by viewModel.launches.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getLaunches()
    })

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
    ComposeKtorTheme {
        Greeting("Android")
    }
}