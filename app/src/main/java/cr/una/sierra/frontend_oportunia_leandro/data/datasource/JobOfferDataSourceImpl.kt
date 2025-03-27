package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.JobOfferDto
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.providers.JobOfferProvider
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.JobOfferMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JobOfferDataSourceImpl(
    private val jobOfferMapper: JobOfferMapper
): JobOfferDataSource{
    override suspend fun getJobOffers(): Flow<List<JobOfferDto>> = flow {
        val tasks = JobOfferProvider.findAllJobOffers()
        emit(tasks.map { jobOfferMapper.mapToDto(it) })
    }

    override suspend fun getJobOfferById(id: Long): JobOfferDto? {
        TODO("Not yet implemented")
    }

    override suspend fun insertJobOffer(jobOfferDto: JobOfferDto) {
        TODO("Not yet implemented")
    }

    override suspend fun updateJobOffer(jobOfferDto: JobOfferDto) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteJobOffer(jobOfferDto: JobOfferDto) {
        TODO("Not yet implemented")
    }

}