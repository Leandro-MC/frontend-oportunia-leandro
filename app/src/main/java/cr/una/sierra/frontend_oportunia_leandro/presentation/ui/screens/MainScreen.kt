import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cr.una.sierra.frontend_oportunia_leandro.presentation.navigation.NavRoutes
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.components.BottomNavigationBar
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.JobOfferListScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.NotificationScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferViewModel

@Composable
fun MainScreen(
    navController: NavHostController, // Global, si lo necesitas para logout o acciones globales
    jobOfferViewModel: JobOfferViewModel,
    onLogout: () -> Unit
) {
    // NavController interno para la navegación de la barra inferior
    val innerNavController = rememberNavController()

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavigationBar(
                navController = innerNavController,
                onLogout = onLogout // Pasa el callback
            )
        }
    ) { paddingValues ->
        // NavHost interno para las secciones de la MainScreen
        NavHost(
            navController = innerNavController,
            startDestination = NavRoutes.JobOfferList.ROUTE,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavRoutes.JobOfferList.ROUTE) {
                JobOfferListScreen(
                    jobOfferViewModel = jobOfferViewModel,
                    paddingValues = PaddingValues(0.dp),
                    onJobOfferClick = { jobId ->
                        navController.navigate(NavRoutes.JobOfferDetail.createRoute(jobId))
                    },
                    onRefresh = {
                        jobOfferViewModel.findAllJobOffes()
                    }
                )
            }
            composable(NavRoutes.Notifications.ROUTE) {
                NotificationScreen(
                    onBack = { innerNavController.navigateUp() }
                )
            }
        }
    }
}


