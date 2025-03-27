package cr.una.sierra.frontend_oportunia_leandro.data.mapper

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.ApplicantRegisterDto
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.CompanyRegisterDto
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.FieldDto
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.UserRegisterDto
import cr.una.sierra.frontend_oportunia_leandro.domain.model.ApplicantRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.model.CompanyRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.model.Field
import cr.una.sierra.frontend_oportunia_leandro.domain.model.UserRegister
import kotlin.collections.map

class UserRegisterMapper {

    fun mapToDomain(dto: UserRegisterDto): UserRegister {
        return when (dto) {
            is CompanyRegisterDto -> CompanyRegister(
                email = dto.email,
                password = dto.password,
                name = dto.name,
                profileImage = dto.profileImage,
                fields = dto.fields.map { Field(it.id, it.name) }
            )
            is ApplicantRegisterDto -> ApplicantRegister(
                email = dto.email,
                password = dto.password,
                name = dto.name,
                profileImage = dto.profileImage,
                lastName = dto.lastName,
                phone = dto.phone,
                birthday = dto.birthday
            )
        }
    }

    fun mapToDto(domain: UserRegister): UserRegisterDto {
        return when (domain) {
            is CompanyRegister -> CompanyRegisterDto(
                email = domain.email,
                password = domain.password,
                name = domain.name,
                profileImage = domain.profileImage,
                fields = domain.fields.map { FieldDto(it.id, it.name) }
            )
            is ApplicantRegister -> ApplicantRegisterDto(
                email = domain.email,
                password = domain.password,
                name = domain.name,
                profileImage = domain.profileImage,
                lastName = domain.lastName,
                phone = domain.phone,
                birthday = domain.birthday
            )
        }
    }
}