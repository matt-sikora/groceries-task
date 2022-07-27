package msikora.task.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import msikora.task.domain.Item

class TestGroceriesRepository : GroceriesRepository {

    private var started = false
    val exposedGroceries: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())

    override val groceries: Flow<List<Item>>
        get() = exposedGroceries

    override fun start() {
        started = true
    }

    override fun stop() {
        started = false
    }
}
