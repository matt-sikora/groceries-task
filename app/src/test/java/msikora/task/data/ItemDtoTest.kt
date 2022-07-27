package msikora.task.data

import org.junit.Assert.*
import org.junit.Test

class ItemDtoTest {

    @Test
    fun testMapping() {
        val empty = ItemDto()
        assertNull(empty.toDomain())
    }
}
