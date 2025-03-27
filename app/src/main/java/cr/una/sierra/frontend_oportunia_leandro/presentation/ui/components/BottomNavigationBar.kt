package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import cr.una.sierra.frontend_oportunia_leandro.presentation.navigation.BottomNavItem
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferViewModel
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.LoginViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
    jobOfferViewModel: JobOfferViewModel,
    loginViewModel: LoginViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        BottomNavItem.items().forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.route == item.route
            } ?: false
            val itemTitle = stringResource(id = item.title)
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                label = {
                    androidx.compose.material3.Text(
                        text = itemTitle,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = isSelected,
                onClick = {
                    if (item is BottomNavItem.Logout) {
                        loginViewModel.logout()
                    }
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

//@Composable
//fun BottomNavigationBar(
//    navController: NavController,
//    jobOfferViewModel: JobOfferViewModel,
//
//    //loginViewModel: LoginViewModel,
//    //backgroundColor: Color = Color.Blue
//) {
////    val items = listOf(
////        BottomNavItem.JobOfferList,
//////        BottomNavItem.TaskDetail,
////        BottomNavItem.NotificationList,
//////        BottomNavItem.Logout
////    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    NavigationBar(
//        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
//        modifier = Modifier.fillMaxWidth(),
//    ) {
////        val navBackStackEntry by navController.currentBackStackEntryAsState()
////        val currentRoute = navBackStackEntry?.destination?.route
//
//        BottomNavItem.items().forEach { item ->
//            val isSelected = currentDestination?.hierarchy?.any {
//                it.route == item.route
//            } ?: false
//
//            // Get string resource outside of the semantics block
//            val itemTitle = stringResource(id = item.title)
//            val itemDescription = if (isSelected) {
//                "$itemTitle, selected"
//            } else {
//                "$itemTitle, not selected"
//            }
//
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        imageVector = item.icon,
//                        contentDescription = null
//                    )
//                },
//                label = {
//                    Text(
//                        text = itemTitle,
//                        style = MaterialTheme.typography.labelSmall
//                    )
//                },
//                selected = isSelected,
//                onClick = {
//                    navController.navigate(item.route) {
//                        // Pop up to the start destination of the graph to
//                        // avoid building up a large stack of destinations
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        // Avoid multiple copies of the same destination
//                        launchSingleTop = true
//                        // Restore state when navigating back
//                        restoreState = true
//                    }
//                },
//                modifier = Modifier.semantics {
//                    contentDescription = itemDescription
//                }
//            )
//        }


// =================================================================================================

//        items.forEach { item ->
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        imageVector = item.icon,
//                        contentDescription = stringResource(id = item.title)
//                    )
//                },
//                label = {
//                    Text(
//                        text = stringResource(id = item.title),
//                        textAlign = TextAlign.Center
//                    )
//                },
//                selected = currentRoute == item.route,
//                onClick = {
//                    when (item) {
////                        is BottomNavItem.TaskDetail -> {
////                            val firstTask = taskViewModel.taskList.value.firstOrNull()
////                            if (firstTask != null) {
////                                navController.navigate("taskDetail/${firstTask.id}")
////                            } else {
////                                // Handle the case when there is no task
////                                // Maybe show a Toast or SnackBar message to inform the user
////                            }
////                        }
//
//                        is BottomNavItem.Logout -> {
//                            // Handle logout action
//                            loginViewModel.logout()
//                            navController.navigate("login") {
//                                popUpTo(0) { inclusive = true }  // Clears the entire back stack
//                            }
//                        }
//
//                        else -> {
//                            navController.navigate(item.route) {
//                                navController.graph.startDestinationRoute?.let { route ->
//                                    popUpTo(route) { saveState = true }
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    }
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = Color.White,  // Set icons to white when selected
//                    unselectedIconColor = Color.Gray,  // Set icons to gray when unselected
//                    selectedTextColor = Color.White,  // Set text to white when selected
//                    unselectedTextColor = Color.Gray  // Set text to gray when unselected
//                )
//            )
//        }
//    }
//}