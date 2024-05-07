package com.example.twoforyou_botc_script_builder.data.model

import com.example.twoforyou_botc_script_builder.data.model.helper.Character_Type

data class Character(
    var name: String = "",
    var characterType: Character_Type = Character_Type.마을주민_TOWNSFOLK,
    var ability: String = "",
    var isFormatChangingRole: Boolean = false,
    var imageUrl : String = ""
)

fun Character.getEnglishName() : String {
    return this.name.filter {
        "^[A-Za-z]*$".toRegex().containsMatchIn(it.toString())
    }
}

fun Character.getKoreanName() : String {
    return this.name.filter {
        "^[ㄱ-ㅎ가-힣]*$".toRegex().containsMatchIn(it.toString())
    }.replace("_", " ")
}
