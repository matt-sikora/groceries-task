package msikora.task.data

import kotlinx.serialization.Serializable
import msikora.task.domain.Item

@Serializable
data class ItemDto(
    val bagColor: String? = null,
    val name: String? = null,
    val weight: String? = null,
) {

    fun toDomain(): Item? {
        return Item(
            bagColor = bagColor
                ?.takeIf { Regex("^#(?:[0-9a-fA-F]{3,4}){1,2}\$").matches(it) }
                ?: return null,
            name = name.takeIf { !it.isNullOrBlank() } ?: return null,
            weight = weight.takeIf { !it.isNullOrBlank() } ?: return null,
        )
    }
}
