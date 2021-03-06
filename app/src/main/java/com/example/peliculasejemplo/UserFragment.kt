package com.example.peliculasejemplo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.peliculasejemplo.database.DataRepository

class UserFragment : Fragment() {

    lateinit var editTextNombre: EditText
    lateinit var editTextContrasena: EditText
    lateinit var textViewUser: TextView
    lateinit var buttonUpdate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user, container, false)

        editTextNombre = v.findViewById(R.id.editTextNombreUsr)
        editTextContrasena = v.findViewById(R.id.editTextContrasenaUsr)
        textViewUser = v.findViewById(R.id.textViewUser)
        buttonUpdate = v.findViewById(R.id.buttonUpdate)



        var preferences = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
        var user = preferences?.getString("user", "")
        textViewUser.text = user.toString()


        buttonUpdate.setOnClickListener {

            var nombre = editTextNombre.text.toString()
            var contrasena = editTextContrasena.text.toString()

            val dataRepository = DataRepository(requireContext())
            dataRepository.updateUsr(nombre,contrasena, user.toString())
        }

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                UserFragment().apply {}
    }
}