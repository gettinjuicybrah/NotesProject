package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import navigation.Screen

@Composable
fun Home(
    navController: NavController
){
    Column{
        Row {
            Button(
                onClick = { navController.navigate(Screen.ListNotesScreen.route) }) {
                Text("See Notes")
            }
        }
        Row {
            Spacer(Modifier.height(20.dp))
        }
        Row {
            Button(onClick = { navController.navigate(Screen.NewNoteScreen.route) }) {
                Text("New Note")
            }
        }
    }
}