package com.example.peliculasejemplo

import android.app.DownloadManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.peliculasejemplo.database.DataRepository
import com.example.peliculasejemplo.model.CitysViewModel
import com.example.peliculasejemplo.pojo.Temp
import com.android.volley.Request
import org.json.JSONArray
import org.json.JSONException

class FichaFragment : Fragment() {
    lateinit var vistaT: TextView
    lateinit var editTextTitulo: EditText
    lateinit var botonBorrar: Button
    lateinit var botonModificar: Button
    private var clima=""
    private var lista= MutableLiveData <ArrayList<Temp>>()
    private var key="&appid=498cfd087f8c3eddda555945b9acf0ed"
    private var url="https://api.openweathermap.org/data/2.5/weather?q="
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_ficha, container, false)
        editTextTitulo = v.findViewById(R.id.editTextTitulo)
        botonBorrar = v.findViewById(R.id.btnBorrar)
        botonModificar = v.findViewById(R.id.btnModificar)
        lista.observe(viewLifecycleOwner, Observer {
             vistaT=v.findViewById(R.id.tiempo)
            vistaT.text=clima
        })
        var dataRepository = DataRepository(requireContext())
        var viewModel = ViewModelProvider(requireActivity()).get(CitysViewModel::class.java)
        var ciudad = viewModel.getCitySeleccionada()

        if (ciudad!=null) {
            editTextTitulo.setText(ciudad.titulo)
        }
        botonBorrar.setOnClickListener {
            ciudad.titulo?.let { it1 -> dataRepository.delete(it1) }
        }
        botonModificar.setOnClickListener {
            ciudad.titulo?.let { it1 -> dataRepository.update(it1, editTextTitulo.text.toString()) }
        }
        jsonParser(ciudad.titulo.toString())
        return v

    }
    private fun jsonParser(nombre:String){
        val url = url+nombre+key

        val requestQueue = Volley.newRequestQueue(context)

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try{
                    
                    var listaUsuarios = ArrayList<Temp>()
                    val weather= response.getJSONArray("weather")
                    if (weather != null) {
                        for (i in 0 until weather.length()) {
                            val tiempo = weather.getJSONObject(i)
                            clima = tiempo.getString("description")
                        }
                    }

                    listaUsuarios.add(Temp(clima))
                    lista.value = listaUsuarios
                }
                catch(e: JSONException){
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error->
                error.printStackTrace()
            })

        requestQueue.add(request)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FichaFragment().apply {}
    }
}


