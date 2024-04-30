package com.example.twoforyou_botc_script_builder.data.db.remote

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.data.model.helper.Character_Type
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirebaseCharacterDatabase {
    val db = Firebase.firestore
    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    val characterList = _characterList.asStateFlow()
    private lateinit var character: Character

    init {
        getAllCharacters()
    }
    private fun getAllCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
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
    }

    companion object {
        val LISTOFSCRIPTS = listOf("TroubleBrewing", "BadMoonRising", "SectsAndViolets")
    }
}