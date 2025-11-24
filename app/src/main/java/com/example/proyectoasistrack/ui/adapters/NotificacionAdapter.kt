package com.example.proyectoasistrack.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Notificacion

class NotificacionAdapter(private val data: List<Notificacion>) :
    RecyclerView.Adapter<NotificacionAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtTipo = v.findViewById<TextView>(R.id.txtTipo)
        val txtDetalle = v.findViewById<TextView>(R.id.txtDetalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notificacion, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val n = data[position]
        holder.txtTipo.text = n.tipo
        holder.txtDetalle.text = "${n.fecha} | ${n.descripcion} | Motivo: ${n.motivo}"
    }

    override fun getItemCount() = data.size
}
