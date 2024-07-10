package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dependencies.viewmodels.SeeNoteViewModel
import navigation.Screen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SeeNote(
    navController: NavController,
    noteId: Int
) {
    val viewModel: SeeNoteViewModel = koinViewModel()
    val note by viewModel.note.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    LaunchedEffect(noteId) {
        viewModel.loadNote(noteId)
    }

    LaunchedEffect(note) {
        note?.let {
            title = it.title
            content = it.content
        }
    }

    Column {
        Button(onClick = { navController.navigate(Screen.ListNotesScreen.route) }) {
            Text("Back")
        }

        Spacer(Modifier.height(20.dp))

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.updateNote(noteId, title, content)
                navController.navigate(Screen.ListNotesScreen.route)
            },
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Saving..." else "Save")
        }

        if (error != null) {
            Text(error!!, color = Color.Red)
        }
    }
}