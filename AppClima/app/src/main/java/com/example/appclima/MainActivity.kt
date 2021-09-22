package com.example.appclima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var tvCiudad:TextView? = null
    var tvGrados:TextView? = null
    var tvStatus:TextView? = null

    fun cargarDatos (ciudad:Ciudad)
    {
        tvCiudad?.text = ciudad.nombre
        tvGrados?.text = ciudad.grados.toString()+"º"
        tvStatus?.text = ciudad.status
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvTemperatura)
        tvStatus = findViewById(R.id.tvClima)


        val ciudad = intent.getStringExtra("com.example.appclima.ciudades.CIUDAD")

        Toast.makeText(this,ciudad,Toast.LENGTH_SHORT).show()

        val ciudadxm = Ciudad("Ciudad de Mexico",15,"Soleado")
        val ciudadBA = Ciudad("Buenos Aires", 30, "Despejado")

        if(ciudad == "ciudad-mexico")
        {
            cargarDatos(ciudadxm)
        }
        else if (ciudad == "ciudad-bsas")
        {
            cargarDatos(ciudadBA)
        }
        else
        {
            Toast.makeText(this,"No se encuentra la información",Toast.LENGTH_SHORT).show()
        }


    }


}