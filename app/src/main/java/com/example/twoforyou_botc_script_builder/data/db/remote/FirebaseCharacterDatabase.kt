package com.example.twoforyou_botc_script_builder.data.db.remote

import android.util.Log
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.data.model.FabledCharacter
import com.example.twoforyou_botc_script_builder.data.model.helper.Character_Type
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirebaseCharacterDatabase {
    val db = Firebase.firestore
    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList = _characterList.asStateFlow()
    private lateinit var character: Character
    private val _fabledCharacterList = MutableStateFlow<List<FabledCharacter>>(emptyList())
    val fabledCharacterList = _fabledCharacterList.asStateFlow()
    private lateinit var fabledCharacter: FabledCharacter

    init {
        getAllCharacters()
        getAllFabledCharacters()
    }

    private fun getAllCharacters() {

        for (script in LISTOFSCRIPTS) {
            for (characterType in Character_Type.entries) {
                val dbRef = FirebaseDatabase.getInstance().reference.child(script)
                    .child(characterType.toString())

                dbRef.get().addOnSuccessListener { dataSnapshot ->
                    for (characterDataSnapshot in dataSnapshot.children) {
                        character = characterDataSnapshot.getValue(Character::class.java)!!
                        _characterList.value += character
                    }

                }.addOnFailureListener {
                    Log.e("firebase", "Error getting data", it)
                }

            }

        }

    }

    private fun getAllFabledCharacters() {
        val dbRef = FirebaseDatabase.getInstance().reference.child("Fabled")

        dbRef.get().addOnSuccessListener { dataSnapshot ->
            for (fabledCharacterDataSnapshot in dataSnapshot.children) {
                fabledCharacter = fabledCharacterDataSnapshot.getValue(FabledCharacter::class.java)!!
                _fabledCharacterList.value += fabledCharacter
            }

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }

    companion object {
        val LISTOFSCRIPTS = listOf("TroubleBrewing", "BadMoonRising", "SectsAndViolets", "Experimental")
    }
}