@file:OptIn(ExperimentalMaterial3Api::class)

package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.edu.lntu.cw4.ui.theme.IPZ_CR4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CR4Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "screen1"
                ){
                    composable("screen1"){
                        Screen1(navController = navController)
                    }
                    composable("screen2/{itemNumber}")
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    LazyColumn {
        items(3){
            index ->
            Card(){
                Text(text = "Перейти на сторінку $index")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IPZ_CR4Theme {
        Screen2()
    }
}


@Composable
fun Screen2(itemNumber: Int){
    val scaffoldState = rememberScrollState()
    Scaffold(
        topBar = {

        }
    ){
        paddingValues ->
        Column(
            Modifier.padding(paddingValues)
        ) {
            Text(
                text = "Сторінка 2",
                modifier = Modifier
            )

        }
    }

}

@Composable
fun Screen1(navcontroller: NavController){
    var selectedItem by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()
}

