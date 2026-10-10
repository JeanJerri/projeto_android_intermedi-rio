package com.agendapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.agendapp.data.Appointment
import com.agendapp.repository.AppointmentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppointmentViewModel(private val repository: AppointmentRepository) : ViewModel() {

    /** Lista reativa exposta à UI como StateFlow. */
    val appointments: StateFlow<List<Appointment>> = repository.appointments
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addAppointment(clientName: String, service: String, date: String, time: String) {
        viewModelScope.launch {
            repository.add(
                Appointment(
                    clientName = clientName.trim(),
                    service = service.trim(),
                    date = date.trim(),
                    time = time.trim()
                )
            )
        }
    }

    fun deleteAppointment(appointment: Appointment) {
        viewModelScope.launch { repository.remove(appointment) }
    }
}

class AppointmentViewModelFactory(
    private val repository: AppointmentRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppointmentViewModel::class.java)) {
            return AppointmentViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecida: ${modelClass.name}")
    }
}
