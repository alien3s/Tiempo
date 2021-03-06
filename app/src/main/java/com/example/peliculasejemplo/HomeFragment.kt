package com.example.peliculasejemplo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)
        var buttonLogin = v.findViewById<Button>(R.id.buttonLogin)
        var buttonRegister = v.findViewById<Button>(R.id.buttonRegister)

        var preferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)
        var login = preferences?.getString("login", "")
        if (login.equals("true")) {
            findNavController().navigate(R.id.action_homeFragment_to_listFragment)
        }

        buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        buttonRegister.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                }
    }
}