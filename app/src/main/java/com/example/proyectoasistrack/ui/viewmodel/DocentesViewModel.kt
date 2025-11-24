package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.model.Docente
import com.example.proyectoasistrack.data.repository.DocentesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DocentesViewModel(private val repository: DocentesRepository) : ViewModel() {

    fun getAllDocentes(): Flow<List<Docente>> = repository.getAllDocentes()

    fun insertDocente(docente: Docente) = viewModelScope.launch {
        repository.insertDocente(docente)
    }

    fun updateDocente(docente: Docente) = viewModelScope.launch {
        repository.updateDocente(docente)
    }

    fun deleteDocente(docente: Docente) = viewModelScope.launch {
        repository.deleteDocente(docente)
    }
}



