package com.example.proyectoasistrack.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.Repository.UsuarioRepository.UsuarioRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.LoginViewModel
import com.example.proyectoasistrack.ui.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegistrar: Button
    private lateinit var btnOlvide: Button
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a los elementos del layout
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        btnLogin = findViewById(R.id.login)           // id del botón Iniciar sesión
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnOlvide = findViewById(R.id.btnOlvide)

        // Inicializar DAO y Repository
        val usuarioDao = AppDatabase.getDatabase(application).usuarioDao()
        val repository = UsuarioRepository(usuarioDao)

        // Inicializar ViewModel
        val factory = LoginViewModelFactory(repository)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        // Botón Iniciar Sesión
        btnLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            loginViewModel.login(email, password).observe(this) { success ->
                if (success) {
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Botón Registrarse
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        // Botón Olvidé mi contraseña
        btnOlvide.setOnClickListener {
            val intent = Intent(this, RecuperarContrasenaActivity::class.java)
            startActivity(intent)
        }
    }
}










