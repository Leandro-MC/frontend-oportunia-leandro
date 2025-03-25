package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.CompanyDto
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.CompanyMapper

class CompanyDataSourceImpl (
    private val companyMapper: CompanyMapper
) : CompanyDataSource {

    override suspend fun getCompanyById(id: Long): CompanyDto? =
        CompanyProvider.findCompanyById(id)?.let { companyMapper.mapToDto(it) }


    override suspend fun insertCompany(company: CompanyDto) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCompany(company: CompanyDto) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCompany(company: CompanyDto) {
        TODO("Not yet implemented")
    }


}