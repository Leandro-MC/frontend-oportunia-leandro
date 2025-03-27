package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.UserRegisterDto

interface UserRegisterDataSource {
    suspend fun getUserRegisterByEmail(email: String): UserRegisterDto?
    suspend fun insertUserRegister(userRegisterDto: UserRegisterDto)
}