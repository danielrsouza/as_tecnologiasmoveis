package com.example.astrabalho.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.astrabalho.R
import com.example.astrabalho.bd.Ativo
import kotlinx.android.synthetic.main.layout_ativo.view.*

 class AtivoListAdapter(private val ativos: List<Ativo>?, private val context: Context) : RecyclerView.Adapter<AtivoListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.layout_ativo, parent, false)
            return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ativos!!.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
             val apelido = itemView.moeda_ativo
             val quantidade = itemView.quantidade_moeda_ativo
             val valor = itemView.valor_moeda_ativo
    }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         val ativo = ativos?.get(position)
         holder.let {
             it.apelido.text = ativo?.apelido
             it.quantidade.text = ativo?.quantidade.toString()
             it.valor.text = ativo?.valor.toString()
         }

     }


 }


