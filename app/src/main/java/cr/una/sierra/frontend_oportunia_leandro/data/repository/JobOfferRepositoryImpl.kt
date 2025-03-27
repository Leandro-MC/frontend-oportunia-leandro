package cr.una.sierra.frontend_oportunia_leandro.data.repository

import android.util.Log
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.JobOfferDataSource
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.JobOfferMapper
import cr.una.sierra.frontend_oportunia_leandro.domain.model.JobOffer
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.JobOfferRepository
import kotlinx.coroutines.flow.first
import java.io.IOException

class JobOfferRepositoryImpl(
    private val dataSource: JobOfferDataSource,
    private val jobOfferMapper: JobOfferMapper
) : JobOfferRepository {

    /**
     * Companion object for the repository.
     * Contains a tag for logging purposes.
     */
    companion object {
        private const val TAG = "JobOfferRepository"
    }

    /**
     * Retrieves all job offers from the data source.
     *
     * @return [Result] containing a list of job offers if successful, or an error if the operation failed
     */
    override suspend fun findAllJobOffers(): Result<List<JobOffer>> = runCatching {
        dataSource.getJobOffers().first().map { jobOfferDto ->
            jobOfferMapper.mapToDomain(jobOfferDto)
        }
    }.recoverCatching { throwable ->

        // Log the error before throwing
        Log.e(TAG, "Failed to  fetch job offers", throwable)

        // Comentado el manejo de errores por ahora
        /*
        when (throwable) {
            is IOException -> throw DomainError.NetworkError("Failed to fetch job offers")
            is IllegalArgumentException -> throw DomainError.MappingError("Error mapping job offers")
            is DomainError -> throw throwable
            else -> throw DomainError.UnknownError
        }
        */
        // Solo devuelve un error genérico por ahora
        throw Exception("Unknown error")
    }

    /**
     * Finds a job offer by its ID.
     *
     * @param jobOfferId The ID of the job offer to find
     * @return [Result] containing the job offer if found, or an error if the operation failed
     */
    override suspend fun findJobOfferById(jobOfferID: Long): Result<JobOffer> = runCatching {
        val jobOfferDto =
            dataSource.getJobOfferById(jobOfferID) ?: throw Exception("Job offer not found")
        jobOfferMapper.mapToDomain(jobOfferDto)
    }.recoverCatching { throwable ->

        // Log the error before throwing
        Log.e(TAG, "Failed to fetch job offer with ID: $jobOfferID", throwable)

        // Comentado el manejo de errores por ahora
        /*
        when (throwable) {
            is IOException -> throw DomainError.NetworkError("Failed to fetch job offer")
            is IllegalArgumentException -> throw DomainError.MappingError("Error mapping job offer")
            is DomainError -> throw throwable
            else -> throw DomainError.UnknownError
        }
        */
        // Solo devuelve un error genérico por ahora
        throw Exception("Unknown error")
    }
}