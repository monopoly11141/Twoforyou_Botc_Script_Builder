package com.example.twoforyou_botc_script_builder.ui.script_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoforyou_botc_script_builder.data.model.Script
import com.example.twoforyou_botc_script_builder.domain.script_list.ScriptListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScriptListViewModel @Inject constructor(
    private val repository: ScriptListRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ScriptListUiState())
    val state = combine(
        repository.displayingScript,
        repository.scriptList,
        _state
    ) { array ->
        ScriptListUiState(
            displayingScript = array[0] as Script,
            scriptList = array[1] as List<Script>
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

    fun jsonStringToString(jsonString: String): Script {
        val script = repository.jsonStringToScript(jsonString)
        _state.update {
            it.copy(
                displayingScript = script
            )
        }
        return script

    }

}