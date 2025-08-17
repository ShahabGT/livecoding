package ir.shahabazimi.livecoding.presentation.register

data class RegisterState(
    val isLoading: Boolean = false,
    val registerSuccess: Boolean = false,
    val error: String? = null
)