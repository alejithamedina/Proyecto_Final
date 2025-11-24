package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.model.Asistencia
import com.example.proyectoasistrack.data.repository.AsistenciaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AsistenciaViewModel(private val repository: AsistenciaRepository) : ViewModel() {

    fun getAllAsistencias(): Flow<List<Asistencia>> = repository.getAllAsistencias()

    fun insert(asistencia: Asistencia) {
        viewModelScope.launch { repository.insert(asistencia) }
    }

    fun update(asistencia: Asistencia) {
        viewModelScope.launch { repository.update(asistencia) }
    }

    fun delete(asistencia: Asistencia) {
        viewModelScope.launch { repository.delete(asistencia) }
    }
}

