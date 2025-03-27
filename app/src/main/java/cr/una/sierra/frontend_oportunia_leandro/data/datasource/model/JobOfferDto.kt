package cr.una.sierra.frontend_oportunia_leandro.data.datasource.model

import java.util.Date

data class JobOfferDto(
    val companyId: Long,
    val companyName: String,
    val image: ByteArray?,// puede que sea null por eso el ?

    val id: Long,
    val country: String,
    val province: String,
    val title: String,
    val workMode: String,
    val description: String,
    val responsibilities: String,
    val requirements: String,
    val benefits: String,
    val postedDate: Date,
)