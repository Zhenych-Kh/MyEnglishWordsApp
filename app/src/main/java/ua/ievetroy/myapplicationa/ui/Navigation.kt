import android.R
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository
import ua.ievetroy.myapplicationa.ui.screens.main.MainScreen
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsScreen
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModelFactory
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(repository: SettingsRepository) {
    val navController = rememberNavController()

    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(repository)
    )

    AnimatedNavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onSettingsClick = { navController.navigate("settings") },
                settingsViewModel = settingsViewModel
            )
        }
        composable(
            "settings",
            enterTransition = { slideInHorizontally { it } },
            exitTransition = { slideOutHorizontally { it } },
            popEnterTransition = { slideInHorizontally { it } },
            popExitTransition = { slideOutHorizontally { it } }
        ) {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                repository = repository
            )
        }
    }
}

