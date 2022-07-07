package net.trancool.listmakerv1.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import net.trancool.listmakerv1.MainActivity
import net.trancool.listmakerv1.R
import net.trancool.listmakerv1.databinding.ListDetailActivityBinding
import net.trancool.listmakerv1.models.TaskList
import net.trancool.listmakerv1.ui.detail.ui.detail.ListDetailFragment
import net.trancool.listmakerv1.ui.detail.ui.detail.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {

    lateinit var list : TaskList
    lateinit var binding: ListDetailActivityBinding
    lateinit var viewModel : ListDetailViewModel
    lateinit var fragment: ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.list_detail_activity)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!// pass the list from MainActivity
        title = viewModel.list.name

        binding.addTaskBtn.setOnClickListener{
            showCreateTaskDialog()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, ListDetailFragment.newInstance())
                .commitNow()
        }


    }

    private fun showCreateTaskDialog() {
//        TODO("Not yet implemented")
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task){ dialog, _->
                val task = taskEditText.text.toString()
                viewModel.addTask(task)
                dialog.dismiss()

            }
            .create()
            .show()
    }

}