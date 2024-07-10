package dependencies

import kotlinx.coroutines.flow.Flow
import database.NoteEntity
interface NoteRepository {

    fun getNotes(): Flow<List<NoteEntity>>
    suspend fun getNoteById(id: Int): NoteEntity?

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)
}