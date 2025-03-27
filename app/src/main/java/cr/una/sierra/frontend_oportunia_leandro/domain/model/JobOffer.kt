package cr.una.sierra.frontend_oportunia_leandro.domain.model

import java.util.Date

data class JobOffer(
    val id: Long,
    val companyId: Long,
    val companyName: String,
    val image: ByteArray?, // Puede ser nulo
    val country: String,
    val province: String,
    val title: String,
    val workMode: String,
    val description: String,
    val responsibilities: String,
    val requirements: String,
    val benefits: String,
    val postedDate: Date
)