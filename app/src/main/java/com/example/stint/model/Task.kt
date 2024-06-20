package com.example.stint.model

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
}