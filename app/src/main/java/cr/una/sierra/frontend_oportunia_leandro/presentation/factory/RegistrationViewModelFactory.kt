package cr.una.sierra.frontend_oportunia_leandro.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.UserRegisterRepository
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.RegistrationViewModel

class RegistrationViewModelFactory(
    private val userRegisterRepository: UserRegisterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegistrationViewModel(userRegisterRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}