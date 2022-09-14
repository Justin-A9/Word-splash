package com.example.hiiii.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiiii.R

class subCategoryAdapter(val occupations: ArrayList<sub_users>): RecyclerView.Adapter<subCategoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val sub_category = itemView.findViewById<TextView>(R.id.sub_category_textview)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): subCategoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_category_card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: subCategoryAdapter.ViewHolder, position: Int) {
       val catAdapter : sub_users = occupations[position]
        holder.sub_category.text = catAdapter.name
    }

    override fun getItemCount(): Int {
        return occupations.size
    }

}