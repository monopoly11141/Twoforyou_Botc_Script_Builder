package com.example.twoforyou_botc_script_builder.data.model.helper

import androidx.room.ColumnInfo

data class Script_General_Info (
    @ColumnInfo(name = "script_general_info_id")
    val scriptGeneralInfoId: String = "",
    val author: String = "",
    val name: String = ""
)