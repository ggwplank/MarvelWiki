package com.LCDP.marvelwiki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            val characterId = 10
            fetchCharacterById(characterId)
        }
    }

    private fun fetchCharacterById(characterId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.marvelAPI.getCharacterById(characterId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val characterResponse = response.body()
                        val character = characterResponse?.data?.results?.getOrNull(0)

                        if (character != null) {
                             println(character.description)
                        } else {
                            println("Personaggio non trovato")
                        }
                    } else {
                        println("Errore nella richiesta")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println("Errore nella connessione")
                }
            }
        }
    }


