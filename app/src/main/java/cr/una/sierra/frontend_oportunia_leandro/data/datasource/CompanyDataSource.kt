package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.CompanyDto

interface CompanyDataSource {
    suspend fun getCompanyById(id: Long): CompanyDto?
    suspend fun insertCompany(company: CompanyDto)
    suspend fun updateCompany(company: CompanyDto)
    suspend fun deleteCompany(company: CompanyDto)
}