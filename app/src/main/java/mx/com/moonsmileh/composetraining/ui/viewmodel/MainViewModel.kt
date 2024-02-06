package mx.com.moonsmileh.composetraining.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.moonsmileh.composetraining.data.network.DisneyRepository
import mx.com.moonsmileh.composetraining.ui.view.MainState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val disneyRepository: DisneyRepository) :
    ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            val charactersSaved = disneyRepository.getCharactersFromDB()
            if (charactersSaved.isEmpty()) {
                requestGetCharactersAPI()
            } else {
                state = state.copy(isLoading = false, characters = charactersSaved)
            }

        }
    }

    private suspend fun requestGetCharactersAPI() {
        disneyRepository.getCharactersFromAPI().onSuccess {
            disneyRepository.insertCharacterIntoDB(it)
        }.onFailure {
            state = state.copy(isLoading = false)
        }
    }

}