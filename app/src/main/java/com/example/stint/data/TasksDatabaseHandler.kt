package com.example.stint.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.stint.model.DATABASE_NAME
import com.example.stint.model.DATABASE_VERSION
import com.example.stint.model.KEY_ID
import com.example.stint.model.KEY_TASK_ASSIGNED_BY
import com.example.stint.model.KEY_TASK_ASSIGNED_TIME
import com.example.stint.model.KEY_TASK_ASSIGNED_TO
import com.example.stint.model.KEY_TASK_NAME
import com.example.stint.model.TABLE_NAME
import com.example.stint.model.Task
import java.text.DateFormat
import java.util.Date

class TasksDatabaseHandler(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        // create tasks table
        var CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TASK_NAME + " TEXT," +
                KEY_TASK_ASSIGNED_BY + " TEXT," +
                KEY_TASK_ASSIGNED_TO + " TEXT," +
                KEY_TASK_ASSIGNED_TIME + " LONG" + ")"

        db?.execSQL(CREATE_TASKS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    /*

    CRUD operations
     */

    fun createTask(task: Task){
        val db:SQLiteDatabase = writableDatabase

        val values: ContentValues = ContentValues()
        values.put(KEY_TASK_NAME,task.taskName)
        values.put(KEY_TASK_ASSIGNED_BY,task.assignedBy)
        values.put(KEY_TASK_ASSIGNED_TO,task.assignedTo)
        values.put(KEY_TASK_ASSIGNED_TIME,System.currentTimeMillis())

        db.insert(TABLE_NAME,null,values)

        Log.d("DATA INSERTED","SUCCESS")
        db.close()
    }

    fun readATask(id: Int):Task{
        val db: SQLiteDatabase = writableDatabase
        val cursor: Cursor = db.query(TABLE_NAME, arrayOf(KEY_ID,
            KEY_TASK_NAME, KEY_TASK_ASSIGNED_BY,
            KEY_TASK_ASSIGNED_TIME, KEY_TASK_ASSIGNED_TO),
            KEY_ID + "=?", arrayOf(id.toString()),
            null,null,null,null)


        if(cursor != null)
            cursor.moveToFirst()

        val task = Task()
        task.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
        task.taskName = cursor.getString(cursor.getColumnIndex(KEY_TASK_NAME))
        task.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_TASK_ASSIGNED_BY))
        task.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_TASK_ASSIGNED_TO))
        task.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_TASK_ASSIGNED_TIME))


//        val dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
//        var formattedDate = dateFormat.format(Date(cursor.
//            getLong(cursor.getColumnIndex(KEY_TASK_ASSIGNED_TIME))).time)
//

        return task

    }

    fun readTasks():ArrayList<Task>{
        val db: SQLiteDatabase = readableDatabase
        var list: ArrayList<Task> = ArrayList()

        // select all tasks from table
        var selectAll = "SELECT * FROM " + TABLE_NAME
        var cursor: Cursor = db.rawQuery(selectAll,null)

        // loop through our tasks
        if(cursor.moveToFirst()){
            do{
                var task = Task()

                task.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                task.taskName = cursor.getString(cursor.getColumnIndex(KEY_TASK_NAME))
                task.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_TASK_ASSIGNED_BY))
                task.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_TASK_ASSIGNED_TO))
                task.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_TASK_ASSIGNED_TIME))

                list!!.add(task)
            }while (cursor.moveToNext())
        }

        return list
    }


    fun updateTask(task: Task):Int{
        var db: SQLiteDatabase = writableDatabase

        val values: ContentValues = ContentValues()
        values.put(KEY_TASK_NAME,task.taskName)
        values.put(KEY_TASK_ASSIGNED_BY,task.assignedBy)
        values.put(KEY_TASK_ASSIGNED_TO,task.assignedTo)
        values.put(KEY_TASK_ASSIGNED_TIME,System.currentTimeMillis())


        // update a row
        return db.update(TABLE_NAME,values,KEY_ID+"=?",
            arrayOf(task.id.toString()))
    }


    fun deleteTask(task: Task){
        var db: SQLiteDatabase = writableDatabase

        db.delete(TABLE_NAME,KEY_ID+"=?", arrayOf(task.id.toString()))
        db.close()
    }

    fun getTaskCount():Int{
        var db:SQLiteDatabase = readableDatabase
        var countQuery = "SELECT * FROM " + TABLE_NAME
        var cursor: Cursor = db.rawQuery(countQuery,null)

        return cursor.count
    }
}