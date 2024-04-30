package com.example.twoforyou_botc_script_builder.ui.select_character

import com.example.twoforyou_botc_script_builder.data.model.Character

data class SelectCharacterUiState(
    val allCharacters: List<Character> = emptyList(),
    )