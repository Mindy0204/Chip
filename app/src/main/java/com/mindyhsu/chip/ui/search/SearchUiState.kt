package com.mindyhsu.chip.ui.search

import com.mindyhsu.chip.model.Character

data class SearchUiState(
    val onTagClick: (character: Character) -> Unit
)
