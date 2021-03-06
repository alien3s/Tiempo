package com.example.peliculasejemplo

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.peliculasejemplo.database.DataRepository
import com.example.peliculasejemplo.database.Usuario

class RegisterFragment : Fragment() {

    lateinit var editTextUsuario: EditText
    lateinit var editTextPassword: EditText
    lateinit var editTextNombre: EditText

    init {
        setHasOptionsMenu(true)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var v = inflater.inflate(R.layout.fragment_register, container, false)

        editTextUsuario = v.findViewById(R.id.editTextRegisterUsuario)
        editTextPassword = v.findViewById(R.id.editTextRegisterPassword)
        editTextNombre = v.findViewById(R.id.editTextRegisterNombre)
        val buttonRegisterOk = v.findViewById<Button>(R.id.buttonRegisterOk)

        buttonRegisterOk.setOnClickListener{
            processRegister()
        }
        return v
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun processRegister(){
        val dataRepository = DataRepository(requireContext())
        if (dataRepository.insert(Usuario(editTextUsuario.text.toString(), editTextPassword.text.toString(), editTextNombre.text.toString())) == -1) {
            Toast.makeText(requireContext(), "Usuario repetido", Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply { }
    }
}