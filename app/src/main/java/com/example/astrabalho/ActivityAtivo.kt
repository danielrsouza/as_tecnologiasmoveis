package com.example.astrabalho

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ativo.*


val API_URL = "https://economia.awesomeapi.com.br/json/all"

class ActivityAtivo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ativo)

        //Array de Moedas
        val moedas = resources.getStringArray(R.array.moedas)

        //Spinner
        var resultSpinner = ""
        val spinner = findViewById<Spinner>(R.id.spinner_moedas)

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, moedas)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    resultSpinner = moedas[position].toString()
                }
            }
        }

        salvar_ativo.setOnClickListener {
            val intent = Intent(this, ActivityTransacao::class.java)
            intent.putExtra("moeda", resultSpinner)
            startActivity(intent)
        }

    }


}
