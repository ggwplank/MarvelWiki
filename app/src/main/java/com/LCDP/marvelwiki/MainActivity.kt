package com.LCDP.marvelwiki

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val characterId = 1011334
            fetchCharacterById(characterId)
        }
        }
    }

    private fun fetchCharacterById(characterId: Int) {
        //necessario al momento per fare la ricerca tramite id
        val baseUrl = Constant.BASE_URL + "$characterId"
        //creazione dell'URL completo della richiesta
        val url = "$baseUrl?ts=${Constant.ts}&apikey=${Constant.API_KEY}&hash${Constant.hash()}"//il link generato se cliccato sopra dice che la chiave non è valida
        Log.i("$url", "testResponse")
        //crezione oggetto OkHttpClient per eseguire la richiesta
        val client = OkHttpClient()
        //creazione oggetto di tipo Request con l'URL della richiesta
        val request = Request.Builder()
            .url(url)
            .build()

        //esecuzione della richiesta in modo asincrono
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Gestisci l'errore di connessione
                Log.i("errpre connessione", "testResponse")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()?.string()

                // Utilizza GSON per deserializzare i dati JSON nella classe
                val gson = Gson()
                val result = gson.fromJson(responseBody, CharaterResponse::class.java)

                val character = result.data.results[0]

                // Stampa le informazioni del personaggio nel log
                Log.i("ID: ${character.id}", "testResponse")
                Log.i("Nome: ${character.name}", "testResponse")
                Log.i("Descrizione: ${character.description}", " testResponse")
            }

        })


/*        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.marvelAPI.getCharacterById(characterId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val characterResponse = response.body()
                        val character = characterResponse?.data?.results?.getOrNull(0)

                        if (character != null) {
                             Log.i(character.description, "testResponse")
                        } else {
                            Log.i("Personaggio non trovato", "testResponse")
                        }
                    } else {
                        Log.i("Errore richiesta", "testResponse")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.i("Errore connessione", "testResponse")
                }
            }
        }
    }
*/

}