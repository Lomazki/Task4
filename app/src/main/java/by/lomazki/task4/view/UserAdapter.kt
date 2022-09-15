package by.lomazki.task4.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.lomazki.task4.databinding.ItemUserBinding
import by.lomazki.task4.models.User

class UserAdapter(private val context: Context) : ListAdapter<User, UserViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding
                .inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return (oldItem.name == newItem.name &&
                        oldItem.email == newItem.email &&
                        oldItem.phoneNumber == newItem.phoneNumber)
            }

        }
    }
}