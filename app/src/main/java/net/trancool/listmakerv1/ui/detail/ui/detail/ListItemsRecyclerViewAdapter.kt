package net.trancool.listmakerv1.ui.detail.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.trancool.listmakerv1.databinding.ListItemViewHolderBinding
import net.trancool.listmakerv1.models.TaskList

class ListItemsRecyclerViewAdapter(private var list: TaskList):
RecyclerView.Adapter<ListItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
//        TODO("Not yet implemented")
        val binding = ListItemViewHolderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
//        TODO("Not yet implemented")
        holder.binding.textViewTask.text = list.tasks[position]
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
        return list.tasks.size
    }
    fun tasksUpdated() {
        notifyItemInserted(list.tasks.size - 1)
    }
}