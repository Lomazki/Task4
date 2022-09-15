package by.lomazki.task4.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import by.lomazki.task4.dao.UserDao
import by.lomazki.task4.models.User

class UserViewModel(
    private val userDao: UserDao
) : ViewModel() {

    val liveData: LiveData<List<User>> = userDao.observeUsers()

    fun onSwipeRight(position: Int) {
        val userForDelete = userDao.getAll()[position]
        userDao.delete(userForDelete)
    }
}
