package com.example.twoforyou_botc_script_builder.data.script_list

import androidx.compose.runtime.remember
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.data.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botc_script_builder.domain.script_list.ScriptListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ScriptListRepositoryImpl @Inject constructor(
    private val firebaseCharacterDatabase: FirebaseCharacterDatabase
) : ScriptListRepository {
    var _allCharacters = MutableStateFlow<List<Character>>(emptyList())
    val allCharacters = _allCharacters.asStateFlow()

    init {
        _allCharacters.value = getAllCharactersFromFirebase()
    }
    override fun getAllCharactersFromFirebase(): List<Character> {
        return firebaseCharacterDatabase.getAllCharacters()
    }

}