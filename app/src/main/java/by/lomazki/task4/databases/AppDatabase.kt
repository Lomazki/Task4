package by.lomazki.task4.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import by.lomazki.task4.dao.UserDao
import by.lomazki.task4.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}