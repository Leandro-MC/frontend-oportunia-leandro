package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationScreen(
    onBack: () -> Unit
){
    Button(onClick = onBack) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
        )
        Text(text = "Volver")
    }



    // Implementación de la pantalla de notificaciones
    // Aquí puedes agregar el contenido específico de la pantalla de notificaciones
    // Por ejemplo, una lista de notificaciones o cualquier otro componente que desees mostrar

    // Ejemplo simple:
    // Text(text = "Pantalla de Notificaciones", modifier = Modifier.padding(paddingValues))
}