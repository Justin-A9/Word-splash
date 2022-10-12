package com.example.squash.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squash.R
import com.example.squash.datasource.SubCategories
import kotlin.collections.ArrayList

class SubCategoryAdapter(private var subCategories: ArrayList<SubCategories>): RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val subCategoryName: TextView = itemView.findViewById(R.id.sub_category_textview)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sub_category_card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val text = subCategories[position]
        holder.subCategoryName.text = text.name
    }

    override fun getItemCount(): Int {
        return subCategories.size
    }


    fun listFilter(filterList: ArrayList<SubCategories>){
        subCategories = filterList
        notifyDataSetChanged()
    }
    }

