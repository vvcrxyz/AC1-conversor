package com.example.ac1_conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var lblPrueba: TextView
    private lateinit var lblResultado: TextView
    private lateinit var txtEntrada: EditText
    private lateinit var btnTemperatura: Button
    private lateinit var btnLongitud: Button
    private lateinit var btnPeso: Button
    private lateinit var btnVolumen: Button
    private lateinit var btnConvertir: Button

    private var conversionSeleccionada: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lblPrueba = findViewById(R.id.lblPrueba)
        lblResultado = findViewById(R.id.lblResultado)
        txtEntrada = findViewById(R.id.txtEntrada)
        btnTemperatura = findViewById(R.id.btnTemperatura)
        btnLongitud = findViewById(R.id.btnLongitud)
        btnPeso = findViewById(R.id.btnPeso)
        btnVolumen = findViewById(R.id.btnVolumen)
        btnConvertir = findViewById(R.id.btnConvertir)

        btnTemperatura.setOnClickListener {
            conversionSeleccionada = "temp"
            lblPrueba.text = "Convertir °C a °F"
        }

        btnLongitud.setOnClickListener {
            conversionSeleccionada = "long"
            lblPrueba.text = "Convertir Km a Millas"
        }

        btnPeso.setOnClickListener {
            conversionSeleccionada = "peso"
            lblPrueba.text = "Convertir Kg a Libras"
        }

        btnVolumen.setOnClickListener {
            conversionSeleccionada = "vol"
            lblPrueba.text = "Convertir Litros a Onzas"
        }

        btnConvertir.setOnClickListener {
            val entrada = txtEntrada.text.toString()
            if (entrada.isEmpty()) {
                Toast.makeText(this, "Introduce un valor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val valor = entrada.toDouble()
            val resultado = when(conversionSeleccionada){
                "temp" -> celsiusToFahrenheit(valor)
                "long" -> kmToMillas(valor)
                "peso" -> kgToLb(valor)
                "vol" -> litrosToOnzas(valor)
                else -> {
                    Toast.makeText(this, "Selecciona una conversión primero", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val textoResultado = when(conversionSeleccionada){
                "temp" -> "$valor °C = $resultado °F"
                "long" -> "$valor km = $resultado millas"
                "peso" -> "$valor kg = $resultado lb"
                "vol" -> "$valor L = $resultado onzas"
                else -> ""
            }

            lblResultado.text = textoResultado
        }
    }

    private fun celsiusToFahrenheit(c: Double) = (c * 9 / 5) + 32
    private fun kmToMillas(km: Double) = km * 0.621371
    private fun kgToLb(kg: Double) = kg * 2.20462
    private fun litrosToOnzas(litros: Double) = litros * 33.814
}
