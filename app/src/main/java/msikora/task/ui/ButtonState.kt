package msikora.task.ui

data class ButtonState(
    val enabled: Boolean,
) {
    fun toggle() = copy(enabled = !enabled)

    companion object {
        fun disabled() = ButtonState(enabled = false)
        fun enabled() = ButtonState(enabled = true)
    }
}
