package msikora.task.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import msikora.task.domain.Item
import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GroceriesRepositoryImplTest {

    @Test
    @Ignore
    fun testCollecting() = runTest {
        val repo = GroceriesRepositoryImpl(
            groceriesService = NetworkModule.provideGroceriesService(NetworkModule.provideScarlet()),
        )
        repo.start()
        val collectJob = launch(UnconfinedTestDispatcher()) {
            repo.groceries
                .map { println("$it") }
                .collect()
        }

        Thread.sleep(5000)
        collectJob.cancel()
    }
}
