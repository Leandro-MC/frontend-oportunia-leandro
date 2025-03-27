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
import androidx.compose.material3.TextFieldDefaults

import cr.una.sierra.frontend_oportunia_leandro.presentation.navigation.NavRoutes

/**
 * Un único Composable que maneja:
 * - Selección de Postulante / Empresa
 * - Paso 1: Correo + Contraseñas
 * - Paso 2: Campos específicos + Confirmar
 */
@Composable
fun RegistrationScreen(
    navController: NavController
) {
    // Tipo seleccionado: "Postulante" (true) o "Empresa" (false)
    var isPostulante by remember { mutableStateOf(true) }
    // Paso actual: 1 => (Correo, Contraseña), 2 => (Datos específicos)
    var currentStep by remember { mutableStateOf(1) }

    // Paso 1: Estados
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Paso 2: Estados de Postulante
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    // Paso 2: Estados de Empresa
    var companyName by remember { mutableStateOf("") }
    var sector by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }

    // Surface con el fondo oscuro (definido en tu tema)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Contenedor principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Encabezado con flecha en la esquina superior derecha
            // Si estamos en step 1, la flecha regresa al Login
            // Si estamos en step 2, la flecha regresa al step 1
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        if (currentStep == 1) {
                            navController.popBackStack() // Volver al Login
                        } else {
                            currentStep = 1 // Regresar al paso 1
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterEnd)
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
                // =======================
                // PASO 1: Selección de tipo + Correo + Contraseñas
                // =======================

                // Título principal
                Text(
                    text = "Registrarse",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Toggle (Tabs) para Postulante o Empresa
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

                // Campos de Correo, Contraseña, Confirmar Contraseña
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

                // Texto aclaratorio, por ejemplo:
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Debe tener al menos 8 dígitos, combinando valores alfanuméricos.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botón CONTINUAR
                Button(
                    onClick = {
                        // Podrías validar email, password, etc. antes de avanzar
                        currentStep = 2
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("CONTINUAR")
                }
            } else {
                // =======================
                // PASO 2: Campos específicos
                // =======================
                if (isPostulante) {
                    // Encabezado en rojo: "Registro de Postulante"
                    Text(
                        text = "Registro de Postulante",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Text(
                        text = "Registro de Empresa",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Ícono central con + (simulamos)
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

                // Botón CONFIRMAR
                Button(
                    onClick = {
                        // Aquí harías la lógica final de registro
                        // Podrías navegar a otra pantalla o volver a login
                        navController.navigate(NavRoutes.Login.ROUTE) {
                            popUpTo(NavRoutes.Registration.ROUTE) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
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
        modifier = Modifier.fillMaxWidth(),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            textColor = MaterialTheme.colorScheme.onSurface,
//            cursorColor = MaterialTheme.colorScheme.primary,
//            focusedBorderColor = MaterialTheme.colorScheme.primary,
//            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
//            focusedLabelColor = MaterialTheme.colorScheme.primary,
//            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
//        )
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
        // Aquí podrías superponer un ícono de '+', etc.
    }
}
