package com.agendapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.agendapp.viewmodel.AppointmentViewModel

object Routes {
    const val LIST = "list"
    const val ADD = "add"
}

@Composable
fun AppNavigation(viewModel: AppointmentViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LIST) {
        composable(Routes.LIST) {
            AppointmentListScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate(Routes.ADD) }
            )
        }
        composable(Routes.ADD) {
            AddAppointmentScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
