package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import cr.una.sierra.frontend_oportunia_leandro.domain.model.ApplicantRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.model.CompanyRegister
import cr.una.sierra.frontend_oportunia_leandro.domain.model.Field
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.RegistrationState
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.RegistrationViewModel
import java.time.LocalDate

/**
 * Un único Composable que maneja:
 * - Selección de Postulante / Empresa
 * - Paso 1: Correo + Contraseñas
 * - Paso 2: Campos específicos + Confirmar
 */
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel,
    onRegistrationSuccess: () -> Unit,
) {
    var isPostulante by remember { mutableStateOf(true) }
    var currentStep by remember { mutableStateOf(1) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    var companyName by remember { mutableStateOf("") }
    var sector by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }

    val registrationState by viewModel.registrationState.collectAsState()

    // Si el registro es exitoso, navegar a Login
    LaunchedEffect(registrationState) {
        when (registrationState) {
            is RegistrationState.Success -> {
                onRegistrationSuccess()
            }
            is RegistrationState.Error -> {
                // Manejar el error de registro
                val errorMessage = (registrationState as RegistrationState.Error).message
                // Mostrar un mensaje de error o manejarlo de otra manera
            }
            else -> {}
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        if (currentStep == 1) {
                            navController.popBackStack()
                        } else {
                            currentStep = 1
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (currentStep == 1) {
                Text(
                    text = "Registrarse",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = { isPostulante = true },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (isPostulante)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.primary,
                            containerColor = if (isPostulante)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.Transparent
                        )
                    ) {
                        Text("POSTULANTE")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    TextButton(
                        onClick = { isPostulante = false },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (!isPostulante)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.primary,
                            containerColor = if (!isPostulante)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.Transparent
                        )
                    ) {
                        Text("EMPRESA")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                RegistrationOutlinedTextField(
                    label = "Correo electrónico",
                    value = email,
                    onValueChange = { email = it }
                )
                Spacer(modifier = Modifier.height(16.dp))

                RegistrationOutlinedTextField(
                    label = "Contraseña",
                    value = password,
                    onValueChange = { password = it },
                    isPassword = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                RegistrationOutlinedTextField(
                    label = "Confirmar contraseña",
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    isPassword = true
                )


                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { currentStep = 2 },
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("CONTINUAR")
                }
            } else {
                Text(
                    text = if (isPostulante) "Registro de Postulante" else "Registro de Empresa",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                UserCircleIcon()

                Spacer(modifier = Modifier.height(16.dp))

                if (isPostulante) {
                    // Campos de Postulante
                    RegistrationOutlinedTextField(
                        label = "Nombre",
                        value = name,
                        onValueChange = { name = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    RegistrationOutlinedTextField(
                        label = "Apellidos",
                        value = lastName,
                        onValueChange = { lastName = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    RegistrationOutlinedTextField(
                        label = "Teléfono",
                        value = phone,
                        onValueChange = { phone = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    RegistrationOutlinedTextField(
                        label = "Fecha de Nacimiento",
                        value = birthDate,
                        onValueChange = { birthDate = it }
                    )
                } else {
                    // Campos de Empresa
                    RegistrationOutlinedTextField(
                        label = "Nombre",
                        value = companyName,
                        onValueChange = { companyName = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    RegistrationOutlinedTextField(
                        label = "Sector o Industria",
                        value = sector,
                        onValueChange = { sector = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    RegistrationOutlinedTextField(
                        label = "Contacto",
                        value = contact,
                        onValueChange = { contact = it }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (isPostulante) {
                            val applicantRegister = ApplicantRegister(
                                email = email,
                                password = password,
                                name = name,
                                profileImage = null,
                                lastName = lastName,
                                phone = phone,
                                birthday = LocalDate.now()
                            )
                            viewModel.registerApplicant(applicantRegister)
                        } else {
                            val companyRegister = CompanyRegister(
                                email = email,
                                password = password,
                                name = companyName,
                                profileImage = null,
                                fields = listOf(Field(0, sector)) // Cambiar esto
                            )
                            viewModel.registerCompany(companyRegister)
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("CONFIRMAR")
                }
            }
        }
    }
}


/**
 * Campo reutilizable para texto y contraseñas.
 */
@Composable
fun RegistrationOutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth()
    )
}


/**
 * Ícono circular central (con un 'person' + posible overlay de '+').
 */
@Composable
fun UserCircleIcon() {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "User Icon",
            tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            modifier = Modifier.size(48.dp)
        )
        // Aquí superponer un ícono de '+', etc.
    }
}



