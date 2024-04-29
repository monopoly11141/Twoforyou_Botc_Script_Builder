package com.example.twoforyou_botc_script_builder.data.remote

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.twoforyou_botc_script_builder.data.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseCharacterDatabase {
    private val db = Firebase.firestore
    private lateinit var characterList: MutableList<Character>
    private lateinit var character: Character

    fun getAllCharacters() :  MutableList<Character> {
        characterList = mutableListOf()

        CoroutineScope(Dispatchers.IO).launch {
            for(script in LISTOFSCRIPTS) {
                db.collection(script)
                    .get()
                    .addOnSuccessListener {result ->
                        for(document in result.documents) {
                            character = document.toObject(Character::class.java)!!
                            characterList.add(character)
                        }
                    }
            }

        }

        return characterList

    }

    companion object {
        val LISTOFSCRIPTS = listOf( "TroubleBrewing", "BadMoonRising", "SectsAndViolets")
    }
}