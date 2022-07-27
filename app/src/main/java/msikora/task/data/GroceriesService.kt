package msikora.task.data

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import kotlinx.coroutines.channels.ReceiveChannel

interface GroceriesService {

    @Receive
    fun observeWebSocketEvent(): ReceiveChannel<WebSocket.Event>
}
