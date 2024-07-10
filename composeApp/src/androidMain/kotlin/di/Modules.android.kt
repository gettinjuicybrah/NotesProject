package di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import android.content.Context
import database.DBFactory
import org.koin.android.ext.koin.androidContext

actual val platformModule = module {
    single { DBFactory(androidContext()) }
}