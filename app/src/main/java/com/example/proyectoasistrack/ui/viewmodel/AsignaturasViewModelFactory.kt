package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoasistrack.data.repository.AsignaturasRepository

class AsignaturasViewModelFactory(
    private val repository: AsignaturasRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AsignaturasViewModel::class.java)) {
            return AsignaturasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

