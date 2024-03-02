@file:OptIn(ExperimentalMaterial3Api::class)

package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.rememberTopAppBarState
import androidx.navigation.navArgument
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
                    composable(
                        "screen2/{itemNumber}",
                        arguments = listOf(navArgument("itemNumber") { type = NavType.IntType })
                    ){backStackEntry ->
                        val itemNumber = backStackEntry.arguments?.getInt("itemNumber") ?: 0
                        Screen2()
                    }
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
fun Screen2() {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Screen 1") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        scaffoldState.snackbarHostState.showSnackbar("UI State Saved")
                    }) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = null)
                    }
                }
            )
        }
    ) {
        paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = topAppBarState.scrollPosition.value,
                top = topAppBarState.height.value + 8.dp, // adjust as needed
                end = 8.dp,
                bottom = 8.dp
        ) {
            items(10) { index ->
                Text(
                    text = "Item $index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedItem = index
                            navController.navigate("screen2/$selectedItem")
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}