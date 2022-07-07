package net.trancool.listmakerv1

import MainViewModel
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import net.trancool.listmakerv1.databinding.ActivityMainBinding
import net.trancool.listmakerv1.models.TaskList
import net.trancool.listmakerv1.ui.detail.ListDetailActivity
import net.trancool.listmakerv1.ui.main.ListSelectionRecyclerViewAdapter
import net.trancool.listmakerv1.ui.main.MainFragment
import net.trancool.listmakerv1.ui.main.MainViewModelFactory

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this)))
            .get(MainViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.i("MainActivity", viewModel.toString())

        if (savedInstanceState == null) {
           val mainFragment = MainFragment.newInstance(this)
                   supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
        }

        binding.fabButton.setOnClickListener {
            showCreateListDialog()
        }
    }

    private fun showCreateListDialog() {

        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)
        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            dialog.dismiss()
            val taskList = TaskList(listTitleEditText.text.toString())
            viewModel.saveList(taskList)
            showListDetail(taskList)

        }


        builder.create().show()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 1
        if (requestCode == LIST_DETAIL_REQUEST_CODE && resultCode ==
            Activity.RESULT_OK) {
// 2
            data?.let {
                // 3
               viewModel.updateList(data.getParcelableExtra(INTENT_LIST_KEY)!!)
                viewModel.refreshLists()
            }
        } }

//    private fun updateLists() {
//        val lists = listDataManager.readLists()
//        listsRecyclerView.adapter =
//            ListSelectionRecyclerViewAdapter(lists, this)
//    }

//    Creating an intent
    private fun showListDetail(list: TaskList){
//    Create an Intent Object using MainActivity context(this), and the class name of the activity we want to move to
        val listDetailIntent = Intent(this, ListDetailActivity::class.java)
//    provide Extras as keys with associated values you can provide to Intents
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)// pass key and data  to the intent
        startActivityForResult(listDetailIntent, LIST_DETAIL_REQUEST_CODE)// start the targeted activity
    }

    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 123
    }


    override fun listItemTapped(list: TaskList) {
//        TODO("Not yet implemented")
        showListDetail(list)
    }


}