package com.example.twoforyou_botc_script_builder.data.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.twoforyou_botc_script_builder.data.db.helper.CharacterListConverter
import com.example.twoforyou_botc_script_builder.data.db.helper.CharactersObjectListConverter
import com.example.twoforyou_botc_script_builder.data.model.Script

@Database(
    entities = [Script::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    CharacterListConverter::class,
    CharactersObjectListConverter::class
)
abstract class ScriptDb : RoomDatabase() {
    abstract val scriptDao: ScriptDao

}