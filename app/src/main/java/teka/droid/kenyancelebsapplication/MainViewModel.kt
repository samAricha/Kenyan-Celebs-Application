package teka.droid.kenyancelebsapplication

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import teka.droid.kenyancelebsapplication.data.Celeb
import teka.droid.kenyancelebsapplication.data.CelebsApi
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api: CelebsApi)
    : ViewModel(){

    private val _state = mutableStateOf(CelebState())
    val state: State<CelebState> = _state

    init {
        getRandomCeleb()
    }

    fun getRandomCeleb(){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    celeb = api.getRandomCeleb(),
                    isLoading = false
                )

            }catch (e: java.lang.Exception){
                Log.e("MainViewModel", "getRandomCeleb: ", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class CelebState(
        val celeb: Celeb? = null,
        val isLoading: Boolean = false
    )

}
