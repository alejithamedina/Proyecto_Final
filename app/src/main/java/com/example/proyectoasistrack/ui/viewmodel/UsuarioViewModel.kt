package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.proyectoasistrack.data.Repository.UsuarioRepository.UsuarioRepository

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    val usuarios = repository.getAll().asLiveData()

}



