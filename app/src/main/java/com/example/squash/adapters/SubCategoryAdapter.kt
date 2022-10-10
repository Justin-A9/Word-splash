package com.example.squash.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squash.R
import com.example.squash.data.SubUsers

class SubCategoryAdapter(val occupations: ArrayList<SubUsers>): RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val sub_category = itemView.findViewById<TextView>(R.id.sub_category_textview)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_category_card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val catAdapter : SubUsers = occupations[position]
        holder.sub_category.text = catAdapter.name
    }

    override fun getItemCount(): Int {
        return occupations.size
    }

}