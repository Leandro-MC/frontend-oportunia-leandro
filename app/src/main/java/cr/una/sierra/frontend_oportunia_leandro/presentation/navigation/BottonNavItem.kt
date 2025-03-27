package cr.una.sierra.frontend_oportunia_leandro.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import cr.una.sierra.frontend_oportunia_leandro.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    /**
     * TaskList represents the navigation item for the task list screen.
     */
    data object JobOfferList : BottomNavItem(
        NavRoutes.JobOfferList.ROUTE,
        R.string.menu_home,
        Icons.AutoMirrored.Filled.List)

    /**
     * TaskDetail represents the navigation item for the task detail screen.
     */
    data object NotificationList : BottomNavItem(
        NavRoutes.Notifications.ROUTE,
        R.string.menu_notifications,
        Icons.Filled.Info
    )

    /**
     * Settings represents the navigation item for the settings screen.
     */
//    object Settings : BottomNavItem("settings", R.string.settings, Icons.Filled.Settings)
//
//    /**
//     * Logout represents the navigation item for the logout functionality.
//     */
    data object Logout : BottomNavItem(
        NavRoutes.Login.ROUTE,
        R.string.logout,
        Icons.AutoMirrored.Filled.ExitToApp
    )

    companion object {
        /**
         * Returns a list of all bottom navigation items to be displayed in the navigation bar.
         */
        fun items() = listOf(JobOfferList, NotificationList, Logout)
    }
}