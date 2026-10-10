package com.agendapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.agendapp.data.AppDatabase
import com.agendapp.repository.AppointmentRepository
import com.agendapp.ui.AppNavigation
import com.agendapp.viewmodel.AppointmentViewModel
import com.agendapp.viewmodel.AppointmentViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: AppointmentViewModel by viewModels {
        val dao = AppDatabase.getInstance(applicationContext).appointmentDao()
        AppointmentViewModelFactory(AppointmentRepository(dao))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation(viewModel)
            }
        }
    }
}
