package screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dependencies.viewmodels.ListNotesViewModel
import navigation.Screen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ListNotes(
    navController: NavController
) {
    val viewModel: ListNotesViewModel = koinViewModel()
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    Column {
        Button(onClick = { navController.navigate(Screen.HomeScreen.route) }) {
            Text("Back")
        }

        when {
            isLoading -> {
                Text("Loading...")
            }
            error != null -> {
                Text(error!!, color = Color.Red)
            }
            /*
            notes.isNullOrEmpty() -> {
                Text("No notes available")
            }
             */
            else -> {
                LazyColumn {
                    items(notes.value) { note ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate(Screen.SeeNoteScreen.route + "/${note.id}") }
                                .padding(16.dp)
                        ) {
                            Text(note.title)
                        }
                    }
                }
            }
        }
    }
}
