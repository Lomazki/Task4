package by.lomazki.task4.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import by.lomazki.task4.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM User")
    fun observeUsers(): LiveData<List<User>>

    @Insert
    fun insertAll(vararg user: User)

    @Delete
    fun delete(user: User)

    //------ не использую ----------------------------------

    @Query("DELETE FROM user where id = :userId")
    fun deleteById(userId: Int)

    @Query("SELECT * FROM user WHERE id IN (:userId)")
    fun loadById(userId: Int): User
}
