package msikora.task.utils

import androidx.compose.ui.graphics.Color

object Colors {

    val circleBorder = Color(136, 136, 136)
    val nameBackground = Color(180,180,180)
    val textsBorder = Color(221,221,221)
}

fun parseAsColor(fromBackend: String): Color {
    val colorInt = android.graphics.Color.parseColor(fromBackend);
    val r: Float = (colorInt shr 16 and 0xff) / 255.0f
    val g: Float = (colorInt shr 8 and 0xff) / 255.0f
    val b: Float = (colorInt and 0xff) / 255.0f
    val a: Float = (colorInt shr 24 and 0xff) / 255.0f
    return Color(r, g, b, a)
}
