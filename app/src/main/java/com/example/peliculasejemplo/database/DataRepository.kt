package com.example.peliculasejemplo.database

import android.content.Context
import kotlinx.coroutines.*

class DataRepository(context: Context) {
    private val cityDao: CityDao? = AppDatabase.getInstance(context)?.CityDao()
    private val usuarioDao: UsuarioDao? = AppDatabase.getInstance(context)?.usuarioDao()

    fun insert(city: City):Int {
        if (cityDao != null){
            CoroutineScope(Dispatchers.IO).launch {
                cityDao!!.insert(city)
            }
            return 0
        }
        return -1
    }

    fun insert(usuario: Usuario):Int {
        if (usuarioDao != null){
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDao.insert(usuario)
            }
            return 0
        }
        return -1
    }

    fun delete(titulo: String):Int {
        if (cityDao != null){
            CoroutineScope(Dispatchers.IO).launch {
                cityDao.delete(titulo)
            }
            return 0
        }
        return -1
    }

    fun update(tituloO: String, tituloN: String):Int {
        if (cityDao != null){
            CoroutineScope(Dispatchers.IO).launch {
                cityDao.update(tituloO, tituloN)
            }
            return 0
        }
        return -1
    }

    fun updateUsr(nombre: String, contrasena: String, usuario: String):Int {
        if (usuarioDao != null){
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDao.updateUsr(nombre, contrasena, usuario)
            }
            return 0
        }
        return -1
    }

    fun deleteUsr(usuario: String):Int {
        if (usuarioDao != null){
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDao.deleteUsr(usuario)
            }
            return 0
        }
        return -1
    }

    fun isLogin(usuario: String, password:String): Boolean{

        var job : Job

        job = CoroutineScope(Dispatchers.IO).async {
            usuarioDao!!.countUsuarioByUsuarioPassword(usuario, password)!!
        }

        return runBlocking {
            job.await() == 1
        }
    }

    fun countUsuario():Int = runBlocking {
        usuarioDao!!.countUsuario()!!
    }


    fun getCity():List<City> = runBlocking {
        cityDao!!.getCity()
    }

}