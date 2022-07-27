package msikora.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import msikora.task.ui.GroceriesScreen
import msikora.task.ui.GroceriesViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroceriesScreen()
        }
    }
}

//
//@Composable
//fun Greeting(name: String) {
////    Column {
////    }
//}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskTheme {
        Greeting("Android")
    }
}*/
