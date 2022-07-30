package net.trancool.listmakerv1.ui.main

import MainViewModel
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.trancool.listmakerv1.databinding.FragmentMainBinding
import net.trancool.listmakerv1.models.TaskList
//import com.raywenderlich.listmaker.R
//import com.raywenderlich.listmaker.databinding.MainFragmentBinding
//import com.raywenderlich.listmaker.models.TaskList
import kotlin.properties.Delegates

class MainFragment(
    ) : Fragment(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener{
    interface MainFragmentInteractionListener {
        fun listItemTapped(list: TaskList)
    }
    lateinit var clickListener: MainFragmentInteractionListener



    private lateinit var binding: FragmentMainBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding =  FragmentMainBinding.inflate(inflater, container, false)

        binding.listsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(),
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity())))
            .get(MainViewModel::class.java)

        val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this)

        binding.listsRecyclerview.adapter = recyclerViewAdapter

        viewModel.onListAdded = {
            recyclerViewAdapter.tasksUpdated()
        }
    }

    override fun listItemClicked(list: TaskList) {
//        TODO("Not yet implemented")
        clickListener.listItemTapped(list)

    }
}