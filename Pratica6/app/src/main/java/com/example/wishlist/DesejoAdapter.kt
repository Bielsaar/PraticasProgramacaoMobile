package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DesejoAdapter (val lista: MutableList<Desejo>) : RecyclerView.Adapter<DesejoAdapter.DesejoViewHolder>() {
    var listener: OnItemClickRecycleView? = null
    var listenerLong: OnItemLongClickRecycleView? = null

    inner class DesejoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvDesejo: TextView

        init {
            this.tvDesejo = itemView.findViewById(R.id.TvItemDesejo)

            itemView.setOnClickListener(){
                this@DesejoAdapter.listener?.OnItemClick(this.adapterPosition)
            }

            itemView.setOnLongClickListener(){
                this@DesejoAdapter.listenerLong?.OnItemLongClick(this.adapterPosition)
                return@setOnLongClickListener true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesejoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycleview, parent, false)
        return DesejoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DesejoViewHolder, position: Int) {
        val desejo = this.lista.get(position)
        holder.tvDesejo.text = desejo.descricao
    }

    override fun getItemCount(): Int = this.lista.size



}