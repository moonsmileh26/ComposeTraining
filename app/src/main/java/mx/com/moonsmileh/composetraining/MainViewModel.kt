package mx.com.moonsmileh.composetraining

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.moonsmileh.composetraining.data.DisneyRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val disneyRepository: DisneyRepository) :
    ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init {

        viewModelScope.launch {
            state.copy(isLoading = true)
            disneyRepository.getCharacters().onSuccess {
                state.copy(characters = it)
            }.onFailure {

            }
            state.copy(isLoading = false)

        }
    }


}