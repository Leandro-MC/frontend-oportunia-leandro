package cr.una.sierra.frontend_oportunia_leandro.presentation.navigation

sealed class NavRoutes {
    data object Login : NavRoutes() {
        const val ROUTE = "login"
    }

    data object Registration : NavRoutes() {
        const val ROUTE = "registration"
    }

    data object JobOfferList : NavRoutes() {
        const val ROUTE = "jobOfferList"
    }

    data object JobOfferDetail : NavRoutes() {
        const val ROUTE = "jobOfferDetail/{jobOfferId}"
        const val ARG_JOB_OFFER_ID = "jobOfferId"

        fun createRoute(jobOfferId: Long): String {
            return "jobOfferDetail/$jobOfferId"
        }
    }

    data object Notifications : NavRoutes() {
        const val ROUTE = "notifications"
    }

    data object MainScreen : NavRoutes() {
        const val ROUTE = "main_screen"
    }
}

