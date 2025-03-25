package ru.mrapple100.sqlroom

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.mrapple100.sqlroom.ui.theme.SQLRoomTheme
import java.util.ArrayList

class MainActivity : ComponentActivity() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
    lateinit var db: AppDataAplication

    var arrayDerevo = ArrayList<Derevo>().apply {
        add(ru.mrapple100.sqlroom.Derevo(122, "123", 123.0f))
        add(Derevo(123,"123",123.0f))
        add(Derevo(121,"123",123.0f))
    }
    var derevoMutableStateFlow = MutableStateFlow(arrayDerevo)
    var _derevoStateFlow = derevoMutableStateFlow.asStateFlow()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = LocalDI.db

        setContent {
            SQLRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        var textDerevo by remember { mutableStateOf("") }
        val derevoState = _derevoStateFlow.collectAsState()
        Column {
            TextField(value = textDerevo,
                onValueChange = {it -> textDerevo=it}
            )
            Button(onClick = {
                GlobalScope.launch {
                    db.derevoDAO().insertDerevo(Derevo(0, textDerevo, 100.0f))
                    derevoMutableStateFlow.value = db.derevoDAO().getAllDerevo() as ArrayList<Derevo>
                }
            }) {
                Text(text = "Insert")
            }

            LazyColumn {
                items(derevoState.value) { derevo ->
                    Card {
                        Text(text = derevo.material)
                        Text(text = "" + derevo.price)

                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SQLRoomTheme {
            Greeting("Android")
        }
    }

}
