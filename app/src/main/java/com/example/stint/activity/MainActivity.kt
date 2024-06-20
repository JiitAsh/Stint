package com.example.stint.activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stint.R
import com.example.stint.data.TasksDatabaseHandler
import com.example.stint.model.Task

class MainActivity : AppCompatActivity() {

    var dbHandler : TasksDatabaseHandler? = null
    var enterTask = findViewById<EditText>(R.id.enterTaskId)
    var assignTo = findViewById<EditText>(R.id.assignToId)
    var assignedBy = findViewById<EditText>(R.id.assignedById)
    var saveATask = findViewById<Button>(R.id.saveTaskBtn)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHandler = TasksDatabaseHandler(this)

        saveATask.setOnClickListener {

            if(!TextUtils.isEmpty(enterTask.text.toString())
                && !TextUtils.isEmpty(assignTo.text.toString())
                && !TextUtils.isEmpty(assignedBy.text.toString())){

                // save task to the database

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
}