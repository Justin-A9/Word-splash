package com.example.hiiii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiiii.databinding.ActivitySubCategoryBinding
import com.example.hiiii.fragments.subCategoryAdapter
import com.example.hiiii.fragments.sub_users
import java.util.*
import kotlin.collections.ArrayList

class SUB_CATEGORY : AppCompatActivity() {

    val sub_professions = ArrayList<sub_users>()

    val  displayList = ArrayList<sub_users>()
    private lateinit var binding: ActivitySubCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val recycler = binding.subRecycler
        recycler.layoutManager = LinearLayoutManager(this)




        sub_professions.add(sub_users("Victor"))
        sub_professions.add(sub_users("Joshua"))
        sub_professions.add(sub_users("Great"))
        sub_professions.add(sub_users("Victor"))
        val adapter = subCategoryAdapter(displayList)

        displayList.addAll(sub_professions)
        recycler.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.recyclermenu, menu)
        val menuItem = menu!!.findItem(R.id.search)

        if (menuItem != null){
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()){

                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        sub_professions.forEach {

                            if (it.name.toLowerCase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }

                        }
                        binding.subRecycler.adapter!!.notifyDataSetChanged()

                    }else{
                        displayList.clear()
                        displayList.addAll(sub_professions)
                        binding.subRecycler.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }


            })
        }


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}