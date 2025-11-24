package com.example.proyectoasistrack.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoasistrack.R

import com.example.proyectoasistrack.data.Repository.UsuarioRepository.UsuarioRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.PerfilViewModel
import com.example.proyectoasistrack.ui.viewmodel.PerfilViewModelFactory

class PerfilActivity : AppCompatActivity() {

    private lateinit var textViewNombre: TextView
    private lateinit var textViewApellido: TextView
    private lateinit var textViewEmail: TextView

    private lateinit var perfilViewModel: PerfilViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Referencias de los TextViews
        textViewNombre = findViewById(R.id.textViewNombre)
        textViewApellido = findViewById(R.id.textViewApellido)
        textViewEmail = findViewById(R.id.textViewEmail)

        // Inicializar ViewModel
        val db = AppDatabase.getDatabase(this)
        val repository = UsuarioRepository(db.usuarioDao())
        val factory = PerfilViewModelFactory(repository)
        perfilViewModel = factory.create(PerfilViewModel::class.java)

        // Supongamos que tienes el email del usuario logueado
        val emailUsuario = "usuario@ejemplo.com"

        // Observar cambios del usuario
        perfilViewModel.getUsuario(emailUsuario).observe(this) { usuario ->
            usuario?.let {
                textViewNombre.text = it.nombre
                textViewApellido.text = it.apellido
                textViewEmail.text = it.email
            }
        }
    }
}













