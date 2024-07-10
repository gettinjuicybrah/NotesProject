package database


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase(), DB {

    abstract fun getDao(): NoteDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}


internal const val dbFileName = "note.db"


interface DB {
    fun clearAllTables() {}
}