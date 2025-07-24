import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository
import ua.ievetroy.myapplicationa.ui.screens.main.MainScreen
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsScreen

@Composable
fun AppNavHost(repository: SettingsRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onSettingsClick = { navController.navigate("settings") }
            )
        }
        composable("settings") {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                repository = repository
            )
        }
    }
}