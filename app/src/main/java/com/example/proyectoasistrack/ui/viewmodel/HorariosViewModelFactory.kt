package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoasistrack.data.repository.HorariosRepository

class HorariosViewModelFactory(
    private val repository: HorariosRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HorariosViewModel(repository) as T
    }
}
