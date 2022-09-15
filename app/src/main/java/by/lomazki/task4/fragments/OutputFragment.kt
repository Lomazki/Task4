package by.lomazki.task4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.lomazki.task4.R
import by.lomazki.task4.appDatabase
import by.lomazki.task4.constants.Constants
import by.lomazki.task4.databinding.FragmentOutputBinding
import by.lomazki.task4.view.UserAdapter
import by.lomazki.task4.view.UserViewModel
import com.google.android.material.divider.MaterialDividerItemDecoration

class OutputFragment : Fragment() {

    private var _binding: FragmentOutputBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val adapter by lazy { UserAdapter(requireContext()) }
    private val userDao by lazy { requireContext().appDatabase.userDao() }
    private val viewModel: UserViewModel by viewModels(
        factoryProducer = {
            viewModelFactory {
                initializer { UserViewModel(userDao) }
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentOutputBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            updateDatabase()
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                MaterialDividerItemDecoration(
                    requireContext(),
                    MaterialDividerItemDecoration.VERTICAL
                )
            )
            viewModel.liveData.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            toolbar
                .menu
                .findItem(R.id.search_action)
                .actionView
                .let { it as SearchView }
                .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        adapter.submitList(userDao.getAll()
                            .filter {
                                it.name.contains(newText.toString())
                            })
                        return true
                    }
                })

//-------------- Удаление по свайпу--------------------
            val swipeToDelete =
                object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        viewModel.onSwipeRight(position)
                        Toast.makeText(
                            requireContext(),
                            Constants.DELETED,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            val itemTouchHelper = ItemTouchHelper(swipeToDelete)
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }

    private fun updateDatabase() {
        adapter.submitList(userDao.getAll())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}