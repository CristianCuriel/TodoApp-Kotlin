package com.prueba.todoapp.addtasks.dominio

import com.prueba.todoapp.addtasks.data.TaskRepository
import com.prueba.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel){
        taskRepository.add(taskModel)
    }

}