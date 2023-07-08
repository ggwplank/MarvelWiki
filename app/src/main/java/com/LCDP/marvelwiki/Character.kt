package com.LCDP.marvelwiki

data class CharaterResponse(var data: CharaterData)
data class CharaterData (val results: List<Character>)

data class Character (
    val id: Int,
    val name: String,
    val description: String
)
