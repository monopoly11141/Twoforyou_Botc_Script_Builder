package com.example.twoforyou_botc_script_builder.data.select_character

import com.example.twoforyou_botc_script_builder.data.db.remote.FirebaseCharacterDatabase
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.domain.select_character.SelectCharacterRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SelectCharacterRepositoryImpl @Inject constructor(
    private val firebaseCharacterDatabase: FirebaseCharacterDatabase
) : SelectCharacterRepository {
    override val allCharacters: StateFlow<List<Character>> by lazy {
        firebaseCharacterDatabase.characterList
    }


}