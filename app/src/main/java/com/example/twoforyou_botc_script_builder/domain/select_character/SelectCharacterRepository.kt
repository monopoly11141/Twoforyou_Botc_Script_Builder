package com.example.twoforyou_botc_script_builder.domain.select_character

import com.example.twoforyou_botc_script_builder.data.model.Character
import kotlinx.coroutines.flow.StateFlow

interface SelectCharacterRepository {
    //all the possible characters in var.edition
    val allCharacters: StateFlow<List<Character>>
}