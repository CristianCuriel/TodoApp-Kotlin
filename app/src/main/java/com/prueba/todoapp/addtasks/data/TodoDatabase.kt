package com.prueba.todoapp.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDatabase:RoomDatabase() {
 //DAO -> Interfaz que nos permita hacer las peticiones a la base de datos de Room
    abstract fun taskDao(): TaskDao
}