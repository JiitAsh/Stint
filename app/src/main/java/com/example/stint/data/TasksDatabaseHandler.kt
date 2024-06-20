package com.example.stint.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.stint.model.DATABASE_NAME
import com.example.stint.model.DATABASE_VERSION
import com.example.stint.model.KEY_ID
import com.example.stint.model.KEY_TASK_ASSIGNED_BY
import com.example.stint.model.KEY_TASK_ASSIGNED_TIME
import com.example.stint.model.KEY_TASK_ASSIGNED_TO
import com.example.stint.model.KEY_TASK_NAME
import com.example.stint.model.TABLE_NAME

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

    }


}