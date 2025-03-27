package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferState
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferViewModel
import cr.una.sierra.frontend_oportunia_leandro.R
import cr.una.sierra.frontend_oportunia_leandro.domain.model.JobOffer
import cr.una.sierra.frontend_oportunia_leandro.presentation.navigation.NavRoutes
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.components.JobOfferItem

@Composable
fun JobOfferListScreen(
    navController: NavController,
    jobOfferViewModel: JobOfferViewModel,
    paddingValues: PaddingValues
) {

    val jobOfferList by jobOfferViewModel.jobOfferList.collectAsState()
    val jobOfferState by jobOfferViewModel.jobOffer.collectAsState()

    LaunchedEffect(Unit) {
        jobOfferViewModel.findAllJobOffes()
    }

    var isRefreshing by remember { mutableStateOf(false) }

    // Update refreshing state based on jobOffer state
    isRefreshing = jobOfferState is JobOfferState.Loading

    @OptIn(androidx.compose.material.ExperimentalMaterialApi::class)
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            jobOfferViewModel.findAllJobOffes()
        }
    )

    @OptIn(androidx.compose.material.ExperimentalMaterialApi::class)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .pullRefresh(pullRefreshState)
    ) {
        when {
            // Show loading indicator when list is empty and loading
            jobOfferList.isEmpty() && jobOfferState is JobOfferState.Loading -> {
                LoadingContent()
            }
            // Show empty state message
            jobOfferList.isEmpty() -> {
                EmptyContent()
            }
            // Show the jobOffer list
            else -> {
                JobOfferListContent(
                    jobOfferList = jobOfferList,
                    navController = navController
                )
            }
        }

        @OptIn(androidx.compose.material.ExperimentalMaterialApi::class)
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            backgroundColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}



@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.semantics {
                contentDescription = "Loading jobOffers"
            }
        )
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.no_job_offers_available),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .semantics {
                    contentDescription = "Empty job offer list message"
                }
        )
    }
}

@Composable
private fun JobOfferListContent(
    jobOfferList: List<JobOffer>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)
            .semantics {
                contentDescription = "Job Offer list with ${jobOfferList.size} jobOffers"
            }
    ) {
        items(
            items = jobOfferList,
            key = { jobOffer -> jobOffer.id ?: 0 }
        ) { jobOffer ->
            JobOfferItem(
                jobOffer = jobOffer,
                onClick = {
                    jobOffer.id?.let { id ->
                        //navController.navigate(NavRoutes.JobOfferDetail.createRoute(id))
                    }
                }
            )
        }
    }
}