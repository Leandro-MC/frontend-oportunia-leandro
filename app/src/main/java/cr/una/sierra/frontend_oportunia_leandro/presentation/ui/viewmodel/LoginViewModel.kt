package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.UserLoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch



sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(
    private val userLoginRepository: UserLoginRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            userLoginRepository.login(email, password)
                .fold(
                    onSuccess = { success ->
                        _loginState.value = if (success) {
                            LoginState.Success
                        } else {
                            LoginState.Error("Credenciales inválidas")
                        }
                    },
                    onFailure = { exception ->
                        _loginState.value = LoginState.Error(
                            exception.message ?: "Error al iniciar sesión"
                        )
                    }
                )
        }
    }

    fun logout() {
        _loginState.value = LoginState.Idle
    }
}