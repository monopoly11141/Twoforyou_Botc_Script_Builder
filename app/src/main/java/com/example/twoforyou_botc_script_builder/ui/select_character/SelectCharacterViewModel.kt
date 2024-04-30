package com.example.twoforyou_botc_script_builder.ui.select_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twoforyou_botc_script_builder.data.model.Character
import com.example.twoforyou_botc_script_builder.domain.select_character.SelectCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SelectCharacterViewModel @Inject constructor(
    private val repository: SelectCharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SelectCharacterUiState())
    val state = combine(
        repository.allCharacters,
        _state
    ){array ->
        SelectCharacterUiState(

            allCharacters = array[0] as List<Character>

        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)

}