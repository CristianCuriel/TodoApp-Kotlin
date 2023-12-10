package com.prueba.todoapp.addtasks.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.todoapp.addtasks.dominio.AddTaskUseCase
import com.prueba.todoapp.addtasks.dominio.DeleteTaskUseCase
import com.prueba.todoapp.addtasks.dominio.GetTasksUseCase
import com.prueba.todoapp.addtasks.dominio.UpdateTaskUseCase
import com.prueba.todoapp.addtasks.ui.TaskUiState.Success
import com.prueba.todoapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase
):ViewModel() {

    val uiState: StateFlow<TaskUiState> = getTasksUseCase().map( ::Success )
        .catch { TaskUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TaskUiState.Loading)

    private val _ShowDialog = MutableLiveData<Boolean>()
    val ShowDialog:LiveData<Boolean> = _ShowDialog

//    private val _tasks = mutableStateListOf<TaskModel>()
//    val tasks:List<TaskModel> = _tasks


    fun onDialogClose() {
        _ShowDialog.value = false
    }

    fun onTasksCreated(task: String) {
        _ShowDialog.value = false
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _ShowDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
/*
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let{
            it.copy(selected = !it.selected)
        }
*/
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }


    }

    fun onItemRemove(taskModel: TaskModel) {

        viewModelScope.launch {
            deleteTaskUseCase(taskModel)
        }

    }


}