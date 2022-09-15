package by.lomazki.task4

import android.app.Application
import android.content.Context
import androidx.room.Room
import by.lomazki.task4.databases.AppDatabase

class DatabaseApplication() : Application() {

    private var _database: AppDatabase? = null
    val database get() = requireNotNull(_database)

    override fun onCreate() {
        super.onCreate()

        _database = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                "database"
            )
            .allowMainThreadQueries().build()
    }
}

val Context.appDatabase: AppDatabase
    get() = when (this) {
        is DatabaseApplication -> database
        else -> applicationContext.appDatabase
    }