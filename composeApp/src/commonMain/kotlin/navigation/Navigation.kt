package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import screens.Home
import screens.ListNotes
import screens.NewNote
import screens.SeeNote

@Composable
fun NavHostController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            Home(navController)
        }
        composable(route = Screen.ListNotesScreen.route) {
            ListNotes(navController)
        }
        composable(route = Screen.NewNoteScreen.route) {
            NewNote(navController)
        }
        composable(
            route = Screen.SeeNoteScreen.route + "/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            SeeNote(navController, noteId)
        }
    }
}

sealed class Screen(val route: String) {

    object HomeScreen: Screen("home_screen")
    object ListNotesScreen: Screen("listnotes_screen")
    object NewNoteScreen: Screen("newnote_screen")
    object SeeNoteScreen: Screen("seenote_screen")


}