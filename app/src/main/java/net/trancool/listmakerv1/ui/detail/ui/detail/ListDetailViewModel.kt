package net.trancool.listmakerv1.ui.detail.ui.detail

import androidx.lifecycle.ViewModel
import net.trancool.listmakerv1.models.TaskList

class ListDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    lateinit var list:  TaskList
    lateinit var onTaskAdded: (()-> Unit)


    fun addTask(task: String){
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}