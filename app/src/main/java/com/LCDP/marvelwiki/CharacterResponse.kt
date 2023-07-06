package com.LCDP.marvelwiki

data class CharacterResponse (
    val data: CharacterData
)

data class CharacterData (
    val results: List<Character>
)