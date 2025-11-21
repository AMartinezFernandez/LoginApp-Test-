package com.example.loginapp.adapters

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.databinding.ActivitySecondBinding
import com.example.loginapp.databinding.ItemProductoListaBinding
import com.example.loginapp.model.producto
import com.google.android.material.snackbar.Snackbar

//1. extiendo de reciclerview.adaper
//3. poner clase anidada dentro de viewholder
//4. implementa los metodos no implementados

// creo producto en una clase y lo añado a la classe(var lista datos: producto)

class productoAdapter(var listaDatos: ArrayList<producto>) : RecyclerView.Adapter<productoAdapter.MyHolder>(){
    //2. creo la clase anidadoa Viewholder
    inner class MyHolder(var binding: ItemProductoListaBinding): RecyclerView.ViewHolder(binding.root)
    // se encarga de crear el holder, es decir la plantilla
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        //6 inflamos la lista , activamos la lista
        return MyHolder(ItemProductoListaBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }
// pinta cada fila , lista de productos
    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        //7
        val producto = listaDatos[position]
    holder.binding.imagenFila.setImageResource(producto.icono)
    holder.binding.textoProduto.setText(producto.nombre)
    holder.binding.imagenFila.setOnClickListener { Snackbar.make(holder.binding.root,"El tipo de comida es ${producto.tipo}",
        Snackbar.LENGTH_SHORT) }
    holder.binding.textoProduto.setOnClickListener { Snackbar.make(holder.binding.root,"El nombre de comida es ${producto.nombre}",
        Snackbar.LENGTH_SHORT) }
    }
//indica cual es el numero de filas que tengo que pintar en total
    override fun getItemCount(): Int {
        //5 rellenamos este metodo el tamaño de la lista
        return listaDatos.size
    }



}