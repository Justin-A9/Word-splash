package com.example.hiiii.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiiii.R
import com.example.hiiii.SUB_CATEGORY

class Adapter(private val userOccupations: ArrayList<Occupations>):
    RecyclerView.Adapter<Adapter.ViewHolder>()  {

    var onItemClick : ((Occupations) -> Unit)? = null


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var mainOccupation = itemView.findViewById<TextView>(R.id.profession)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        var listOccupations: Occupations = userOccupations[position]
        holder.mainOccupation.text = listOccupations.name

        holder.itemView.setOnClickListener {

            onItemClick?.invoke(listOccupations)
        }
    }

    override fun getItemCount(): Int {
        return userOccupations.size
    }
}