package com.example.proyectoasistrack.data.Repository.UsuarioRepository


import com.example.proyectoasistrack.data.dao.UsuarioDao
import com.example.proyectoasistrack.data.model.Usuario
import kotlinx.coroutines.flow.Flow

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    fun getAll(): Flow<List<Usuario>> {
        return usuarioDao.getAll()
    }

    fun getUsuario(email: String): Flow<Usuario?> {
        return usuarioDao.getUsuarioByEmail(email)
    }

    // Agregar esta función
    suspend fun insert(usuario: Usuario) {
        usuarioDao.insert(usuario)
    }
}







