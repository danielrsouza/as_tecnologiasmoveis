package com.example.astrabalho

import android.content.Intent
import android.icu.math.BigDecimal
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.astrabalho.bd.Ativo
import com.example.astrabalho.bd.database
import kotlinx.android.synthetic.main.activity_transacao.*
import org.jetbrains.anko.db.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


var ativo : Ativo? = null
var valorQuantidade : Any? = null
var valorMoeda : Any? = null


class ActivityTransacao : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transacao)

        val moeda = intent.extras?.getString("moeda")
        buscaDadosSobreMoeda(moeda.toString())

        salvar_transacao.setOnClickListener {
            database.use {
                val teste = insert("Ativos", "apelido" to moeda.toString(), "quantidade" to quantidade_moeda.text.toString().toDouble(), "valor" to valorMoeda.toString().toDouble())

                if (teste != -1L) {
                    Toast.makeText(this@ActivityTransacao, "Inserido com Sucesso!", Toast.LENGTH_LONG).show()
                }

            }

            val intent = Intent(this, ActivityViewAtivo::class.java)
            startActivity(intent)
            onRestart()
        }


    }


    fun buscaDadosSobreMoeda(moeda: String) {
        val URL_API = "https://www.mercadobitcoin.net/api/$moeda/ticker/"

        doAsync {
            val responseMoeda = URL(URL_API).readText()

            uiThread {
                val objectTicker = JSONObject(responseMoeda).getJSONObject("ticker")
                Log.e("moeda", objectTicker.toString())
                valorMoeda = objectTicker.getDouble("buy")
                valor_moeda.text = valorMoeda.toString()


                // Função que modifica o valor da moeda referente a quantidade que é comprada.
                quantidade_moeda.addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(s: Editable) {
                        valorQuantidade =
                            quantidade_moeda.text.toString().toDouble() * valorMoeda.toString()
                                .toDouble()
                        valor_qtd.text = valorQuantidade.toString()
                    }


                    override fun beforeTextChanged(
                        s: CharSequence, start: Int,
                        count: Int, after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence, start: Int,
                        before: Int, count: Int
                    ) {
                    }
                })


                }

            }

        }
    }




