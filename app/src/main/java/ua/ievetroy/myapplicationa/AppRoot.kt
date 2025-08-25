package ua.ievetroy.myapplicationa

import AppNavHost
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository
import ua.ievetroy.myapplicationa.ui.screens.main.bars.BottomBar
import ua.ievetroy.myapplicationa.ui.theme.AppDimens
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModelFactory

@Composable
fun AppRoot(repository: SettingsRepository) {
    val navController = rememberNavController()
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(repository)
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val routesWithBar = remember { setOf("main", "library") }
    val showBottomBar = currentRoute in routesWithBar

    Column(modifier = AppModifiers.rootColumnModifier) {
        AppNavHost(
            repository = repository,
            navController = navController,
            settingsViewModel = settingsViewModel,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        if (showBottomBar) {
            BottomBar(
                modifier = AppModifiers.bottomBarModifier,
                navController = navController
            )
        }
    }
}



