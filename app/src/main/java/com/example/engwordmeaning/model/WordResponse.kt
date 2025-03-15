package com.example.engwordmeaning.model

data class WordResponse(
    val word: String = "",
    val meanings: List<Meaning> = emptyList()
)

data class Meaning(
    val partOfSpeech: String = "",
    val definitions: List<Definition> = emptyList()
)

data class Definition(
    val definition: String = ""
)
