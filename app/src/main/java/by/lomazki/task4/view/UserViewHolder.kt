package by.lomazki.task4.view

import androidx.recyclerview.widget.RecyclerView
import by.lomazki.task4.databinding.ItemUserBinding
import by.lomazki.task4.models.User

class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            userName.text = user.name
            userEmail.text = user.email
            userPhoneNumber.text = user.phoneNumber
        }
    }
}