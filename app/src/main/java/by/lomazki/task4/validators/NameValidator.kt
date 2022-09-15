package by.lomazki.task4.validators

import by.lomazki.task4.constants.Constants.EMPTY_FIELD
import by.lomazki.task4.constants.Constants.EMPTY_STRING
import by.lomazki.task4.constants.Constants.ERROR_NAME
import by.lomazki.task4.constants.Constants.REGEX_NAME
import by.lomazki.task4.models.ErrorMessage
import java.util.regex.Pattern

class NameValidator {

    fun validate(value: String): String {
        val pattern = Pattern.compile(REGEX_NAME)
        val matcher = pattern.matcher(value)

        if (value.isEmpty()) {
            ErrorMessage.message = EMPTY_FIELD
            return EMPTY_STRING
        }

        return if (matcher.find()) {
            ErrorMessage.message = ERROR_NAME
            EMPTY_STRING
        } else {
            value
        }
    }
}
