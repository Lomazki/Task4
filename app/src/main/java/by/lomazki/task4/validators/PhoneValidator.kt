package by.lomazki.task4.validators

import by.lomazki.task4.constants.Constants.EMPTY_FIELD
import by.lomazki.task4.constants.Constants.EMPTY_STRING
import by.lomazki.task4.constants.Constants.ERROR_PHONE_COUNT_DIGIT
import by.lomazki.task4.constants.Constants.ERROR_PHONE_TYPE
import by.lomazki.task4.constants.Constants.SIZE_PHONE_NUMBER
import by.lomazki.task4.models.ErrorMessage

class PhoneValidator {

    fun validate(value: String): String {
        if (value.isEmpty()) {
            ErrorMessage.message = EMPTY_FIELD
            return EMPTY_STRING
        }
        val valueArray = value.toCharArray().toMutableList()
        valueArray.removeAll { it == ' ' }
        if (valueArray[0] == '+') {
            valueArray.removeAt(0)
        }
        valueArray.forEach {
            if (!it.isDigit()) {
                ErrorMessage.message = ERROR_PHONE_TYPE
                return EMPTY_STRING
            }
        }

        if (valueArray.size != SIZE_PHONE_NUMBER) {
            ErrorMessage.message = ERROR_PHONE_COUNT_DIGIT
            return EMPTY_STRING
        }
        // приводим в образцово-показательный вид
        valueArray.add(5, ' ')
        valueArray.add(3, ' ')
        valueArray.add(0, '+')

        return String(valueArray.toCharArray())
    }
}

