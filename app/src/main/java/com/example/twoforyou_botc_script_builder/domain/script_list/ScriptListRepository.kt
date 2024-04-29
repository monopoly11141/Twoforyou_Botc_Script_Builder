package com.example.twoforyou_botc_script_builder.domain.script_list

import com.example.twoforyou_botc_script_builder.data.model.Character

interface ScriptListRepository {
    fun getAllCharactersFromFirebase() : List<Character>
}