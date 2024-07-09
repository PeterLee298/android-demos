package com.bitmask.android.sqlitesimple

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bitmask.android.sqlitesimple.tables.BrowsingHistoryTable
import com.bitmask.android.sqlitesimple.ui.theme.SQLiteSimpleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SQLiteSimpleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        Log.d("MainActivity", "onCreate");

//        BrowsingHistoryTable.cleanTable();
        BrowsingHistoryTable.insertOrUpgrade("aaa", "bbb", 1);
        for (i in 1..100) {
            BrowsingHistoryTable.insertOrUpgrade("aaa".plus(i), "bbb".plus(i), i);
            Log.d("MainActivity", "BrowsingHistoryTable insertOrUpgrade: $i");
        }

        val count = BrowsingHistoryTable.getCount();
        Log.d("MainActivity", "BrowsingHistoryTable count: $count");

        Toast.makeText(this, "count: $count", Toast.LENGTH_SHORT).show();

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
    SQLiteSimpleTheme {
        Greeting("Android")
    }
}