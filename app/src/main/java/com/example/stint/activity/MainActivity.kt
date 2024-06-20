package com.example.stint.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stint.R
import com.example.stint.data.TasksDatabaseHandler
import com.example.stint.model.Task

class MainActivity : AppCompatActivity() {

    var dbHandler : TasksDatabaseHandler? = null
//    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        progressDialog = ProgressDialog(this)

        dbHandler = TasksDatabaseHandler(this)

        var enterTask = findViewById<EditText>(R.id.enterTaskId)
        var assignTo = findViewById<EditText>(R.id.assignToId)
        var assignedBy = findViewById<EditText>(R.id.assignedById)
        var saveATask = findViewById<Button>(R.id.saveTaskBtn)

        saveATask.setOnClickListener {
            /* ToDo: remove progress dialog, it's not working properly */
//            progressDialog!!.setMessage("Saving Task...")
//            progressDialog!!.show()

            if(!TextUtils.isEmpty(enterTask.text.toString())
                && !TextUtils.isEmpty(assignTo.text.toString())
                && !TextUtils.isEmpty(assignedBy.text.toString())){

                // save task to the database
                var task = Task()
                task.taskName = enterTask.text.toString()
                task.assignedBy = assignedBy.text.toString()
                task.assignedTo = assignTo.text.toString()
//                task.taskName = findViewById<EditText>(R.id.enterTaskId).text.toString()
//                task.assignedBy = findViewById<EditText>(R.id.assignedById).text.toString()
//                task.assignedTo = findViewById<EditText>(R.id.assignToId).text.toString()

                saveToDB(task)
//                progressDialog!!.cancel()
                startActivity(Intent(this,TaskListActivity::class.java))

            }else{
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }




//        var task = Task()
//        task.taskName = "Clean Room"
//        task.assignedBy = "Jitesh"
//        task.assignedTo = "Kumawat"
//        //dbHandler!!.createTask(task)
//
//
//        // Read from database
//        var tasks: Task = dbHandler!!.readATask(1)
//
//        Log.d("Item: ",tasks.taskName.toString())
    }


    fun saveToDB(task:Task){
        dbHandler!!.createTask(task)
    }
}