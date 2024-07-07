package com.example.stint.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stint.R
import com.example.stint.data.TaskListAdapter
import com.example.stint.data.TasksDatabaseHandler
import com.example.stint.model.Task

class TaskListActivity : AppCompatActivity() {
    var dbHandler : TasksDatabaseHandler? = null
//    private var adapter: TaskListAdapter? = null
//    private var taskList: ArrayList<Task>? = null
//    private var taskListItems: ArrayList<Task>? = null
//    private var layoutManager: RecyclerView.LayoutManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(findViewById(R.id.my_toolbar))
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

         var adapter: TaskListAdapter? = null
         var taskList: ArrayList<Task>? = null
         var taskListItems: ArrayList<Task>? = null
         var layoutManager: RecyclerView.LayoutManager? = null

        dbHandler = TasksDatabaseHandler(this)

        taskList = ArrayList<Task>()
        taskListItems = ArrayList()
        layoutManager = LinearLayoutManager(this)
        adapter = TaskListAdapter(taskListItems ,this)

        // setup list = recyclerview
        val recyclerViewId = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewId.layoutManager = layoutManager
        recyclerViewId.adapter = adapter

        // load tasks
        taskList = dbHandler!!.readTasks()
//        taskList!!.reverse()


        for(c in taskList!!.iterator()){
            val task=Task()

//            task.id=1
//            task.taskName="Hello"
//            task.assignedBy="World"
//            task.assignedTo="from"
//            task.timeAssigned=234567890

//            task.id=c.id
            task.taskName=c.taskName
            task.assignedBy=c.assignedBy
            task.assignedTo=c.assignedTo
            //task.timeAssigned=c.timeAssigned   // ToDo: convert to human readable date   // done
            task.showHumanDate(c.timeAssigned!!)  // human readable date

            taskListItems!!.add(task)

        }

        adapter?.notifyDataSetChanged()


//        check that list is working or not
//        for(task in taskList!!){
//            Log.d("List",task.taskName.toString())
//        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.top_menu,menu)

        return true
    }
}