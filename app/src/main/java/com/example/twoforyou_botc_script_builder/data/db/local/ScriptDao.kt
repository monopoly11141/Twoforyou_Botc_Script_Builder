package com.example.twoforyou_botc_script_builder.data.db.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.twoforyou_botc_script_builder.data.model.Script
import kotlinx.coroutines.flow.Flow

@Dao
interface ScriptDao {

    @Query("SELECT * FROM script_database")
    fun getAllScript() : Flow<List<Script>>

    @Insert()
    suspend fun insertScript(script: Script)

    @Delete
    suspend fun deleteScript(script: Script)

    @Query("DELETE FROM script_database")
    suspend fun deleteAllScript()
}