package cr.una.sierra.frontend_oportunia_leandro.data.datasource.providers

import cr.una.sierra.frontend_oportunia_leandro.domain.model.ApplicantRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.model.CompanyRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.model.Field
import cr.una.sierra.frontend_oportunia_leandro.domain.model.UserLogin
import cr.una.sierra.frontend_oportunia_leandro.domain.model.UserRegister
import java.time.LocalDate

class UserRegisterProvider {
    companion object {
        private val users = mutableListOf(
            CompanyRegister(
                email = "contacto@techsolutions.com",
                password = "SecurePass123",
                name = "Tech Solutions",
                profileImage = "https://example.com/images/techsolutions.png",
                fields = listOf(
                    Field(1, "Tecnología"),
                    Field(2, "Consultoría")
                )
            ),
            CompanyRegister(
                email = "info@greenenergycr.com",
                password = "EcoPower2025",
                name = "Green Energy CR",
                profileImage = "https://example.com/images/greenenergy.png",
                fields = listOf(
                    Field(3, "Energías Renovables"),
                    Field(4, "Sostenibilidad")
                )
            ),
            CompanyRegister(
                email = "contacto@finanzas360.com",
                password = "Finance2025!",
                name = "Finanzas 360",
                profileImage = "https://example.com/images/finanzas360.jpg",
                fields = listOf(
                    Field(5, "Finanzas"),
                    Field(6, "Inversiones")
                )
            ),
            ApplicantRegister(
                email = "juan.perez@gmail.com",
                password = "Juan2023!",
                name = "Juan",
                profileImage = "https://example.com/images/juan.png",
                lastName = "Pérez",
                phone = "50688881234",
                birthday = LocalDate.of(1990, 4, 15) // 15 de mayo de 1990
            ),
            ApplicantRegister(
                email = "maria.gomez@hotmail.com",
                password = "MariaSecure!",
                name = "María",
                profileImage = "https://example.com/images/maria.png",
                lastName = "Gómez",
                phone = "50677769900",
                birthday = LocalDate.of(1985, 10, 25) // 25 de noviembre de 1985
            ),
            ApplicantRegister(
                email = "carlos.ortiz@yahoo.com",
                password = "Carlos2024!",
                name = "Carlos",
                profileImage = null,
                lastName = "Ortiz",
                phone = "50666554433",
                birthday = LocalDate.of(1995, 7, 30) // 30 de agosto de 1995
            ),
            ApplicantRegister(
                email = "1234",
                password = "1234",
                name = "Carlos",
                profileImage = null,
                lastName = "Ortiz",
                phone = "50666554433",
                birthday = LocalDate.of(1995, 7, 30) // 30 de agosto de 1995
            )
        )

        fun findUserRegisterByEmail(email: String): UserRegister? {
            return users.find { it.email == email }
        }

        fun addUserRegister(userRegister: UserRegister) {
            if (findUserRegisterByEmail(userRegister.email) != null) {
                throw IllegalArgumentException("User with email ${userRegister.email} already exists")
            }
            users.add(userRegister)
        }

        fun loginUser(userLogin: UserLogin): Boolean {
            return users.any { it.email == userLogin.email && it.password == userLogin.password }
        }
    }
}

//class CompanyRegistrationProvider {
//    companion object {
//        private val companies = mutableListOf(
//            CompanyRegistration(
//                email = "contacto@techsolutions.com",
//                password = "SecurePass123",
//                name = "Tech Solutions",
//                profileImage = "https://example.com/images/techsolutions.png",
//                fields = listOf(
//                    Field(1, "Tecnología"),
//                    Field(2, "Consultoría")
//                )
//            ),
//            CompanyRegistration(
//                email = "info@greenenergycr.com",
//                password = "EcoPower2025",
//                name = "Green Energy CR",
//                profileImage = "https://example.com/images/greenenergy.png",
//                fields = listOf(
//                    Field(3, "Energías Renovables"),
//                    Field(4, "Sostenibilidad")
//                )
//            ),
//            CompanyRegistration(
//                email = "contacto@finanzas360.com",
//                password = "Finance2025!",
//                name = "Finanzas 360",
//                profileImage = "https://example.com/images/finanzas360.jpg",
//                fields = listOf(
//                    Field(5, "Finanzas"),
//                    Field(6, "Inversiones")
//                )
//            )
//        )
//
//        fun findCompanyRegistrationByEmail(email: String): CompanyRegistration? {
//            return companies.find { it.email == email }
//        }
//
//        fun getCompaniesRegistrated(): List<CompanyRegistration> {
//            return companies
//        }
//
//        fun addCompany(companyRegistration: CompanyRegistration) {
//            companies.add(companyRegistration)
//        }
//
//        fun deleteCompany(companyRegistration: CompanyRegistration) {
//            companies.remove(companyRegistration)
//        }
//    }
//}
//
//class ApplicantRegistrationProvider {
//    companion object {
//        private val applicants = mutableListOf(
//            ApplicantRegistration(
//                email = "juan.perez@gmail.com",
//                password = "Juan2023!",
//                name = "Juan",
//                profileImage = "https://example.com/images/juan.png",
//                lastName = "Pérez",
//                phone = "50688881234",
//                birthday = LocalDate.of(1990, 4, 15) // 15 de mayo de 1990
//            ),
//            ApplicantRegistration(
//                email = "maria.gomez@hotmail.com",
//                password = "MariaSecure!",
//                name = "María",
//                profileImage = "https://example.com/images/maria.png",
//                lastName = "Gómez",
//                phone = "50677769900",
//                birthday = LocalDate.of(1985, 10, 25) // 25 de noviembre de 1985
//            ),
//            ApplicantRegistration(
//                email = "carlos.ortiz@yahoo.com",
//                password = "Carlos2024!",
//                name = "Carlos",
//                profileImage = null,
//                lastName = "Ortiz",
//                phone = "50666554433",
//                birthday = LocalDate.of(1995, 7, 30) // 30 de agosto de 1995
//            )
//        )
//
//        fun findApplicantRegistrationByEmail(email: String): ApplicantRegistration? {
//            return applicants.find { it.email == email }
//        }
//
//        fun getApplicantsRegistrated(): List<ApplicantRegistration> {
//            return applicants
//        }
//
//        fun addApplicant(applicantRegistration: ApplicantRegistration) {
//            applicants.add(applicantRegistration)
//        }
//
//        fun deleteApplicant(applicantRegistration: ApplicantRegistration) {
//            applicants.remove(applicantRegistration)
//        }
//    }
//}