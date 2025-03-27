package cr.una.sierra.frontend_oportunia_leandro.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cr.una.sierra.frontend_oportunia_leandro.domain.model.JobOffer
import cr.una.sierra.frontend_oportunia_leandro.domain.repository.JobOfferRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class JobOfferState {
    /** Indicates an ongoing JobOffer operation */
    data object Loading : JobOfferState()

    /** Contains the successfully retrieved JobOffer */
    data class Success(val jobOffer: JobOffer) : JobOfferState()

    /** Indicates no JobOffer is available */
    data object Empty : JobOfferState()

    /** Contains an error message if the JobOffer operation fails */
    data class Error(val message: String) : JobOfferState()
}

class JobOfferViewModel( //) @Inject constructor(
    private val jobOfferRepository: JobOfferRepository
) : ViewModel() {

    private val _jobOffer = MutableStateFlow<JobOfferState>(JobOfferState.Empty)
    val jobOffer: StateFlow<JobOfferState> = _jobOffer

    private val _selectedJobOffer = MutableStateFlow<JobOffer?>(null)
    val selectedJobOffer: StateFlow<JobOffer?> = _selectedJobOffer

    private val _jobOfferList = MutableStateFlow<List<JobOffer>>(emptyList())
    val jobOfferList: StateFlow<List<JobOffer>> = _jobOfferList

    fun selectJobOffekById(Id: Long) {
        viewModelScope.launch {
            jobOfferRepository.findJobOfferById(Id)
                .onSuccess { jobOffer ->
                    _selectedJobOffer.value = jobOffer
                }
                .onFailure { exception ->
                    Log.e("JobOfferViewModel", "Error fetching jobOffe by ID: ${exception.message}")
                }
        }
    }

    fun findAllJobOffes() {
        viewModelScope.launch {
            jobOfferRepository.findAllJobOffers()
                .onSuccess { jobOffers ->
                    Log.d("TaskViewModel", "Total Tasks: ${jobOffers.size}")
                    _jobOfferList.value = jobOffers
                }
                .onFailure { exception ->
                    Log.e("TaskViewModel", "Failed to fetch tasks: ${exception.message}")
                }
        }
    }
}