package msikora.task.data

import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideScarlet() = Scarlet.Builder()
        .webSocketFactory(OkHttpClient().newWebSocketFactory("wss://superdo-groceries.herokuapp.com/receive"))
        .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideGroceriesService(scarlet: Scarlet) = scarlet.create(GroceriesService::class.java)

    @Provides
    @Singleton
    fun provideJson() = Json.Default
}
