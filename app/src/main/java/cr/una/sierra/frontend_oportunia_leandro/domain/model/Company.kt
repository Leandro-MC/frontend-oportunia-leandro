package cr.una.sierra.frontend_oportunia_leandro.domain.model

data class Company (
    val id: Long,
    val name: String,
    val website: String,
    val about: String,
    val mission: String,
    val vision: String,
    val infrastructure: String
)