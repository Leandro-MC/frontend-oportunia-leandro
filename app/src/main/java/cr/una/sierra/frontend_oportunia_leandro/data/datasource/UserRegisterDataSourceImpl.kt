package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.UserRegisterDto
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.providers.UserRegisterProvider
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.UserRegisterMapper

class UserRegisterDataSourceImpl(
    private val userRegisterMapper: UserRegisterMapper,
) : UserRegisterDataSource {

    override suspend fun getUserRegisterByEmail(email: String): UserRegisterDto? =
        UserRegisterProvider.findUserRegisterByEmail(email)?.let { userRegisterMapper.mapToDto(it) }

    override suspend fun insertUserRegister(userRegisterDto: UserRegisterDto) {
        UserRegisterProvider.addUserRegister(userRegisterMapper.mapToDomain(userRegisterDto))
    }

}