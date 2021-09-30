package com.example.appclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var tvCiudad:TextView? = null
    var tvGrados:TextView? = null
    var tvStatus:TextView? = null

    fun cargarDatos (ciudad:Ciudad)
    {
        tvCiudad?.text = ciudad.name
        tvGrados?.text = String.format("%.1f",ciudad.main?.temp) +"º"
        tvStatus?.text = ciudad.weather?.get(0)?.description?.replaceFirstChar(Char::uppercase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvTemperatura)
        tvStatus = findViewById(R.id.tvClima)


        val ciudad = intent.getStringExtra("com.example.appclima.ciudades.CIUDAD")

        if(Network.hayRed(this)){
            //ejecutar solicitud HTTP
                //key = b76fd52e1dc9bffe8c8d6a2305994644
            solicitudHTTPVolley("https://api.openweathermap.org/data/2.5/weather?id="+ciudad+"&appid=b76fd52e1dc9bffe8c8d6a2305994644&units=metric&lang=es")
        }else{
            //mostrar mensaje
            Toast.makeText(this,"No hay red",Toast.LENGTH_SHORT).show()
        }


    }

    private fun solicitudHTTPVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val solicitud = StringRequest(Request.Method.GET,url, {
                response ->
            try {
                Log.d("SolicitudHTTPVolley",response)

                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)
                Log.d("GSON",ciudad.name)
                cargarDatos(ciudad)
            }catch (e: Exception){

            }
        }, {  })

        queue.add(solicitud)
    }


}