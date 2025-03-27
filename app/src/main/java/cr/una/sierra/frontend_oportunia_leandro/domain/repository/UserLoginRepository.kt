package cr.una.sierra.frontend_oportunia_leandro.domain.repository

interface UserLoginRepository {
    suspend fun login(username: String, password: String): Result<Boolean>
    suspend fun logout()
}