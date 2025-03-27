package cr.una.sierra.frontend_oportunia_leandro.data.datasource.model

import java.time.LocalDate

sealed class UserRegisterDto(
    open val email: String,
    open val password: String,
    open val name: String,
    open val profileImage: String? = null
)

data class CompanyRegisterDto(
    override val email: String,
    override val password: String,
    override val name: String,
    override val profileImage: String? = null,
    val fields: List<FieldDto>,
) : UserRegisterDto(email, password, name, profileImage)

data class ApplicantRegisterDto(
    override val email: String,
    override val password: String,
    override val name: String,
    override val profileImage: String? = null,
    val lastName: String,
    val phone: String,
    val birthday: LocalDate?,
) : UserRegisterDto(email, password, name, profileImage)
