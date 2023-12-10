package com.prueba.todoapp.addtasks.dominio

import com.prueba.todoapp.addtasks.data.TaskRepository
import com.prueba.todoapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository){
    operator fun invoke(): Flow<List<TaskModel>> {
        return taskRepository.task
    }
}