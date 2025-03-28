package cr.una.sierra.frontend_oportunia_leandro.presentation.navigation

import MainScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavGraph.Companion.findStartDestination

import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.JobOfferDetailScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.LoginScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.RegistrationScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferViewModel
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.LoginViewModel
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.RegistrationViewModel


@Composable
fun NavGraph(
    jobOfferViewModel: JobOfferViewModel,
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "auth_graph"
    ) {
        // Rutas del módulo de autenticación
        authNavGraph(navController, loginViewModel, registrationViewModel)

        // Rutas del módulo de ofertas (se mantiene sin cambios)
        jobOfferNavGraph(navController, jobOfferViewModel)

        // Pantalla principal que contiene el menú inferior
        composable(NavRoutes.MainScreen.ROUTE) {
            MainScreen(
                navController = navController,
                jobOfferViewModel = jobOfferViewModel,
                onLogout = {
                    loginViewModel.logout()
                    registrationViewModel.registerLogout()
                    // Navegar al flujo de autenticación: puedes usar el navController global o un navController especial para este propósito
                    navController.navigate("auth_graph") {
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    registrationViewModel: RegistrationViewModel
) {
    navigation(
        startDestination = NavRoutes.Login.ROUTE,
        route = "auth_graph"
    ) {
        composable(NavRoutes.Login.ROUTE) {
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegistration = {
                    navController.navigate(NavRoutes.Registration.ROUTE)
                },
                onLoginSuccess = {
                    // Navegar a la pantalla principal con menú inferior
                    navController.navigate(NavRoutes.MainScreen.ROUTE) {
                        popUpTo(NavRoutes.Login.ROUTE) { inclusive = true }
                    }
                }
            )
        }
        composable(NavRoutes.Registration.ROUTE) {
            RegistrationScreen(
                navController = navController,
                viewModel = registrationViewModel,
                onRegistrationSuccess = {
                    navController.navigate(NavRoutes.MainScreen.ROUTE) {
                        popUpTo(NavRoutes.Login.ROUTE) { inclusive = true }
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.jobOfferNavGraph(
    navController: NavHostController,
    jobOfferViewModel: JobOfferViewModel
) {
    navigation(
        startDestination = NavRoutes.JobOfferList.ROUTE,
        route = "job_offer_graph"
    ) {
        composable(
            route = NavRoutes.JobOfferDetail.ROUTE,
            arguments = listOf(navArgument(NavRoutes.JobOfferDetail.ARG_JOB_OFFER_ID) {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            val jobOfferId =
                backStackEntry.arguments?.getLong(NavRoutes.JobOfferDetail.ARG_JOB_OFFER_ID)
                    ?: 0L
            JobOfferDetailScreen(
                jobOfferId = jobOfferId,
                jobOfferViewModel = jobOfferViewModel,
                navController = navController,
                paddingValues = PaddingValues()
            )
        }
    }
}