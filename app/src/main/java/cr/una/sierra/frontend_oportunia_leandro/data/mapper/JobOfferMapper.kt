package cr.una.sierra.frontend_oportunia_leandro.data.mapper

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.JobOfferDto
import cr.una.sierra.frontend_oportunia_leandro.domain.model.JobOffer

class JobOfferMapper {
    /**
     * Convierte un JobOfferDto (capa de datos) a un JobOffer (capa de dominio)
     */
    fun mapToDomain(dto: JobOfferDto): JobOffer = JobOffer(
        id = dto.id,
        companyId = dto.companyId,
        companyName = dto.companyName,
        image = dto.image,
        country = dto.country,
        province = dto.province,
        title = dto.title,
        workMode = dto.workMode,
        description = dto.description,
        responsibilities = dto.responsibilities,
        requirements = dto.requirements,
        benefits = dto.benefits,
        postedDate = dto.postedDate
    )

    /**
     * Convierte un JobOffer (capa de dominio) a un JobOfferDto (capa de datos)
     */
    fun mapToDto(domain: JobOffer): JobOfferDto = JobOfferDto(
        id = domain.id,
        companyId = domain.companyId,
        companyName = domain.companyName,
        image = domain.image,
        country = domain.country,
        province = domain.province,
        title = domain.title,
        workMode = domain.workMode,
        description = domain.description,
        responsibilities = domain.responsibilities,
        requirements = domain.requirements,
        benefits = domain.benefits,
        postedDate = domain.postedDate
    )
}
