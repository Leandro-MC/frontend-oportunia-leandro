package cr.una.sierra.frontend_oportunia_leandro.domain.model

import java.time.LocalDate

sealed class UserRegister(
    open val email: String,
    open val password: String,
    open val name: String,
    open val profileImage: String? = null
)

data class CompanyRegister(
    override val email: String,
    override val password: String,
    override val name: String,
    override val profileImage: String? = null,
    val fields: List<Field>,
) : UserRegister(email, password, name, profileImage)

data class ApplicantRegister(
    override val email: String,
    override val password: String,
    override val name: String,
    override val profileImage: String? = null,
    val lastName: String,
    val phone: String,
    val birthday: LocalDate?,
) : UserRegister(email, password, name, profileImage)