package com.example.s8107356assessment2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8107356assessment2.R
import com.example.s8107356assessment2.model.DashboardEntity

class DashboardAdapter(
    private val items: List<DashboardEntity>
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    inner class DashboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumText: TextView = itemView.findViewById(R.id.text_album)
        val artistText: TextView = itemView.findViewById(R.id.text_artist)

        fun bind(entity: DashboardEntity) {
            albumText.text = entity.albumTitle
            artistText.text = entity.artistName
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dashboard_entity, parent, false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
