package com.prueba.todoapp.addtasks.ui

import com.prueba.todoapp.addtasks.ui.model.TaskModel

sealed interface TaskUiState{

    object Loading:TaskUiState
    data class Error(val throwable: Throwable): TaskUiState
    data class Success(val task: List<TaskModel>) : TaskUiState

}