package msikora.task.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import msikora.task.data.GroceriesRepository
import msikora.task.domain.Item
import javax.inject.Inject

@HiltViewModel
class GroceriesViewModel
@Inject constructor(
    val groceriesRepository: GroceriesRepository,
) : ViewModel(
) {
    var collectionJob: Job? = null

    private val _items = MutableStateFlow(emptyList<Item>())
    val items: StateFlow<List<Item>> = _items

    private val _buttonState = MutableStateFlow(ButtonState.disabled())
    val buttonState: StateFlow<ButtonState> = _buttonState

    fun toggle() {
        _buttonState.value = _buttonState.value.toggle()
        when (_buttonState.value.enabled) {
            true -> {
                collectionJob = viewModelScope.launch {
                    groceriesRepository.start()
                    groceriesRepository.groceries.collect { groceries ->
                        _items.value = groceries
                    }
                }
            }
            false -> {
                collectionJob?.cancel()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        groceriesRepository.stop()
    }
}
