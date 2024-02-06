package mx.com.moonsmileh.composetraining.ui.view

import mx.com.moonsmileh.composetraining.data.model.DisneyCharacter

data class MainState(
    val isLoading: Boolean = false,
    val characters: List<DisneyCharacter> = emptyList()
)