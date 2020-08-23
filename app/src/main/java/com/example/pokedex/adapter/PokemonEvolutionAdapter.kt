package com.example.pokedex.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.`interface`.IPokemonClickListener
import com.example.pokedex.common.Common
import com.example.pokedex.common.Common.findPokemonByNum
import com.example.pokedex.common.Common.getColorByType
import com.example.pokedex.model.Evolution
import com.robertlevonyan.views.chip.Chip
import com.robertlevonyan.views.chip.OnChipClickListener
import java.util.*

class PokemonEvolutionAdapter(
    var context: Context,
    evolutions: List<Evolution>?
) : RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder>() {
    private var evolutions: List<Evolution>? = null
    var chip: Chip? = null
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
        holder.chip.chipText = evolutions!![position].name
        holder.run {
            chip.changeBackgroundColor(
                getColorByType(
                    findPokemonByNum(
                        evolutions!![position].num
                    )!!.type!![0]
                )
            )
        }
        holder.run {
            setOnChipClickListener(OnChipClickListener {
                LocalBroadcastManager.getInstance(context)
                .sendBroadcast(Intent(Common.KEY_NUM_EVOLUTION).putExtra("num", evolutions!![adapterPosition].num))
            })
        }
    }

    override fun getItemCount(): Int {
        return evolutions?.size!!
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var chip: Chip = itemView.findViewById<View>(R.id.chip) as Chip

        private var onChipClickListener:OnChipClickListener?=null

        fun setOnChipClickListener(onChipClickListener: OnChipClickListener){
            this.onChipClickListener = onChipClickListener
        }
    }

    init {
        if (evolutions != null) this.evolutions = evolutions else this.evolutions =
            ArrayList()
    }
}
