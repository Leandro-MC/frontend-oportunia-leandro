package cr.una.sierra.frontend_oportunia_leandro


import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
//import dagger.hilt.android.AndroidEntryPoint


import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.JobOfferDataSourceImpl
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.UserLoginDataSourceImpl
import cr.una.sierra.frontend_oportunia_leandro.data.datasource.UserRegisterDataSourceImpl
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.JobOfferMapper
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.UserLoginMapper
import cr.una.sierra.frontend_oportunia_leandro.data.mapper.UserRegisterMapper
import cr.una.sierra.frontend_oportunia_leandro.data.repository.JobOfferRepositoryImpl
import cr.una.sierra.frontend_oportunia_leandro.data.repository.UserLoginRepositoryImpl
import cr.una.sierra.frontend_oportunia_leandro.data.repository.UserRegisterRepositoryImpl
import cr.una.sierra.frontend_oportunia_leandro.presentation.factory.JobOfferViewModelFactory
import cr.una.sierra.frontend_oportunia_leandro.presentation.factory.LoginViewModelFactory
import cr.una.sierra.frontend_oportunia_leandro.presentation.factory.RegistrationViewModelFactory
import cr.una.sierra.frontend_oportunia_leandro.presentation.navigation.NavRoutes
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.components.BottomNavigationBar
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.layout.MainLayout
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.JobOfferDetailScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.JobOfferListScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.LoginScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.NoificationsScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens.RegistrationScreen
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.theme.FrontendoportuniaTheme
//import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.theme.FrontendoportunialeandroTheme
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferViewModel


import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.LoginViewModel
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.RegistrationViewModel
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.UserType

class MainActivity : ComponentActivity() {

    private val jobOfferViewModel: JobOfferViewModel by viewModels() {
        val jobOfferMapper = JobOfferMapper()
        val dataSource = JobOfferDataSourceImpl(jobOfferMapper)
        val jobOfferRepository = JobOfferRepositoryImpl(dataSource, jobOfferMapper)
        JobOfferViewModelFactory(jobOfferRepository)
    }

    private val loginViewModel: LoginViewModel by viewModels(){
        val userLoginMapper = UserLoginMapper()
        val dataSource = UserLoginDataSourceImpl(userLoginMapper)
        val userRegisterRepository = UserLoginRepositoryImpl (dataSource, userLoginMapper)
        LoginViewModelFactory(userRegisterRepository)
    }

    private val registrationViewModel: RegistrationViewModel by viewModels(){
        val userRegisterMapper = UserRegisterMapper()        // Recordar cambiar a usar el field Mapper dentro del UserRegisterMapper
        val dataSource = UserRegisterDataSourceImpl(userRegisterMapper)
        val userRegisterRepository = UserRegisterRepositoryImpl (dataSource, userRegisterMapper)
        RegistrationViewModelFactory(userRegisterRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FrontendoportuniaTheme(
                darkTheme = true,
                dynamicColor = false
            ) {
                AppNavigation(jobOfferViewModel, loginViewModel)
            }
        }
    }

    @Composable
    fun AppNavigation(
        jobOfferViewModel: JobOfferViewModel,
        loginViewModel: LoginViewModel
    ) {
        val navController = rememberNavController()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.Login.ROUTE
            ) {
                // Pantalla de Login
                composable(NavRoutes.Login.ROUTE) {
                    LoginScreen(
                        navController = navController,
                        viewModel = loginViewModel
                    )
                }
                // Pantalla de Registro (a implementar)
                composable(NavRoutes.Registration.ROUTE) {
                    RegistrationScreen(
                        navController = navController//,
                        //viewModel = registrationViewModel,
                        //UserType.APPLICANT
                    )
                }
                // Pantalla principal (lista de ofertas) con barra inferior
                composable(NavRoutes.JobOfferList.ROUTE) {
                    MainScreen(
                        navController = navController,
                        jobOfferViewModel = jobOfferViewModel
                    )
                }
                // Pantalla de detalle de oferta (con argumento)
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
                        paddingValues = PaddingValues() // O ajusta según necesites
                    )
                }
                // Pantalla de Notificaciones
                composable(NavRoutes.Notifications.ROUTE) {
                    NoificationsScreen(
                        navController = navController,
                        paddingValues = PaddingValues()
                    )
                }
            }
        }
    }

    @Composable
    fun MainScreen(
        navController: NavHostController,
        jobOfferViewModel: JobOfferViewModel
    ) {
        // Aquí mostramos la pantalla principal (lista de ofertas) con un Scaffold y la barra inferior
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Scaffold(
            bottomBar = {
                if (currentRoute in listOf(NavRoutes.JobOfferList.ROUTE, NavRoutes.Notifications.ROUTE)) {
                    BottomNavigationBar(
                        navController = navController,
                        jobOfferViewModel = jobOfferViewModel,
                        loginViewModel = loginViewModel
                        )
                }
            }
        ) { paddingValues ->
            JobOfferListScreen(
                navController = navController,
                jobOfferViewModel = jobOfferViewModel,
                paddingValues = paddingValues
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        FrontendoportuniaTheme(darkTheme = true) {
            Scaffold { paddingValues ->
                MainLayout(paddingValues = paddingValues) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "jobOffer List Content Preview",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}



//=========================================================================================================================


//    // ViewModel instances for login and jobOffer management
//    private val loginViewModel: LoginViewModel by viewModels()
//    private val jobOfferViewModel: JobOfferViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        enableEdgeToEdge()
//
//        setContent {
//            // RememberNavController is used to handle navigation between composable screens.
//            val navController = rememberNavController()
//
//            Scaffold(
//                bottomBar = {
//                    // Get the current back stack entry to determine the current route
//                    val navBackStackEntry by navController.currentBackStackEntryAsState()
//                    val currentRoute = navBackStackEntry?.destination?.route
//
//                    // Show bottom navigation bar only if the current screen is not "login"
//                    if (currentRoute != "login") {
//                        BottomNavigationBar(navController, jobOfferViewModel, loginViewModel)
//                    }
//                }
//            ) { innerPadding ->
//
//                NavGraph(
//                    navController = navController,
//                    loginViewModel = loginViewModel,
//                    jobOfferViewModel = jobOfferViewModel,
//                    modifier = Modifier.padding(innerPadding)
//                )
//            }


//=========================================================================================================================
//            FrontendoportunialeandroTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}



//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FrontendoportunialeandroTheme {
//        Greeting("Android")
//    }
//}