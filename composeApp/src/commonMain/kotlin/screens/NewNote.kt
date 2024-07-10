package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import database.NoteEntity
import dependencies.viewmodels.NewNoteViewModel
import navigation.Screen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewNote(
    navController: NavController
){
    val viewModel: NewNoteViewModel = koinViewModel()
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    Column {
        Row {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }
            )
        }
        Row {
            Spacer(Modifier.height(20.dp))
        }
        Row {
            TextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") }

            )
        }
            Row {
                Spacer(Modifier.height(20.dp))
            }
            Row {
                //exit button
                Button(onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                }) {
                    Text("Exit")
                }

                //save button
                Button(onClick = {
                    viewModel.insertNote(title, content)
                    navController.navigate(Screen.HomeScreen.route)
                }) {
                    Text("Save")
                }

            }

    }
}