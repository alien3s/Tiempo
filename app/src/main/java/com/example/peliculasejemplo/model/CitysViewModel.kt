package com.example.peliculasejemplo.model

import androidx.lifecycle.ViewModel
import com.example.peliculasejemplo.database.City

class CitysViewModel: ViewModel() {
    private var citySelecionada: City

    init{
        citySelecionada = City(0, "")
    }

    fun getCitySeleccionada():City{
        return citySelecionada
    }

    fun setCitySeleccionada(city: City){
        citySelecionada = city
    }

}