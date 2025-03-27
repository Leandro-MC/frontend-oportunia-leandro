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




////@HiltViewModel
//class LoginViewModel( //@Inject constructor(
//    private val loginRepository: UserLoginRepository, // Inject the login repository
////    private val tokenManager: TokenManager // Inject the TokenManager
//) : ViewModel() {
//
//    // StateFlow to hold the login status
//    private val _isLoggedIn = MutableStateFlow(false)
//    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn
//
//    // StateFlow to hold the error message (for login failures)
//    private val _loginError = MutableStateFlow<String?>(null)
//    val loginError: StateFlow<String?> get() = _loginError
//
//    /**
//     * Function to handle login logic using the LoginRepository.
//     */
//    fun login(username: String, password: String) {
//        viewModelScope.launch {
//            val result = loginRepository.login(username, password)
//
//            if(result == true){
//                _isLoggedIn.value = true
//                _loginError.value = null
//            }else{
//                _isLoggedIn.value = false
//                _loginError.value = "Login failed. Please check your credentials."
//            }
////            =====================================================
////            val result = loginRepository.login(username, password)
////            result.onSuccess { (loginResponse, token) ->
////                if (loginResponse != null && token != null) {
////                    // Store the JWT token via the TokenManager after successful login
////                    tokenManager.saveToken(token)
////
////                    _isLoggedIn.value = true
////                    _loginError.value = null
////                } else {
////                    _isLoggedIn.value = false
////                    _loginError.value = "Login failed. Please check your credentials."
////                }
////            }.onFailure { exception ->
////                _isLoggedIn.value = false
////                _loginError.value = "An error occurred: ${exception.message}"
////            }
//        }
//    }
//
//    /**
//     * Function to handle logout logic.
//     */
//    fun logout() {
////        tokenManager.clearToken() // Clear the token via TokenManager
////        _isLoggedIn.value = false
//    }
//}