package com.example.proyectoasistrack.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Asignatura
import com.example.proyectoasistrack.data.repository.AsignaturasRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.AsignaturasViewModel
import com.example.proyectoasistrack.ui.viewmodel.AsignaturasViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AsignaturasActivity : AppCompatActivity() {

    private lateinit var contenedorAsignaturas: LinearLayout
    private lateinit var asignaturaViewModel: AsignaturasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignaturas)

        contenedorAsignaturas = findViewById(R.id.contenedorAsignaturas)
        val btnAgregar: Button = findViewById(R.id.btnAgregarAsignatura)

        // BOTÓN DEVOLVER
        val btnDevolver: ImageButton = findViewById(R.id.btnDevolverAsignaturas)
        btnDevolver.setOnClickListener { finish() }

        // DAO + REPOSITORY
        val dao = AppDatabase.getDatabase(application).asignaturaDao()
        val repo = AsignaturasRepository(dao)

        // ViewModel
        val factory = AsignaturasViewModelFactory(repo)
        asignaturaViewModel = ViewModelProvider(this, factory)[AsignaturasViewModel::class.java]

        // Agregar
        btnAgregar.setOnClickListener {
            mostrarDialogoAsignatura(null)
        }

        // LISTEN CHANGES
        lifecycleScope.launch {
            asignaturaViewModel.getAllAsignaturas().collect {
                mostrarAsignaturas(it)
            }
        }
    }

    private fun mostrarAsignaturas(lista: List<Asignatura>) {
        contenedorAsignaturas.removeAllViews()

        for (asignatura in lista) {

            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.setPadding(16, 16, 16, 16)

            val tvNombre = TextView(this)
            tvNombre.text = "Nombre: ${asignatura.nombre}"
            tvNombre.setTextColor(getColor(R.color.azul_oscuro))

            val tvSalon = TextView(this)
            tvSalon.text = "Salón: ${asignatura.salon}"
            tvSalon.setTextColor(getColor(R.color.azul_oscuro))

            val tvHorario = TextView(this)
            tvHorario.text = "Horario: ${asignatura.horario}"
            tvHorario.setTextColor(getColor(R.color.azul_oscuro))

            val botonesLayout = LinearLayout(this)
            botonesLayout.orientation = LinearLayout.HORIZONTAL
            botonesLayout.gravity = Gravity.END

            // EDITAR
            val btnEditar = ImageButton(this)
            btnEditar.setImageResource(R.drawable.ic_editar)
            btnEditar.adjustViewBounds = true
            btnEditar.scaleType = ImageView.ScaleType.CENTER_INSIDE
            btnEditar.background = null
            btnEditar.setPadding(0, 0, 0, 0)
            btnEditar.layoutParams = LinearLayout.LayoutParams(dp(40), dp(40))
            btnEditar.setOnClickListener {
                mostrarDialogoAsignatura(asignatura)
            }

// ELIMINAR
            val btnEliminar = ImageButton(this)
            btnEliminar.setImageResource(R.drawable.ic_eliminar)
            btnEliminar.adjustViewBounds = true
            btnEliminar.scaleType = ImageView.ScaleType.CENTER_INSIDE
            btnEliminar.background = null
            btnEliminar.setPadding(0, 0, 0, 0)
            btnEliminar.layoutParams = LinearLayout.LayoutParams(dp(40), dp(40))
            btnEliminar.setOnClickListener {
                asignaturaViewModel.deleteAsignatura(asignatura)
            }


            botonesLayout.addView(btnEditar)
            botonesLayout.addView(btnEliminar)

            layout.addView(tvNombre)
            layout.addView(tvSalon)
            layout.addView(tvHorario)
            layout.addView(botonesLayout)

            contenedorAsignaturas.addView(layout)
        }
    }

    private fun mostrarDialogoAsignatura(asignatura: Asignatura?) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (asignatura == null) "Agregar Asignatura" else "Editar Asignatura")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(16, 16, 16, 16)

        val etNombre = EditText(this)
        etNombre.hint = "Nombre"
        etNombre.setText(asignatura?.nombre ?: "")

        val etSalon = EditText(this)
        etSalon.hint = "Salón"
        etSalon.setText(asignatura?.salon ?: "")

        val etHorario = EditText(this)
        etHorario.hint = "Horario"
        etHorario.setText(asignatura?.horario ?: "")

        layout.addView(etNombre)
        layout.addView(etSalon)
        layout.addView(etHorario)

        builder.setView(layout)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val nombre = etNombre.text.toString()
            val salon = etSalon.text.toString()
            val horario = etHorario.text.toString()

            if (asignatura == null) {
                asignaturaViewModel.insertAsignatura(
                    Asignatura(nombre = nombre, salon = salon, horario = horario)
                )
            } else {
                asignaturaViewModel.updateAsignatura(
                    asignatura.copy(nombre = nombre, salon = salon, horario = horario)
                )
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }
    private fun dp(value: Int): Int {
        return (value * resources.displayMetrics.density).toInt()
    }

}












