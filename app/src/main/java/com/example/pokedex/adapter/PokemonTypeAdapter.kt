package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.`interface`.IPokemonClickListener
import com.example.pokedex.common.Common
import com.robertlevonyan.views.chip.Chip

class PokemonTypeAdapter(
    private var context: Context,
    private var typeList: List<String>
) : RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.chip.chipText = typeList[position]
        holder.chip.changeBackgroundColor(Common.getColorByType(typeList[position]))
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var chip: Chip = itemView.findViewById(R.id.chip) as Chip
        private var iItemClickListener: IPokemonClickListener? = null

        init {
            chip.setOnChipClickListener { v -> iItemClickListener?.onClick(v, adapterPosition) }
        }
    }

}