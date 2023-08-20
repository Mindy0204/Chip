package com.mindyhsu.chip.model

data class EpisodeResult(
    val id: String,
    val name: String,
    val episode: String,
    val air_date: String,
    val characters: List<Character>
)