package com.example.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OceaniaListAdapter (val data : OceaniaCountryList) : RecyclerView.Adapter<OceaniaListAdapter.ViewHolder>(){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.oceania_view_details, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.country.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}