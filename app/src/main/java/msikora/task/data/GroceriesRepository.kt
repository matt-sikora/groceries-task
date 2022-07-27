package msikora.task.data

import kotlinx.coroutines.flow.Flow
import msikora.task.domain.Item

interface GroceriesRepository {

    val groceries: Flow<List<Item>>

    fun start()

    fun stop()
}
