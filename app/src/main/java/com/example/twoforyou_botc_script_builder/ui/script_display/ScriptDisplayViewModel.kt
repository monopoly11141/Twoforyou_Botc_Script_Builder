package com.example.twoforyou_botc_script_builder.ui.script_display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.domain.script_display.ScriptDisplayRepository
import com.example.twoforyou_botc_script_builder.ui.script_list.ScriptListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScriptDisplayViewModel @Inject constructor(
    private val repository: ScriptDisplayRepository
) : ViewModel() {

    private lateinit var script: Script

    private val _state = MutableStateFlow(ScriptListUiState())
    val state = combine(
        repository.displayingScript,
        _state
    ) { array ->
        ScriptListUiState(
            displayingScript = array[0] as Script,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    suspend fun getScriptById(id: Int): Script {

        script = repository.getScriptById(id)

        repository.updateScript(script)

        _state.update {
            it.copy(
                displayingScript = script
            )
        }

        return script
    }

}