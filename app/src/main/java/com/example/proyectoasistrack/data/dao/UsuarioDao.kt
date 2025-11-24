package com.example.proyectoasistrack.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectoasistrack.data.model.Usuario
import kotlinx.coroutines.flow.Flow


@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuarios")
    fun getAll(): Flow<List<Usuario>>

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    fun getUsuarioByEmail(email: String): Flow<Usuario?>
}




















