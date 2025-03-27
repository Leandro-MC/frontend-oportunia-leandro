package cr.una.sierra.frontend_oportunia_leandro.data.repository

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.UserLoginDataSource
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.UserLoginDto
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.UserLoginMapper
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.UserLoginRepository


class UserLoginRepositoryImpl(
    private val userLoginDataSource: UserLoginDataSource,
    private val userLoginMapper: UserLoginMapper
) : UserLoginRepository {

    companion object {
        private const val TAG = "UserLoginRepository"
    }

    override suspend fun login(username: String, password: String): Result<Boolean> = runCatching {
        val userLoginDto = UserLoginDto(username, password)
        userLoginDataSource.loginUser(userLoginDto)
    }.recoverCatching { throwable ->
        throwable.printStackTrace()
        throw Exception("Error logging in", throwable)
    }

    override suspend fun logout() {
        runCatching {
            userLoginDataSource.logoutUser()
        }.recoverCatching { throwable ->
            throwable.printStackTrace()
            throw Exception("Error logging out", throwable)
        }
    }
}
