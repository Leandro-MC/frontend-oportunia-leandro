package cr.una.sierra.frontend_oportunia_leandro.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.UserLoginRepository
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.LoginViewModel


class LoginViewModelFactory(
    private val userLoginRepository: UserLoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userLoginRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}