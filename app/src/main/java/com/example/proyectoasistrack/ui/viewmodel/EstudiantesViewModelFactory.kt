package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoasistrack.data.repository.EstudiantesRepository

class EstudiantesViewModelFactory(
    private val repository: EstudiantesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EstudiantesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EstudiantesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
