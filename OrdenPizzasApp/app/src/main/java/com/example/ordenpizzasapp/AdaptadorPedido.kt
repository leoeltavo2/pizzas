package com.example.ordenpizzasapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lista_pedido.view.*

class AdaptadorPedido(val item:ArrayList<Pedido>) :
    RecyclerView.Adapter<AdaptadorPedido.PedidoViewHolder>() {
    class PedidoViewHolder(view: View):RecyclerView.ViewHolder(view)
    var items: ArrayList<Pedido>? = null
    var viewHolder: RecyclerView.ViewHolder? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorPedido.PedidoViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.lista_pedido, parent, false)
//        viewHolder = RecyclerView.ViewHolder(vista)
        return PedidoViewHolder(vista)
    }

    override fun onBindViewHolder(holder: AdaptadorPedido.PedidoViewHolder, position: Int) {
//        val item = items?.get(position)
        holder.itemView.tvPizzaTipo.text = item[position].tipoPizza
        holder.itemView.tvTamañoPizza.text = item[position].tamaño
        holder.itemView.tvTExtraQueso.text = item[position].extra_queso
        holder.itemView.tvTExtraIngrediente.text = item[position].extra_ingrediente
        holder.itemView.tvTOrillaRellena.text = item[position].orilla_rellena
        holder.itemView.tvTotalPagar.text = item[position].total_pagar
        holder.itemView.tvDireccion.text = item[position].direccion

    }

    override fun getItemCount(): Int {
        return item.size
    }

//    class ViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
//        //  var vista = vista
//
//    }


}



