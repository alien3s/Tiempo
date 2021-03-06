package com.example.peliculasejemplo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasejemplo.ListFragmentDirections
import com.example.peliculasejemplo.R
import com.example.peliculasejemplo.database.City
import com.example.peliculasejemplo.model.CitysViewModel

class CityAdapter(var cities: List<City>, var cityViewModel: CitysViewModel): RecyclerView.Adapter<CityAdapter.ViewHolder>(), Filterable {

    var FilterList = ArrayList<City>()

    init{
        FilterList = cities as ArrayList<City>
    }

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(FilterList[position])
        holder.itemView.setOnClickListener {

            cityViewModel.setCitySeleccionada(FilterList[position])

            val action = ListFragmentDirections.actionListFragmentToFichaFragment()
            it.findNavController().navigate(action)

        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return FilterList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(ciudad: City) {
            val textViewNombre = itemView.findViewById<TextView>(R.id.textViewTitulo)
            textViewNombre.text = ciudad.titulo
        }
    }

    override fun getFilter(): Filter {
       return object :Filter(){
           override fun performFiltering(constraint: CharSequence?): FilterResults {
               val charSequence = constraint.toString()
               if (charSequence.isEmpty()){
                   FilterList = cities as ArrayList<City>
               }
               else{
                   val resultList = ArrayList<City>()
                   for (row in cities){
                       if (row.titulo?.toLowerCase()?.contains(charSequence.toLowerCase())!!){
                           resultList.add(row)
                       }
                   }
                   FilterList = resultList
               }
               var filterResult = FilterResults()
               filterResult.values = FilterList
               return filterResult
           }

           override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
               FilterList = results?.values as ArrayList<City>
               notifyDataSetChanged()
           }
       }
    }
}