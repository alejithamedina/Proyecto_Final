package com.example.proyectoasistrack.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.Repository.UsuarioRepository.UsuarioRepository
import com.example.proyectoasistrack.data.model.Usuario
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.RegistroViewModel
import com.example.proyectoasistrack.ui.viewmodel.RegistroViewModelFactory

class RegistroActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegistrarUsuario: Button
    private lateinit var registroViewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Referencias a los elementos del layout
        etNombre = findViewById(R.id.editTextNombre)
        etApellido = findViewById(R.id.editTextApellido)
        etEmail = findViewById(R.id.editTextEmail)
        etPassword = findViewById(R.id.editTextPassword)
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario)

        // Inicializar DAO y Repository
        val usuarioDao = AppDatabase.getDatabase(application).usuarioDao()
        val repository = UsuarioRepository(usuarioDao)

        // Inicializar ViewModel
        val factory = RegistroViewModelFactory(repository)
        registroViewModel = ViewModelProvider(this, factory)[RegistroViewModel::class.java]

        // Botón Registrar
        btnRegistrarUsuario.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apellido = etApellido.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuario = Usuario(
                nombre = nombre,
                apellido = apellido,
                email = email,
                password = password
            )

            // Insertar usuario en la base de datos
            registroViewModel.insertUsuario(usuario)

            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
            finish() // Cierra la actividad y vuelve al login
        }
    }
}

