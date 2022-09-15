package by.lomazki.task4.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.lomazki.task4.appDatabase
import by.lomazki.task4.constants.Constants
import by.lomazki.task4.databinding.FragmentInputBinding
import by.lomazki.task4.models.ErrorMessage
import by.lomazki.task4.models.User
import by.lomazki.task4.validators.EmailValidator
import by.lomazki.task4.validators.NameValidator
import by.lomazki.task4.validators.PhoneValidator

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val userDao by lazy {
        requireContext().appDatabase.userDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentInputBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            root.setBackgroundColor(Color.GREEN)
            buttonDone.setOnClickListener {
                val name = NameValidator().validate(nameEditText.text.toString())
                checkError(name)

                val email = EmailValidator().validate(emailEditText.text.toString())
                checkError(email)

                val phone = PhoneValidator().validate(phoneEditText.text.toString())
                checkError(phone)

                if (name != Constants.EMPTY_STRING && phone != Constants.EMPTY_STRING && email != Constants.EMPTY_STRING) {
                    val newUser = User(name = name, phoneNumber = phone, email = email)
                    userDao.insertAll(newUser)
                    with(includedLayout) {
                        userPhoneNumber.text = newUser.phoneNumber
                        userName.text = newUser.name
                        userEmail.text = newUser.email
                        root.visibility = View.VISIBLE
                    }
                    Toast.makeText(
                        requireContext(),
                        String.format(Constants.USER_ADDED, newUser.name),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun checkError(value: String) {
        if (value == Constants.EMPTY_STRING) {
            Toast.makeText(
                requireContext(),
                ErrorMessage.message,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}