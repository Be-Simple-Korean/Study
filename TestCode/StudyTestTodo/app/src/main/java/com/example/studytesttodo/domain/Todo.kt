package com.example.studytesttodo.domain

data class Todo(
    val id:Int,
    val title:String,
    val isDone:Boolean = false
)