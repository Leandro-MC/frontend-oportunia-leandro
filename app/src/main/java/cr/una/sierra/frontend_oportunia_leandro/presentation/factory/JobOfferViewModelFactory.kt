package cr.una.sierra.frontend_oportunia_leandro.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.JobOfferRepository
import cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel.JobOfferViewModel

class JobOfferViewModelFactory (
    private val jobOfferRepository: JobOfferRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobOfferViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobOfferViewModel(jobOfferRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}