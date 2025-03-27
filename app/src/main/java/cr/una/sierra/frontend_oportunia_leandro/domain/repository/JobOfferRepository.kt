package cr.una.sierra.frontend_oportunia_leandro.domain.repository

import cr.una.sierra.frontend_oportunia_leandro.domain.model.JobOffer

interface JobOfferRepository {
    suspend fun findAllJobOffers(): Result<List<JobOffer>>
    suspend fun findJobOfferById(jobOfferId: Long): Result<JobOffer>
}