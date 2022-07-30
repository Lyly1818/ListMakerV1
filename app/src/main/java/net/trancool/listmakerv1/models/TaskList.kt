package net.trancool.listmakerv1.models

import android.os.Parcel
import android.os.Parcelable
//Implement parcelable here breakdown the TaskList Object into Int, String, ... that the intent can handle
class TaskList(val name: String, val tasks : ArrayList<String> = ArrayList()): Parcelable {
//    Take a Parcel extract values: title and taskList and pass them to the primary constructor
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.createStringArrayList()!!
    )
    override fun describeContents() = 0

//Creates Parcel from taskList object
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(tasks)
    }



    companion object CREATOR : Parcelable.Creator<TaskList> {
//        Create taskList object from Parcel
        override fun createFromParcel(source: Parcel): TaskList {
            return TaskList(source)
        }

        override fun newArray(size: Int): Array<TaskList?> {
            return arrayOfNulls(size)
        }
    }

}