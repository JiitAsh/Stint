package com.example.stint.model

import java.text.DateFormat
import java.util.Date

class Task() {
    var taskName: String? = null
    var assignedBy: String? = null
    var assignedTo: String? = null
    var timeAssigned: Long? = null
    var id: Int? = null

    constructor(taskName: String,assignedBy: String,
                assignedTo: String,timeAssigned: Long,id: Int):this(){


                    this.taskName = taskName
                    this.assignedBy = assignedBy
                    this.assignedTo = assignedTo
                    this.timeAssigned = timeAssigned
                    this.id = id
    }

    fun showHumanDate(timeAssigned: Long):String{
        var dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
        var formattedDate: String = dateFormat.format(Date(timeAssigned).time)

        return formattedDate
    }
}