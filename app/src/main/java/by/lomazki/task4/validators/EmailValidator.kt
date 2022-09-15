package by.lomazki.task4.validators

import by.lomazki.task4.constants.Constants.EMPTY_FIELD
import by.lomazki.task4.constants.Constants.EMPTY_STRING
import by.lomazki.task4.constants.Constants.ERROR_EMAIL
import by.lomazki.task4.constants.Constants.REGEX_EMAIL
import by.lomazki.task4.models.ErrorMessage
import java.util.regex.Pattern

class EmailValidator {

    fun validate(value: String): String {
        val pattern = Pattern.compile(REGEX_EMAIL)
        val matcher = pattern.matcher(value)

        if (value.isEmpty()) {
            ErrorMessage.message = EMPTY_FIELD
            return EMPTY_STRING
        }
        if (!matcher.find()) {
            ErrorMessage.message = ERROR_EMAIL
            return EMPTY_STRING
        }
        return value
    }
}
