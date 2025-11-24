package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.proyectoasistrack.data.Repository.UsuarioRepository.UsuarioRepository
import com.example.proyectoasistrack.data.model.Usuario


class PerfilViewModel(private val repository: UsuarioRepository) : ViewModel() {

    // Devuelve un LiveData<Usuario?> a partir del Flow
    fun getUsuario(email: String): androidx.lifecycle.LiveData<Usuario?> {
        return repository.getUsuario(email).asLiveData()
    }
}



