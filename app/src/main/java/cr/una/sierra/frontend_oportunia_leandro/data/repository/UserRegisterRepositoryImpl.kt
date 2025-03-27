package cr.una.sierra.frontend_oportunia_leandro.data.repository

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.UserRegisterDataSource
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.UserRegisterMapper
import cr.una.sierra.frontend_oportunia_leandro.domain.model.UserRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.UserRegisterRepository

class UserRegisterRepositoryImpl(
    private val userRegisterDataSource: UserRegisterDataSource,
    private val userRegisterMapper: UserRegisterMapper
) : UserRegisterRepository {

    companion object {
        private const val TAG = "UserRegisterRepository"
    }

    override suspend fun getUserRegisterByEmail(email: String): Result<UserRegister> = runCatching {
        val userRegisterDto = userRegisterDataSource.getUserRegisterByEmail(email) ?: throw Exception("User not found")
        userRegisterMapper.mapToDomain(userRegisterDto)
    }.recoverCatching { throwable ->
        throwable.printStackTrace()
        throw Exception("Error getting user register by email", throwable)
    }

    override suspend fun insertUserRegister(userRegister: UserRegister) {
        runCatching {
            val userRegisterDto = userRegisterMapper.mapToDto(userRegister)
            userRegisterDataSource.insertUserRegister(userRegisterDto)
        }.recoverCatching { throwable ->
            throwable.printStackTrace()
            throw Exception("Error inserting user register", throwable)
        }
    }
}