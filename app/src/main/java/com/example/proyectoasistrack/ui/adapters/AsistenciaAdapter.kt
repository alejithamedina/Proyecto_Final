package com.example.proyectoasistrack.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.BaseAdapter
import com.example.proyectoasistrack.R

class AsistenciaAdapter(
    private val context: Context,
    private val estudiantes: List<String>,
    private val onPresente: (String) -> Unit,
    private val onAusente: (String) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = estudiantes.size

    override fun getItem(position: Int): Any = estudiantes[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_asistencia, parent, false)

        val txtNombre = view.findViewById<TextView>(R.id.txtNombreEstudiante)
        val btnPresente = view.findViewById<Button>(R.id.btnPresente)
        val btnAusente = view.findViewById<Button>(R.id.btnAusente)

        val estudiante = estudiantes[position]
        txtNombre.text = estudiante

        btnPresente.setOnClickListener { onPresente(estudiante) }
        btnAusente.setOnClickListener { onAusente(estudiante) }

        return view
    }
}

