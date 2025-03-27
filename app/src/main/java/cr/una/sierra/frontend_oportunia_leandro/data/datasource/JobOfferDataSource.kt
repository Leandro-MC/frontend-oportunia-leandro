package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.JobOfferDto
import kotlinx.coroutines.flow.Flow

interface JobOfferDataSource {
    suspend fun getJobOffers(): Flow<List<JobOfferDto>>
    suspend fun getJobOfferById(id: Long): JobOfferDto?
    suspend fun insertJobOffer(jobOfferDto: JobOfferDto)
    suspend fun updateJobOffer(jobOfferDto: JobOfferDto)
    suspend fun deleteJobOffer(jobOfferDto: JobOfferDto)
}