package cr.una.sierra.frontend_oportunia_leandro.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.JobOfferDetailScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.JobOfferListScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.LoginScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.NoificationsScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferViewModel
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.LoginViewModel



@Composable
fun NavGraph(
    navController: NavHostController,
    jobOfferViewModel: JobOfferViewModel,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Login.ROUTE) {
        // JobOfferList screen
        composable(NavRoutes.Login.ROUTE) {
            JobOfferListScreen(
                navController = navController,
                jobOfferViewModel = jobOfferViewModel,
                paddingValues = paddingValues
            )
        }

        // JobOfferDetail screen
        composable(
            route = NavRoutes.JobOfferDetail.ROUTE,
            arguments = listOf(navArgument(NavRoutes.JobOfferDetail.ARG_JOB_OFFER_ID) {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            val jobOfferId = backStackEntry.arguments?.getLong(NavRoutes.JobOfferDetail.ARG_JOB_OFFER_ID) ?: 0L
            JobOfferDetailScreen(
                jobOfferId = jobOfferId,
                jobOfferViewModel = jobOfferViewModel,
                navController = navController,
                paddingValues = paddingValues
            )
        }

        // Settings screen
        composable(NavRoutes.Notifications.ROUTE) {
            NoificationsScreen(navController, paddingValues)
        }


    }
}


// ============================================================================================

//@Composable
//fun NavGraph(
//    navController: NavHostController,
//    loginViewModel: LoginViewModel,
//    jobOfferViewModel: JobOfferViewModel,
//    modifier: Modifier = Modifier
//) {
//    // Collect the login state from the ViewModel
//    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()
//
//    NavHost(
//        navController = navController,
//        startDestination = if (isLoggedIn) BottomNavItem.JobOfferList.route else "login",
//        modifier = modifier
//    ) {
//        // Define the composable for the login screen
//        composable("login") {
//            LoginScreen(
//                loginViewModel = loginViewModel,
//                onLoginSuccess = {
//                    // Navigate to the jobOffer list screen on successful login
//                    navController.navigate(BottomNavItem.JobOfferList.route) {
//                        popUpTo("login") { inclusive = true }
//                    }
//                }
//            )
//        }
//        // Define the composable for the jobOffer list screen
//        composable(BottomNavItem.JobOfferList.route) {
//            JobOfferListScreen(navController, jobOfferViewModel)
//        }
//        // Define the composable for the jobOffer detail screen
////        composable(BottomNavItem.JobOfferDetail.route + "/{jobOfferId}") { backStackEntry ->
////            val jobOfferId = backStackEntry.arguments?.getString("jobOfferId")?.toIntOrNull()
////            jobOfferId?.let {
////                JobOfferDetailScreen(
////                    jobOfferId = it,
////                    jobOfferViewModel = jobOfferViewModel,
////                    navController = navController
////                )
////            }
////        }
//        // Define the composable for the settings screen
//        composable(BottomNavItem.NotificationList.route) {
//            // SettingsScreen() // Uncomment if needed
//        }
//        // Define the composable for the logout functionality
////        composable(BottomNavItem.Logout.route) {
////            loginViewModel.logout() // Call the logout function
////            navController.navigate("login") {
////                popUpTo(BottomNavItem.JobOfferList.route) { inclusive = true }
////            }
////        }
//    }
//}