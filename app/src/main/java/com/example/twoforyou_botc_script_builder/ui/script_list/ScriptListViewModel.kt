package com.example.twoforyou_botc_script_builder.ui.script_list

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.data.model.helper.Script_General_Info
import com.example.twoforyou_botc_script_builder.domain.script_list.ScriptListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScriptListViewModel @Inject constructor(
    private val repository: ScriptListRepository
) : ViewModel() {

    private lateinit var script: Script

    private val _state = MutableStateFlow(ScriptListUiState())
    val state = combine(
        repository.displayingScript,
        repository.getAllScript(),
        _state
    ) { array ->
        ScriptListUiState(
            displayingScript = array[0] as Script,
            scriptList = array[1] as List<Script>
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    fun jsonStringToScriptFromScriptWebsite(jsonString: String): Script {
        script = repository.jsonStringToScriptFromScriptWebsite(jsonString)
        repository.updateScript(script)

        _state.update {
            it.copy(
                displayingScript = script
            )
        }
        Log.d(TAG, "jsonStringToScriptFromScriptWebsite: Script : ${script}")
        return script

    }

    fun jsonStringToScriptFromAzureWebsite(jsonString: String, scriptGeneralInfoId : String = "_meta", author : String = "", name: String): Script {
        script = repository.jsonStringToScriptFromAzureWebsite(jsonString, Script_General_Info(scriptGeneralInfoId, author, name))
        repository.updateScript(script)

        _state.update {
            it.copy(
                displayingScript = script
            )
        }

        return script

    }

    fun addScript(script: Script) {
        viewModelScope.launch {
            repository.insertScript(script)
        }
    }

    fun deleteScript(script: Script) {
        viewModelScope.launch {
            repository.deleteScript(script)
        }
    }

    fun deleteAllScript() {
        viewModelScope.launch {
            repository.deleteAllScript()
        }
    }

    fun getCharacterByName(name: String) : Character? {
        return repository.getCharacterByName(name)
    }

}