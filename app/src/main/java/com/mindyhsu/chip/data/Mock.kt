package com.mindyhsu.chip.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindyhsu.chip.AppApplication
import com.mindyhsu.chip.model.EpisodeResult
import timber.log.Timber
import java.io.InputStream

fun loadEpisodesFromAssets(): String? {
    var jsonString: String? = null
    try {
        val inputStream: InputStream = AppApplication.instance.assets.open("StubEpisode123.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        jsonString = String(buffer)
        Timber.d("loadJSONFromAssets json = $jsonString")
    } catch (e: Exception) {
        Timber.e("loadJSONFromAssets Exception = ${e.message}")
    }
    return jsonString
}

fun mockGetEpisodes(): List<EpisodeResult> {
    val listType = object : TypeToken<List<EpisodeResult>>() {}.type
    return Gson().fromJson(loadEpisodesFromAssets(), listType)
}

fun mockSearchEpisodes(keyword: String): List<EpisodeResult> {
    val listType = object : TypeToken<List<EpisodeResult>>() {}.type
    val episodes: List<EpisodeResult> = Gson().fromJson(loadEpisodesFromAssets(), listType)
    val result = mutableListOf<EpisodeResult>()
    episodes.forEach { episode ->
        if (episode.name.contains(keyword, true)) {
            result.add(episode)
        }
    }
    Timber.d("mockSearchEpisodes list = $result")
    return result
}