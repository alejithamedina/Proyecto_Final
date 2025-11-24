package com.example.proyectoasistrack.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Reporte
import com.example.proyectoasistrack.data.repository.ReportesRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.ReportesViewModel
import com.example.proyectoasistrack.ui.viewmodel.ReportesViewModelFactory
import kotlinx.coroutines.launch

class ReportesActivity : AppCompatActivity() {

    private lateinit var contenedorReportes: LinearLayout
    private lateinit var reportesViewModel: ReportesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)

        contenedorReportes = findViewById(R.id.contenedorReportes)
        val btnAgregar: Button = findViewById(R.id.btnAgregar)

        val reporteDao = AppDatabase.getDatabase(application).reporteDao()
        val repository = ReportesRepository(reporteDao)
        val factory = ReportesViewModelFactory(repository)
        reportesViewModel = ViewModelProvider(this, factory)[ReportesViewModel::class.java]

        btnAgregar.setOnClickListener { mostrarDialogoAgregar(null) }

        // Botón Devolver al final
        val btnDevolver = findViewById<ImageButton>(R.id.btnDevolverReportes)
        btnDevolver.setOnClickListener { finish() }

        lifecycleScope.launch {
            reportesViewModel.getAllReportes().collect { lista ->
                mostrarReportes(lista)
            }
        }
    }

    private fun mostrarReportes(lista: List<Reporte>) {
        contenedorReportes.removeAllViews()

        for (reporte in lista) {
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }

            val tvTitulo = TextView(this).apply {
                text = "Título: ${reporte.titulo}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }
            val tvDescripcion = TextView(this).apply {
                text = "Descripción: ${reporte.descripcion}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }
            val tvFecha = TextView(this).apply {
                text = "Fecha: ${reporte.fecha}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            val layoutBotones = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.END
            }

            val btnEditar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_editar)
                background = null
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = LinearLayout.LayoutParams(200, 200)
                setOnClickListener { mostrarDialogoAgregar(reporte) }
            }

            val btnEliminar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_eliminar)
                background = null
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = LinearLayout.LayoutParams(200, 200)
                setOnClickListener { reportesViewModel.deleteReporte(reporte) }
            }

            layoutBotones.addView(btnEditar)
            layoutBotones.addView(btnEliminar)

            layout.addView(tvTitulo)
            layout.addView(tvDescripcion)
            layout.addView(tvFecha)
            layout.addView(layoutBotones)

            contenedorReportes.addView(layout)
        }
    }

    private fun mostrarDialogoAgregar(reporte: Reporte?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (reporte == null) "Agregar Reporte" else "Editar Reporte")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val etTitulo = EditText(this).apply { hint = "Título"; reporte?.let { setText(it.titulo) } }
        val etDescripcion = EditText(this).apply { hint = "Descripción"; reporte?.let { setText(it.descripcion) } }
        val etFecha = EditText(this).apply { hint = "Fecha"; reporte?.let { setText(it.fecha) } }

        layout.addView(etTitulo)
        layout.addView(etDescripcion)
        layout.addView(etFecha)

        builder.setView(layout)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val nuevoReporte = Reporte(
                id = reporte?.id ?: 0,
                titulo = etTitulo.text.toString(),
                descripcion = etDescripcion.text.toString(),
                fecha = etFecha.text.toString()
            )
            if (reporte == null)
                reportesViewModel.insertReporte(nuevoReporte)
            else
                reportesViewModel.updateReporte(nuevoReporte)

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }
}




