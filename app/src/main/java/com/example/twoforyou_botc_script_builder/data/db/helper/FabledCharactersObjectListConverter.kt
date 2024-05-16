package com.example.twoforyou_botc_script_builder.data.db.helper

import androidx.room.TypeConverter
import com.example.twoforyou_botc_script_builder.data.model.FabledCharacter
import com.google.gson.Gson

class FabledCharactersObjectListConverter {

    @TypeConverter
    fun listToJson(value: List<FabledCharacter>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<FabledCharacter>? {
        return Gson().fromJson(value, Array<FabledCharacter>::class.java)?.toList()
    }
}