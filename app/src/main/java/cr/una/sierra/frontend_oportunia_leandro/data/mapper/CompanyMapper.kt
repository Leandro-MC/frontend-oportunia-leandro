package cr.una.sierra.frontend_oportunia_leandro.data.mapper

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.CompanyDto
import cr.una.sierra.frontend_oportunia_leandro.domain.model.Company

class CompanyMapper(){

    /**
     * Maps a [CompanyDto] to a [Company]
     * @param dto The [CompanyDto] to map
     * @return The mapped [Company]
     */

    fun mapToDomain(dto: CompanyDto): Company = Company(
        id = dto.id,
        name = dto.name,
        website = dto.website,
        about = dto.about,
        mission = dto.mission,
        vision = dto.vision,
        infrastructure = dto.infrastructure
    )

    /**
     * Maps a [Company] to a [CompanyDto]
     * @param company The [Company] to map
     * @return The mapped [CompanyDto]
     */

    fun mapToDto(company: Company): CompanyDto = CompanyDto(
        id = company.id,
        name = company.name,
        website = company.website,
        about = company.about,
        mission = company.mission,
        vision = company.vision,
        infrastructure = company.infrastructure
    )

}