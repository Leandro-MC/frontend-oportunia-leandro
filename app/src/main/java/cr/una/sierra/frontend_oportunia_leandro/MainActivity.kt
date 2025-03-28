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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

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
import cr.una.sierra.frontend_oportunia_leandro.presentation.navigation.NavGraph
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.theme.FrontendoportuniaTheme
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferViewModel
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.LoginViewModel
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.RegistrationViewModel

class MainActivity : ComponentActivity() {

    private val jobOfferViewModel: JobOfferViewModel by viewModels() {
        val jobOfferMapper = JobOfferMapper()
        val dataSource = JobOfferDataSourceImpl(jobOfferMapper)
        val jobOfferRepository = JobOfferRepositoryImpl(dataSource, jobOfferMapper)
        JobOfferViewModelFactory(jobOfferRepository)
    }

    private val loginViewModel: LoginViewModel by viewModels() {
        val userLoginMapper = UserLoginMapper()
        val dataSource = UserLoginDataSourceImpl(userLoginMapper)
        val userRegisterRepository = UserLoginRepositoryImpl(dataSource, userLoginMapper)
        LoginViewModelFactory(userRegisterRepository)
    }

    private val registrationViewModel: RegistrationViewModel by viewModels() {
        val userRegisterMapper = UserRegisterMapper()        // Recordar cambiar a usar el field Mapper dentro del UserRegisterMapper
        val dataSource = UserRegisterDataSourceImpl(userRegisterMapper)
        val userRegisterRepository = UserRegisterRepositoryImpl(dataSource, userRegisterMapper)
        RegistrationViewModelFactory(userRegisterRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FrontendoportuniaTheme(
                //darkTheme = true,
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(
                        jobOfferViewModel = jobOfferViewModel,
                        loginViewModel = loginViewModel,
                        registrationViewModel = registrationViewModel
                    )
                }
            }
        }
    }
}


