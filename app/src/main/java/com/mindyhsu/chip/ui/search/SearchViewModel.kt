package com.mindyhsu.chip.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindyhsu.chip.data.mockSearchEpisodes
import com.mindyhsu.chip.model.EpisodeResult
import com.mindyhsu.chip.model.Character

class SearchViewModel : ViewModel() {
    private val _searchResult = MutableLiveData<List<EpisodeResult>?>()
    val searchResult: LiveData<List<EpisodeResult>?>
        get() = _searchResult

    private val _navigateToCharacterDetail = MutableLiveData<Character?>()
    val navigateToCharacterDetail: LiveData<Character?>
        get() = _navigateToCharacterDetail

    var keyword: String = ""

    val uiState = SearchUiState(
        onTagClick = { character ->
            _navigateToCharacterDetail.postValue(character)
        }
    )

    fun getEpisodes(searchKeyword: String) {
        keyword = searchKeyword
        _searchResult.postValue(mockSearchEpisodes(searchKeyword))
    }

    fun completeNavigateToCharacterDetail() {
        _navigateToCharacterDetail.postValue(null)
    }
}