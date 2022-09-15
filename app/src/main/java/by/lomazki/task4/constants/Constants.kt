package by.lomazki.task4.constants

object Constants {
    const val EMPTY_STRING = ""
    const val REGEX_NAME = "[^a-zA-Zа-яА-Я\\s]"
    const val REGEX_EMAIL = "^[a-zA-Z0-9.]+@[a-z]+\\.+[a-z]{2,5}$"
    const val SIZE_PHONE_NUMBER = 12
    const val EMPTY_FIELD = "there is an empty field"
    const val ERROR_NAME = "Only letters and spaces are allowed"
    const val ERROR_EMAIL = "Email is incorrect"
    const val ERROR_PHONE_TYPE = "Only numbers are allowed"
    const val ERROR_PHONE_COUNT_DIGIT = "The phone number must have no more than 12 digits"
    const val USER_ADDED = "User %s has been added"
    const val DELETED = "User has been deleted"
}
