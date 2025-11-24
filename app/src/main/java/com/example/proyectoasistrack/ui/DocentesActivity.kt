package com.example.proyectoasistrack.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Docente
import com.example.proyectoasistrack.data.repository.DocentesRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.DocentesViewModel
import com.example.proyectoasistrack.ui.viewmodel.DocentesViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DocentesActivity : AppCompatActivity() {

    private lateinit var contenedorDocentes: LinearLayout
    private lateinit var docenteViewModel: DocentesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_docentes)

        contenedorDocentes = findViewById(R.id.contenedorDocentes)
        val btnAgregar: Button = findViewById(R.id.btnAgregar)
        val btnDevolver: ImageButton = findViewById(R.id.btnDevolverDocentes)

        // --- Botón devolver solo icono ---
        btnDevolver.background = null
        btnDevolver.adjustViewBounds = true
        btnDevolver.scaleType = ImageView.ScaleType.CENTER_INSIDE
        btnDevolver.setPadding(0, 0, 0, 0)
        btnDevolver.setOnClickListener { finish() }

        // --- DAO y ViewModel ---
        val docenteDao = AppDatabase.getDatabase(application).docenteDao()
        val repository = DocentesRepository(docenteDao)
        val factory = DocentesViewModelFactory(repository)
        docenteViewModel = ViewModelProvider(this, factory)[DocentesViewModel::class.java]

        // --- Botón agregar ---
        btnAgregar.setOnClickListener {
            mostrarDialogoAgregar(null)
        }

        // --- Observar lista ---
        lifecycleScope.launch {
            docenteViewModel.getAllDocentes().collect { lista ->
                mostrarDocentes(lista)
            }
        }
    }

    private fun mostrarDocentes(lista: List<Docente>) {
        contenedorDocentes.removeAllViews()

        for (docente in lista) {
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }

            val tvNombre = TextView(this).apply {
                text = "${docente.nombre} ${docente.apellido}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
                textSize = 18f
            }

            val tvEmail = TextView(this).apply {
                text = docente.email
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            // --- Botones iconos ---
            val layoutBotones = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.END
            }

            val btnEditar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_editar)
                background = null
                layoutParams = LinearLayout.LayoutParams(dp(48), dp(48))
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setPadding(0, 0, 0, 0)
                setOnClickListener { mostrarDialogoAgregar(docente) }
            }

            val btnEliminar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_eliminar)
                background = null
                layoutParams = LinearLayout.LayoutParams(dp(48), dp(48))
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setPadding(0, 0, 0, 0)
                setOnClickListener { docenteViewModel.deleteDocente(docente) }
            }

            layoutBotones.addView(btnEditar)
            layoutBotones.addView(btnEliminar)

            // --- Agregar views al layout principal ---
            layout.addView(tvNombre)
            layout.addView(tvEmail)
            layout.addView(layoutBotones)

            contenedorDocentes.addView(layout)
        }
    }

    private fun mostrarDialogoAgregar(docente: Docente?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (docente == null) "Agregar Docente" else "Editar Docente")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val etNombre = EditText(this).apply {
            hint = "Nombre"
            docente?.let { setText(it.nombre) }
        }

        val etApellido = EditText(this).apply {
            hint = "Apellido"
            docente?.let { setText(it.apellido) }
        }

        val etEmail = EditText(this).apply {
            hint = "Email"
            docente?.let { setText(it.email) }
        }

        layout.addView(etNombre)
        layout.addView(etApellido)
        layout.addView(etEmail)

        builder.setView(layout)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val email = etEmail.text.toString()

            if (docente == null) {
                docenteViewModel.insertDocente(
                    Docente(
                        nombre = nombre,
                        apellido = apellido,
                        email = email
                    )
                )
            } else {
                docenteViewModel.updateDocente(
                    docente.copy(
                        nombre = nombre,
                        apellido = apellido,
                        email = email
                    )
                )
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }

    // --- Función para dp ---
    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}

















