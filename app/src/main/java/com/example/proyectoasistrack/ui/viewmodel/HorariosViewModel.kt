package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.model.Horario
import com.example.proyectoasistrack.data.repository.HorariosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HorariosViewModel(private val repository: HorariosRepository) : ViewModel() {

    fun getAllHorarios(): Flow<List<Horario>> = repository.getAllHorarios()

    fun insertHorario(horario: Horario) {
        viewModelScope.launch { repository.insertHorario(horario) }
    }

    fun updateHorario(horario: Horario) {
        viewModelScope.launch { repository.updateHorario(horario) }
    }

    fun deleteHorario(horario: Horario) {
        viewModelScope.launch { repository.deleteHorario(horario) }
    }
}

