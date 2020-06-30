package com.example.astrabalho

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.astrabalho.adapter.AtivoListAdapter
import com.example.astrabalho.bd.Ativo
import com.example.astrabalho.bd.database
import kotlinx.android.synthetic.main.activity_view_ativo.*
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select


var listaAtivos : List<Ativo>? = null
class ActivityViewAtivo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ativo)
        buscaAtivos()
        val recyclerView = list_view_ativo
        recyclerView.adapter = AtivoListAdapter(listaAtivos, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

///buscar ativos

    fun buscaAtivos() {
        database.use {
            select("Ativos").exec {
                val parser = rowParser { id: Int
                                         , apelido: String, quantidade: Double, valor: Double ->

                    Ativo(id, apelido, quantidade, valor)
                }
                listaAtivos = parseList(parser)

                Log.e("teste", listaAtivos.toString())
            }
        }
    }
}