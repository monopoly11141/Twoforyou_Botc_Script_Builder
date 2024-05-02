package com.example.twoforyou_botc_script_builder.data.db.helper

import androidx.room.TypeConverter
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.google.gson.Gson

class CharactersObjectListConverter {

    @TypeConverter
    fun listToJson(value: List<Character>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Character>? {
        return Gson().fromJson(value, Array<Character>::class.java)?.toList()
    }
}