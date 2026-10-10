package com.agendapp.repository

import com.agendapp.data.Appointment
import com.agendapp.data.AppointmentDao
import kotlinx.coroutines.flow.Flow

/** Única fonte de dados para a ViewModel; abstrai o DAO. */
class AppointmentRepository(private val dao: AppointmentDao) {
    val appointments: Flow<List<Appointment>> = dao.getAll()

    suspend fun add(appointment: Appointment) = dao.insert(appointment)

    suspend fun remove(appointment: Appointment) = dao.delete(appointment)
}
