package cr.una.sierra.frontend_oportunia_leandro.data.datasource

import cr.una.sierra.frontend_oportunia_leandro.domain.model.Company

class CompanyProvider {
    companion object {
        private val companies = mutableListOf(
            Company(
                id = 1,
                name = "Tech Innovators",
                website = "https://www.techinnovators.com",
                about = "Empresa líder en el desarrollo de software basado en inteligencia artificial y soluciones en la nube.",
                mission = "Proveer herramientas tecnológicas innovadoras que transformen industrias y mejoren la eficiencia operativa.",
                vision = "Ser la empresa referente en soluciones de software con inteligencia artificial a nivel global.",
                infrastructure = "Centros de datos en EE.UU., Europa y Asia, con una red segura y escalable."
            ),
            Company(
                id = 2,
                name = "EcoEnergy Solutions",
                website = "https://www.ecoenergy.com",
                about = "Especialistas en energías renovables, desarrollamos soluciones sostenibles para un futuro más verde.",
                mission = "Reducir la huella de carbono global proporcionando energía limpia y accesible.",
                vision = "Un mundo donde la energía renovable sea la principal fuente de electricidad.",
                infrastructure = "Plantas solares y eólicas en más de 15 países, con tecnología de almacenamiento de energía avanzada."
            ),
            Company(
                id = 3,
                name = "Global Logistics",
                website = "https://www.globallogistics.com",
                about = "Líderes en transporte y logística internacional, optimizando cadenas de suministro para empresas de todos los tamaños.",
                mission = "Facilitar el comercio global con soluciones logísticas eficientes y sostenibles.",
                vision = "Ser la compañía de logística más confiable y tecnológicamente avanzada del mundo.",
                infrastructure = "Red de distribución con almacenes automatizados y flotas de vehículos eléctricos en múltiples continentes."
            )
        )

        fun findCompanyById(id: Long): Company? {
            return companies.find { it.id == id }
        }

        fun getCompanies(): List<Company> {
            return companies
        }

        fun addCompany(company: Company) {
            companies.add(company)
        }

        fun deleteCompany(company: Company) {
            companies.remove(company)
        }
    }
}