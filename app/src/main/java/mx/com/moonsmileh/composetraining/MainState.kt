package mx.com.moonsmileh.composetraining

import mx.com.moonsmileh.composetraining.model.DisneyCharacter

data class MainState(
    val isLoading: Boolean = false,
    val characters: List<DisneyCharacter> = emptyList()
)