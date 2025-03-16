package com.example.engwordmeaning.model

data class WordResponse(
    val word: String = "",
    val phonetic: String? = null,
    val phonetics: List<Phonetic> = emptyList(),
    val meanings: List<Meaning> = emptyList()
)

data class Phonetic(
    val text: String? = null,
    val audio: String? = null
)

data class Meaning(
    val partOfSpeech: String = "",
    val definitions: List<Definition> = emptyList()
)

data class Definition(
    val definition: String = ""
)
