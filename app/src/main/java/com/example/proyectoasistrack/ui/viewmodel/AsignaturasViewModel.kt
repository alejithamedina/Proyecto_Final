package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.model.Asignatura
import com.example.proyectoasistrack.data.repository.AsignaturasRepository
import kotlinx.coroutines.launch

class AsignaturasViewModel(private val repository: AsignaturasRepository) : ViewModel() {

    fun getAllAsignaturas() = repository.getAllAsignaturas()

    fun insertAsignatura(asignatura: Asignatura) {
        viewModelScope.launch {
            repository.insert(asignatura)
        }
    }

    fun updateAsignatura(asignatura: Asignatura) {
        viewModelScope.launch {
            repository.update(asignatura)
        }
    }

    fun deleteAsignatura(asignatura: Asignatura) {
        viewModelScope.launch {
            repository.delete(asignatura)
        }
    }
}



