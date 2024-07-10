package delegator

fun action(action: Screens) {
    when (action) {
        is Screens.Home.NewNoteButton -> TODO("Handle new note button")
        is Screens.Home.SeeNotesButton -> TODO("Handle see notes button")

        is Screens.ListNotes.BackButton -> TODO("Handle back button in list")
        is Screens.ListNotes.SeeNoteButton -> TODO("Handle see note button")

        is Screens.NewNote.CancelButton -> TODO("Handle cancel button")
        is Screens.NewNote.SaveButton -> TODO("Handle save button")

        is Screens.SeeNote.BackButton -> TODO("Handle back button in see note")
    }
}

sealed class Screens {
    sealed class Home : Screens() {
        object NewNoteButton : Home()
        object SeeNotesButton : Home()
    }
    sealed class ListNotes : Screens() {
        object BackButton : ListNotes()
        object SeeNoteButton : ListNotes()
    }
    sealed class NewNote : Screens() {
        object CancelButton : NewNote()
        object SaveButton : NewNote()
    }
    sealed class SeeNote : Screens() {
        object BackButton : SeeNote()
    }
}