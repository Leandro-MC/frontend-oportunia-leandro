package cr.una.sierra.frontend_oportunia_leandro.data.datasource.providers

import cr.una.sierra.frontend_oportunia_leandro.domain.model.JobOffer
import java.util.Date
import kotlin.collections.find

class JobOfferProvider {
    companion object {// funciones y variables dentro de él son estáticas, es decir, se pueden usar sin necesidad de crear un objeto de TaskProvider
        private val jobOfferList = listOf(
            JobOffer(
                id = 1,
                companyId = 1001,
                companyName = "Google",
                image = null,
                country = "United States",
                province = "California",
                title = "Software Engineer",
                workMode = "Remote",
                description = "Develop and maintain applications.",
                responsibilities = "Write clean code, collaborate with teams.",
                requirements = "Experience in Kotlin, Java.",
                benefits = "Health insurance, remote work.",
                postedDate = Date()
            ),
            JobOffer(
                id = 2,
                companyId = 1002,
                companyName = "United States",
                image = null,
                country = "United States",
                province = "Washington",
                title = "Cloud Engineer",
                workMode = "Hybrid",
                description = "Work on cloud solutions.",
                responsibilities = "Develop cloud-based applications.",
                requirements = "Experience in Azure, Kubernetes.",
                benefits = "Flexible hours, health insurance.",
                postedDate = Date()
            )
        )

        /**
         * Finds a job offer by its ID.
         * @param id The ID of the job offer to find.
         * @return The job offer with the given ID, or null if not found.
         */
        fun findJobOfferById(id: Long): JobOffer? {
            return jobOfferList.find { it.id == id }
        }

        /**
         * Finds all job offers.
         * @return A list of all job offers.
         */
        fun findAllJobOffers(): List<JobOffer> {
            return jobOfferList
        }
    }
}