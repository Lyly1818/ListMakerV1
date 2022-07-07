package net.trancool.listmakerv1.ui.detail.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
//import net.trancool.listmakerv1.databinding.
import net.trancool.listmakerv1.R
import net.trancool.listmakerv1.databinding.ListDetailFragmentBinding

class ListDetailFragment : Fragment() {

    private lateinit var viewModel: ListDetailViewModel
    lateinit var binding: ListDetailFragmentBinding

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return inflater.inflate(R.layout.list_detail_fragment, container, fa=lse)
//        user binding to access the layout
        binding  = ListDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root// return the root of the View
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListDetailViewModel::class.java)
        // TODO: Use the ViewModel
        val recyclerAdapter = ListItemsRecyclerViewAdapter(viewModel.list)
        binding.itemListRecyclerView.adapter = recyclerAdapter
        binding.itemListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.onTaskAdded = {
            recyclerAdapter.notifyDataSetChanged()
        }
    }

}