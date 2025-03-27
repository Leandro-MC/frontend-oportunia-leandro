package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cr.una.sierra.frontend_oportunia_leandro.R

@Composable
fun MainLayout(
    paddingValues: PaddingValues,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            // App header section
            Surface(
                modifier = Modifier.fillMaxWidth(),
//                color = MaterialTheme.colorScheme.primary
                color = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onPrimary, // Blanco
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp)
                        .semantics { heading() }
                )
            }

            // Subtitle section
            Text(
                text = stringResource(id = R.string.app_title),
                color = MaterialTheme.colorScheme.onSurfaceVariant, // Azul oficial
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .padding(bottom = 16.dp)
            )

            // Content
            content()
        }
    }
}