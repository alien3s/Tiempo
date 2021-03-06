package com.example.peliculasejemplo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.peliculasejemplo.database.City
import com.example.peliculasejemplo.database.DataRepository


class InsertFragment : Fragment() {

    lateinit var editTextTitulo: TextView
    lateinit var buttonInsertar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_insert, container, false)
        editTextTitulo = v.findViewById(R.id.editTextFichaTitulo)
        buttonInsertar = v.findViewById(R.id.buttonInsertar)

        buttonInsertar.setOnClickListener {
            var ciudad = City(editTextTitulo.text.toString().hashCode(), editTextTitulo.text.toString())
            val dataRepository = DataRepository(requireContext())
            dataRepository.insert(ciudad)
        }

        return v
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InsertFragment().apply {


            }
    }
}