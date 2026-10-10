package com.agendapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Entidade Room: um agendamento de serviço. */
@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clientName: String,
    val service: String,
    val date: String,
    val time: String
)
