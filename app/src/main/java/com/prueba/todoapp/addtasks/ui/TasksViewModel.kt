package com.prueba.todoapp.addtasks.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prueba.todoapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor():ViewModel() {

    private val _ShowDialog = MutableLiveData<Boolean>()
    val ShowDialog:LiveData<Boolean> = _ShowDialog

    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks:List<TaskModel> = _tasks


    fun onDialogClose() {
        _ShowDialog.value = false
    }

    fun onTasksCreated(task: String) {
        _ShowDialog.value = false
        _tasks.add(TaskModel(task = task))
    }

    fun onShowDialogClick() {
        _ShowDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].let{
            it.copy(selected = !it.selected)
        }

    }

    fun onItemRemove(taskModel: TaskModel) {
        val task = _tasks.find{ it.id == taskModel.id} //Buscame en las tareas un ID que sea igual dentro del listado
        _tasks.remove(task)
    }


}