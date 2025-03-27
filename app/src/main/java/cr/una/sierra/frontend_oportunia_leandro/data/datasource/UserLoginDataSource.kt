package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.UserLoginDto

interface UserLoginDataSource {
    suspend fun loginUser(userLoginDto: UserLoginDto): Boolean         // revisar si debe retornar un UserLoginDto
    suspend fun logoutUser()

}