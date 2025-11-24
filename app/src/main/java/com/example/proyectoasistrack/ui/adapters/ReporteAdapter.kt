package com.example.proyectoasistrack.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Reporte

class ReporteAdapter(
    private val context: Context,
    private val reportes: List<Reporte>
) : BaseAdapter() {

    override fun getCount(): Int = reportes.size

    override fun getItem(position: Int): Any = reportes[position]

    override fun getItemId(position: Int): Long = reportes[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_reporte, parent, false)

        val reporte = reportes[position]

        val txtTitulo = view.findViewById<TextView>(R.id.titulo)
        val txtDescripcion = view.findViewById<TextView>(R.id.descripcion)
        val txtFecha = view.findViewById<TextView>(R.id.fecha)

        txtTitulo.text = reporte.titulo
        txtDescripcion.text = reporte.descripcion
        txtFecha.text = reporte.fecha

        return view
    }
}

