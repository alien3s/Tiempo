package com.example.peliculasejemplo

import android.app.DownloadManager
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasejemplo.adapters.CityAdapter
import com.example.peliculasejemplo.database.DataRepository
import com.example.peliculasejemplo.model.CitysViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException
import com.example.peliculasejemplo.database.City
import com.example.peliculasejemplo.pojo.Temp
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.android.volley.Request
import com.android.volley.Request.Method.GET
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class ListFragment : Fragment() {

    lateinit var recyclerViewLista: RecyclerView

    lateinit var adapter: CityAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_list, container, false)
        recyclerViewLista = v.findViewById(R.id.recyclerviewlista)

        listaCiudades()

        var fab = v.findViewById<FloatingActionButton>(R.id.floatingActionButton)

        fab.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToInsertFragment()
            findNavController().navigate(action)
        }

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        var menuItemSearch = menu.findItem(R.id.app_bar_search)
        menuItemSearch.setVisible(true)

        var searchView = menuItemSearch.actionView as SearchView
        searchView.queryHint = "Buscar ciudad"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }



    private fun listaCiudades(){
        var dataRepository = DataRepository(requireContext())
        var ciudad = dataRepository.getCity()
        var viewModel = ViewModelProvider(requireActivity()).get(CitysViewModel::class.java)
        adapter = CityAdapter(ciudad, viewModel)
        recyclerViewLista.adapter = adapter
        recyclerViewLista.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {  }
    }

}