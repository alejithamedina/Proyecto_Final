package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.model.Estudiante
import com.example.proyectoasistrack.data.repository.EstudiantesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EstudiantesViewModel(private val repository: EstudiantesRepository) : ViewModel() {

    fun getAllEstudiantes(): Flow<List<Estudiante>> {
        return repository.getAllEstudiantes()
    }

    fun insertEstudiante(estudiante: Estudiante) = viewModelScope.launch {
        repository.insert(estudiante)
    }

    fun updateEstudiante(estudiante: Estudiante) = viewModelScope.launch {
        repository.update(estudiante)
    }

    fun deleteEstudiante(estudiante: Estudiante) = viewModelScope.launch {
        repository.delete(estudiante)
    }
}

