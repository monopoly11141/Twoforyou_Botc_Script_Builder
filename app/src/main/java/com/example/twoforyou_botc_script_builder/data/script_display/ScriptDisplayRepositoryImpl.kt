package com.example.twoforyou_botc_script_builder.data.script_display

import com.example.twoforyou_botc_script_builder.data.db.local.ScriptDao
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.domain.script_display.ScriptDisplayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ScriptDisplayRepositoryImpl  @Inject constructor(
    private val scriptDao: ScriptDao
) : ScriptDisplayRepository{
    private val _displayingScript = MutableStateFlow(Script())
    override val displayingScript: StateFlow<Script>
        get() = _displayingScript.asStateFlow()

    override suspend fun getScriptById(id: Int): Script {
        return scriptDao.getScriptById(id)
    }

    override fun updateScript(script: Script) {
        _displayingScript.value = script
    }

}