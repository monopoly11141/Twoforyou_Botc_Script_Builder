package com.example.twoforyou_botc_script_builder.data.model

data class FabledCharacter(
    var name: String = "",
    var ability: String = "",
    var imageUrl : String = ""
)


fun FabledCharacter.getEnglishName() : String {
    return this.name.filter {
        "^[A-Za-z]*$".toRegex().containsMatchIn(it.toString())
    }.replace("_", " ").trim()
}

fun FabledCharacter.getKoreanName() : String {
    return this.name.filter {
        "^[ㄱ-ㅎ가-힣]*$".toRegex().containsMatchIn(it.toString())
    }.replace("_", " ").trim()
}
