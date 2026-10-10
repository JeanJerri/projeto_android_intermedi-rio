package com.agendapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.agendapp.viewmodel.AppointmentViewModel

private val DATE_REGEX = Regex("""^(0[1-9]|[12]\d|3[01])/(0[1-9]|1[0-2])/\d{4}$""")
private val TIME_REGEX = Regex("""^([01]\d|2[0-3]):[0-5]\d$""")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppointmentScreen(
    viewModel: AppointmentViewModel,
    onBack: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var service by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var time by rememberSaveable { mutableStateOf("") }
    var submitted by rememberSaveable { mutableStateOf(false) }

    val nameError = submitted && name.isBlank()
    val serviceError = submitted && service.isBlank()
    val dateError = submitted && !DATE_REGEX.matches(date.trim())
    val timeError = submitted && !TIME_REGEX.matches(time.trim())

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Novo agendamento") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Nome do cliente") }, singleLine = true,
                isError = nameError,
                supportingText = { if (nameError) Text("Informe o nome") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = service, onValueChange = { service = it },
                label = { Text("Serviço") }, singleLine = true,
                isError = serviceError,
                supportingText = { if (serviceError) Text("Informe o serviço") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = date, onValueChange = { date = it },
                label = { Text("Data (dd/mm/aaaa)") }, singleLine = true,
                isError = dateError,
                supportingText = { if (dateError) Text("Data inválida") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = time, onValueChange = { time = it },
                label = { Text("Horário (HH:mm)") }, singleLine = true,
                isError = timeError,
                supportingText = { if (timeError) Text("Horário inválido") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    submitted = true
                    if (name.isNotBlank() && service.isNotBlank() &&
                        DATE_REGEX.matches(date.trim()) && TIME_REGEX.matches(time.trim())
                    ) {
                        viewModel.addAppointment(name, service, date, time)
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Salvar") }
        }
    }
}
