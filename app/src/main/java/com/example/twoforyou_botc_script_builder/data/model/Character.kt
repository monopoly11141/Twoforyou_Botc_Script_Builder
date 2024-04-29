package com.example.twoforyou_botc_script_builder.data.model

import com.example.twoforyou_botc_script_builder.domain.enum.Character_Type

data class Character(
    val name: String,
    val characterType: Character_Type,
    val ability: String,
    val isFormatChangingRole: Boolean,
    val imageUrl : String
)
