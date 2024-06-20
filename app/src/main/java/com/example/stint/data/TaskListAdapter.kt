package com.example.stint.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stint.R
import com.example.stint.model.Task

class TaskListAdapter(private val list:ArrayList<Task>,
                      private val context: Context)
                        :RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TaskListAdapter.ViewHolder {
        // create our view from xml file
        var view = LayoutInflater.from(context).
                    inflate(R.layout.list_row,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListAdapter.ViewHolder, position: Int) {

        holder?.bindViews(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var taskName = itemView.findViewById<TextView>(R.id.listTaskName)
        var assignedBy = itemView.findViewById<TextView>(R.id.listAssignedBy)
        var assignedDate = itemView.findViewById<TextView>(R.id.listDate)
        var assignedTo = itemView.findViewById<TextView>(R.id.listAssignedTo)

        fun bindViews(task:Task){
            taskName.text = task.taskName
            assignedBy.text = task.assignedBy
            assignedDate.text = task.timeAssigned.toString()  // Todo: convert to human readable date
            assignedTo.text = task.assignedTo
        }

    }

}