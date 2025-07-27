import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository
import ua.ievetroy.myapplicationa.ui.screens.main.MainScreen
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsScreen
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModelFactory

@Composable
fun AppNavHost(repository: SettingsRepository) {
    val navController = rememberNavController()

    // ОДИН інстанс на граф!
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(repository)
    )

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onSettingsClick = { navController.navigate("settings") },
                settingsViewModel = settingsViewModel   // ← тут!
            )
        }
        composable("settings") {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                repository = repository                // для фабрики
            )
        }
    }
}
