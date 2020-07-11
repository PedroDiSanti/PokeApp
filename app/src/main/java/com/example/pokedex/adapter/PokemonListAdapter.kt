package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.model.Pokemon

class PokemonListAdapter(
    private var context: Context,
    private var pokemonList:List<Pokemon>
) : RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView:View = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(pokemonList[position].img).into(holder.imagePokemon)
        holder.textPokemon.text = pokemonList[position].name
    }

    inner class MyViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        internal var imagePokemon: ImageView = itemView.findViewById(R.id.pokemon_image) as ImageView
        internal var textPokemon: TextView = itemView.findViewById(R.id.pokemon_name) as TextView
    }
}