package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cr.una.sierra.frontend_oportunia_leandro.domain.model.ApplicantRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.model.CompanyRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.UserRegisterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Enum to differentiate user types
enum class UserType {
    COMPANY, APPLICANT
}

// Sealed class for registration state
sealed class RegistrationState {
    data object Idle : RegistrationState()
    data object Loading : RegistrationState()
    data object Success : RegistrationState()
    data class Error(val message: String) : RegistrationState()
}

// Validation function
fun validateRegistration(
    email: String,
    password: String,
    confirmPassword: String,
    name: String,
    userType: UserType,
    additionalFields: Map<String, String>
): Result<Unit> = runCatching {
    require(email.isNotBlank() && email.contains("@")) { "Correo electrónico inválido" }
    require(password.length >= 8) { "La contraseña debe tener al menos 8 caracteres" }
    require(password == confirmPassword) { "Las contraseñas no coinciden" }
    require(name.isNotBlank()) { "El nombre es obligatorio" }

    when (userType) {
        UserType.COMPANY -> {
            require(additionalFields["sector"]?.isNotBlank() == true) { "El sector es obligatorio" }
        }
        UserType.APPLICANT -> {
            require(additionalFields["lastName"]?.isNotBlank() == true) { "Los apellidos son obligatorios" }
            require(additionalFields["phone"]?.isNotBlank() == true) { "El teléfono es obligatorio" }
            require(additionalFields["birthday"]?.isNotBlank() == true) { "La fecha de nacimiento es obligatoria" }
        }
    }
}

// ViewModel for Registration
class RegistrationViewModel(
    private val userRegisterRepository: UserRegisterRepository
) : ViewModel() {
    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun registerCompany(
        companyRegister: CompanyRegister
    ) {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            try {
                userRegisterRepository.insertUserRegister(companyRegister)
                _registrationState.value = RegistrationState.Success
            } catch (e: Exception) {
                _registrationState.value = RegistrationState.Error(
                    e.message ?: "Error al registrar la empresa"
                )
            }
        }
    }

    fun registerApplicant(
        applicantRegister: ApplicantRegister
    ) {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Loading
            try {
                userRegisterRepository.insertUserRegister(applicantRegister)
                _registrationState.value = RegistrationState.Success
            } catch (e: Exception) {
                _registrationState.value = RegistrationState.Error(
                    e.message ?: "Error al registrar el postulante"
                )
            }
        }
    }

    fun registerLogout() {
        _registrationState.value = RegistrationState.Idle
    }
}