package ru.mrapple100.sqlroom

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.mrapple100.sqlroom.local.AppDataAplication
import ru.mrapple100.sqlroom.local.Derevo
import ru.mrapple100.sqlroom.local.DerevoWithPhrase
import ru.mrapple100.sqlroom.local.Phrase
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

    var arrayDerevoWithPhrase = ArrayList<DerevoWithPhrase>().apply {
        add(DerevoWithPhrase(Derevo(122, "123", 123.0f,0), Phrase(0,"213234556")))
        add(DerevoWithPhrase(Derevo(122, "123", 123.0f,0), Phrase(0,"23456789")))
        add(DerevoWithPhrase(Derevo(122, "123", 123.0f,0), Phrase(0,"sdfghjk")))
    }
    var derevoWithPhraseMutableStateFlow = MutableStateFlow(arrayDerevoWithPhrase)
    var _derevoWithPhraseStateFlow = derevoWithPhraseMutableStateFlow.asStateFlow()

    var arrayPhrase = ArrayList<Phrase>().apply {
        add( Phrase(0,"213234556"))

    }

    var phraseMutableStateFlow = MutableStateFlow(arrayPhrase)
    var _phraseStateFlow = phraseMutableStateFlow.asStateFlow()



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
        val derevoWithPhraseState = _derevoWithPhraseStateFlow.collectAsState()
        val phraseState = _phraseStateFlow.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = textDerevo,
                onValueChange = { it -> textDerevo=it}
            )
            Button(onClick = {
                GlobalScope.launch {
                    db.derevoDAO().insertPhrase(Phrase(0,"Я проснулся"))
                    phraseMutableStateFlow.value = db.derevoDAO().getAllPhrase() as ArrayList<Phrase>
                }
            }) {
                Text(text = "InsertPhrase")
            }
            Button(onClick = {
                GlobalScope.launch {
                    db.derevoDAO().insertDerevo(Derevo(0, textDerevo, 100.0f,1))
                    derevoWithPhraseMutableStateFlow.value = db.derevoDAO().getAllDerevoWithPhrase() as ArrayList<DerevoWithPhrase>
                }
            }) {
                Text(text = "Insert")
            }

            LazyColumn {
                items(derevoWithPhraseState.value) { derevoWithPhrase ->
                    Card(
                        modifier = Modifier
                            .size(200.dp, 100.dp)
                            .padding(0.dp, 10.dp)
                    ) {
                        Row {
                            Column(
                                modifier = Modifier
                                    .padding(5.dp)
                            ) {
                                Text(text = derevoWithPhrase.derevo.material)
                                Text(text = "" + derevoWithPhrase.derevo.price)
                            }
                            Column(
                                modifier = Modifier
                                    .padding(5.dp)
                            ) {
                                Text(text = derevoWithPhrase.phrase.phrase)
                            }
                        }

                    }
                }
            }

            Text(modifier = Modifier
                .padding(15.dp)
                , text = "Фразы")


            LazyColumn {
                items(phraseState.value) { phrase ->
                    Card(
                        modifier = Modifier
                            .size(200.dp, 100.dp)
                            .padding(0.dp, 10.dp)
                    ) {
                        Row {

                            Column(
                                modifier = Modifier
                                    .padding(5.dp)
                            ) {
                                Text(text = ""+phrase.phraseId)
                                Text(text = phrase.phrase)
                            }
                        }

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
