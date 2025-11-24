// RegistroViewModel.kt
package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.Repository.UsuarioRepository.UsuarioRepository
import com.example.proyectoasistrack.data.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistroViewModel(private val repository: UsuarioRepository) : ViewModel() {

    fun insertUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(usuario)
        }
    }
}
