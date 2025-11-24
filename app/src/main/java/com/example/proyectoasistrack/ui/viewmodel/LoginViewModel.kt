package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.Repository.UsuarioRepository.UsuarioRepository
import com.example.proyectoasistrack.data.model.Usuario
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsuarioRepository) : ViewModel() {

    fun login(email: String, password: String): LiveData<Boolean> {
        val resultado = MutableLiveData<Boolean>()

        viewModelScope.launch {
            repository.getUsuario(email).collect { usuario: Usuario? ->
                resultado.postValue(usuario?.password == password)
            }
        }

        return resultado
    }
}

