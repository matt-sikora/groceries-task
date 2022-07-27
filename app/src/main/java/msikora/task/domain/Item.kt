package msikora.task.domain

import msikora.task.utils.parseAsColor

data class Item(
    val bagColor: String,
    val name: String,
    val weight: String,
) {

    val composeColor: androidx.compose.ui.graphics.Color by lazy {
        parseAsColor(bagColor)
    }
}
