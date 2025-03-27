package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.UserLoginDto
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.providers.UserRegisterProvider
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.UserLoginMapper

class UserLoginDataSourceImpl (
    private val userLoginMapper: UserLoginMapper
) : UserLoginDataSource {

    override suspend fun loginUser(userLoginDto: UserLoginDto): Boolean {
        return UserRegisterProvider.loginUser(userLoginMapper.mapToDomain(userLoginDto))
    }

    override suspend fun logoutUser() {
        TODO("Not yet implemented")
    }
}