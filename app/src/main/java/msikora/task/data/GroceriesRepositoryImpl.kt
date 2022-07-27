package msikora.task.data

import com.tinder.scarlet.Message
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import msikora.task.domain.Item
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroceriesRepositoryImpl
@Inject constructor(
    val groceriesService: GroceriesService,
) : GroceriesRepository {

    private var listeningJob: Job? = null
    private val _groceries = MutableStateFlow<List<Item>>(emptyList())
    override val groceries = _groceries

    override fun start() {
        if (listeningJob == null) {
            listeningJob = CoroutineScope(Dispatchers.IO).launch {
                groceriesService.observeWebSocketEvent()
                    .consumeAsFlow()
                    .filterIsInstance<WebSocket.Event.OnMessageReceived>()
                    .mapNotNull { it.toItemDto(Json.Default)?.toDomain() }
                    .collect {
                        _groceries.value = _groceries.value + it
                    }
            }
        }
    }

    override fun stop() {
        listeningJob?.cancel()
    }
}

private fun WebSocket.Event.OnMessageReceived.toItemDto(json: Json): ItemDto? {
    return (message as? Message.Text)?.let { json.decodeFromString(it.value) }
}
