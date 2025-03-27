package cr.una.sierra.frontend_oportunia_leandro.data.mapper

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.UserLoginDto
import cr.una.sierra.frontend_oportunia_leandro.domain.model.UserLogin


class UserLoginMapper {
    fun mapToDomain(dto: UserLoginDto): UserLogin {
        return UserLogin(
            dto.email,
            dto.password
        )
    }

    fun mapToDto(userLogin: UserLogin): UserLoginDto {
        return UserLoginDto(
            userLogin.email,
            userLogin.password
        )
    }
}