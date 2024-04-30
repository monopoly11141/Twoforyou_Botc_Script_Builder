package com.example.twoforyou_botc_script_builder.data.model


import com.example.twoforyou_botc_script_builder.data.model.helper.Script_General_Info

data class Script(
    val scriptGeneralInfo : Script_General_Info? = null,
    val charactersList : MutableList<String> = mutableListOf(),
    val charactersObjectList: MutableList<Character> = mutableListOf()
)
