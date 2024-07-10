package di

import database.NoteDatabase
import database.DBFactory
import database.NoteDao
import dependencies.NoteRepository
import dependencies.NoteRepositoryImpl
import dependencies.viewmodels.ListNotesViewModel
import dependencies.viewmodels.NewNoteViewModel
import dependencies.viewmodels.SeeNoteViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module
val databaseModule = module {
    single<NoteDatabase> {
        val dbFactory: DBFactory = get()
        dbFactory.createDatabase()
    }
    single<NoteDao> { get<NoteDatabase>().getDao() }
}
val sharedModule = module {
    includes(databaseModule)

    //single<NoteRepository> { NoteRepositoryImpl(get()) }
    singleOf(::NoteRepositoryImpl).bind<NoteRepository>()

    viewModelOf(::ListNotesViewModel)
    viewModelOf(::SeeNoteViewModel)
    viewModelOf(::NewNoteViewModel)
    /*
    viewModel { ListNotesViewModel(get()) }
    viewModel { SeeNoteViewModel(get()) }
    viewModel { NewNoteViewModel(get()) }
    */


}