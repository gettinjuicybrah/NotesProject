package dependencies.viewmodels

import dependencies.NoteRepositoryImpl
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import database.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ListNotesViewModel() : ViewModel(), KoinComponent {
    val repository: NoteRepositoryImpl by inject()
    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes: StateFlow<List<NoteEntity>> = _notes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getNotes().collect { notesList ->
                    _notes.value = notesList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = "Failed to load notes: ${e.message}"
                _isLoading.value = false
            }
        }
    }
}
class SeeNoteViewModel() : ViewModel(), KoinComponent {
    val repository: NoteRepositoryImpl by inject()
    private val _note = MutableStateFlow<NoteEntity?>(null)
    val note: StateFlow<NoteEntity?> = _note.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadNote(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _note.value = repository.getNoteById(id)
                //_isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Failed to load note: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateNote(id: Int, title: String, content: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val updatedNote = NoteEntity(id = id, title = title, content = content)
                repository.insertNote(updatedNote)
                _note.value = updatedNote
            } catch (e: Exception) {
                _error.value = "Failed to update note: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
class NewNoteViewModel : ViewModel(), KoinComponent {
    private val repository: NoteRepositoryImpl by inject()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun insertNote(title: String, content: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val note = NoteEntity(title = title, content = content)
                repository.insertNote(note)
            } catch (e: Exception) {
                _error.value = "Failed to insert note: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}