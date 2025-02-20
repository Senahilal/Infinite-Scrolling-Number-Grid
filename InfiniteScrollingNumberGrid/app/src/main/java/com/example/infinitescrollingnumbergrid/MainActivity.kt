package com.example.infinitescrollingnumbergrid

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.* // For layout components
import androidx.compose.material3.*
import androidx.compose.runtime.* // For state management
import androidx.compose.ui.Alignment
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.infinitescrollingnumbergrid.ui.theme.InfiniteScrollingNumberGridTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfiniteScrollingNumberGridTheme {
                    NumberGrid()
            }
        }
    }
}



@Composable
fun NumberGrid() {
    
    var numbers by rememberSaveable { mutableStateOf((1..30).toList()) }
    
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(30.dp)) {

        //on click set numbers list to 1..30
        Button(onClick = { numbers = (1..30).toList() }, modifier = Modifier.width(200.dp).height(30.dp)) {
            Text("Go back to 1-30")
        }

        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxSize()) {

            //creating box for each number
            items(numbers) { number ->
                Box(modifier = Modifier.padding(8.dp)) {
                    Text(text = number.toString(), modifier = Modifier.padding(16.dp))
                }
            }

            item {
                coroutineScope.launch {
                    delay(1000) // Simulate loading delay
                    // Get the next number
                    val indexStart = (numbers.maxOrNull() ?: 0) + 1

                    //create new list
                    val newNumbers = (indexStart..(indexStart + 29)).toList()
                    numbers = numbers + newNumbers
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
    InfiniteScrollingNumberGridTheme {
        Greeting("Android")
    }
}