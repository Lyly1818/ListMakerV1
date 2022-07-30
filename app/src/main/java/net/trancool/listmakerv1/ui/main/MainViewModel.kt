import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import net.trancool.listmakerv1.models.TaskList

class MainViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {

//TODO : what is this???? --> Explained the role of these lambda functions
    lateinit var onListAdded: (() -> Unit)
    lateinit var list: TaskList
    lateinit var onTaskAdded: (() -> Unit)

    val lists: MutableList<TaskList> by lazy {
        retrieveLists()
    }

//    take a string and pass it to the task list
    fun addTask(task: String) {
        list.tasks.add(task)
        updateList(list)
    }


    private fun retrieveLists(): MutableList<TaskList> {

        val sharedPreferencesContents = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in sharedPreferencesContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemsHashSet)
            taskLists.add(list)
        }

        return taskLists
    }

    fun saveList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }

    fun updateList(list: TaskList) {
//        TODO("Not yet implemented")
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
//        lists.add(list)
//        onListAdded.invoke()
    }

    fun refreshLists() {
//        TODO("Not yet implemented")
        lists.clear()
        lists.addAll(retrieveLists())
    }



}