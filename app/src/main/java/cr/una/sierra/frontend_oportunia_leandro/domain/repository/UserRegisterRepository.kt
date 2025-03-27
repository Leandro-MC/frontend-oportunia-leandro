package cr.una.sierra.frontend_oportunia_leandro.domain.repository

import cr.una.sierra.frontend_oportunia_leandro.domain.model.UserRegister

interface UserRegisterRepository {
    suspend fun getUserRegisterByEmail(email: String): Result<UserRegister>
    suspend fun insertUserRegister(userRegister: UserRegister)
}