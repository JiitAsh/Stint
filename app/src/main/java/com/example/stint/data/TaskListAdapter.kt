package com.example.stint.data

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.stint.R
import com.example.stint.activity.TaskListActivity
import com.example.stint.model.Task

class TaskListAdapter(private val list:ArrayList<Task>,
                      private val context: Context)
                        :RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TaskListAdapter.ViewHolder {
        // create our view from xml file
        var view = LayoutInflater.from(context).inflate(R.layout.list_row,parent,false)

        return ViewHolder(view,context,list)
    }

    override fun onBindViewHolder(holder: TaskListAdapter.ViewHolder, position: Int) {

        holder.bindViews(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View,context:Context,list:ArrayList<Task>) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        var mContext = context    // internal context, we'll use it in Toast (btw we can use it for other purpose
        var mList = list

        var taskName = itemView.findViewById<TextView>(R.id.listTaskName) as TextView
        var assignedBy = itemView.findViewById<TextView>(R.id.listAssignedBy) as TextView
        var assignedDate = itemView.findViewById<TextView>(R.id.listDate) as TextView
        var assignedTo = itemView.findViewById<TextView>(R.id.listAssignedTo)  as TextView


        var deleteButton = itemView.findViewById<Button>(R.id.listDeleteBtn)
        var editButton = itemView.findViewById<Button>(R.id.listEditBtn)


        fun bindViews(task:Task){
            taskName.text = task.taskName
            assignedBy.text = task.assignedBy
            //assignedDate.text = task.timeAssigned.toString()  // Todo: convert to human readable date  // done
            assignedDate.text = task.showHumanDate(System.currentTimeMillis())    // human readable date
//            assignedDate.text = "Date: 20 June 2024"
            assignedTo.text = task.assignedTo

            deleteButton.setOnClickListener(this)
            editButton.setOnClickListener (this)
        }

        override fun onClick(v: View?) {

            var mPosition: Int = adapterPosition
            var task = mList[mPosition]

            when(v!!.id){
                deleteButton.id -> {

                    deleteTask(task.id!!)
                    mList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)

                }
                editButton.id -> {

                    editTask(task)

                }
            }
        }


        fun deleteTask(id: Int){
            var db: TasksDatabaseHandler = TasksDatabaseHandler(mContext)

            db.deleteTask(id)
        }


        fun editTask(task:Task){

            var dialogBuilder:AlertDialog.Builder?
            var dialog:AlertDialog?
            var dbHandler:TasksDatabaseHandler = TasksDatabaseHandler(context)

            var view = LayoutInflater.from(context).inflate(R.layout.popup,null)

            var taskName = view.findViewById<EditText>(R.id.popupEnterTaskId)
            var assignedBy = view.findViewById<EditText>(R.id.popupAssignedById)
            var assignedTo = view.findViewById<EditText>(R.id.popupAssignToId)
            var saveButtton = view.findViewById<Button>(R.id.popupSaveTaskBtn)


            dialogBuilder = AlertDialog.Builder(context).setView(view)  // builds dialog
            dialog = dialogBuilder!!.create()  // actual dialog
            dialog!!.show()   // shows the dialog


            saveButtton.setOnClickListener {
                var tName = taskName.text.toString().trim()
                var aBy = assignedBy.text.toString().trim()
                var aTo = assignedTo.text.toString().trim()

                if (!TextUtils.isEmpty(tName)
                    && !TextUtils.isEmpty(aBy)
                    && !TextUtils.isEmpty(aTo)
                ) {

//                    var task = Task()
                    task.taskName = "Task: ${tName}"
                    task.assignedBy = "Assigned By: ${aBy}"
                    task.assignedTo = "Assigned To: ${aTo}"


                    // save task to the database
                    dbHandler!!.updateTask(task)
                    notifyItemChanged(adapterPosition,task)

                    dialog!!.dismiss()

//                    startActivity(Intent(this, TaskListActivity::class.java))
//                    finish()

                } else {

                }

            }
        }

    }

}