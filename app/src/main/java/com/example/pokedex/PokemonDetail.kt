package com.example.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.adapter.PokemonEvolutionAdapter
import com.example.pokedex.adapter.PokemonTypeAdapter
import com.example.pokedex.common.Common
import com.example.pokedex.model.Pokemon

class PokemonDetail : Fragment() {
    private var pokemonImg: ImageView? = null
    private var pokemonName: TextView? = null
    private var pokemonHeight: TextView? = null
    private var pokemonWeight: TextView? = null
    private var recyclerType: RecyclerView? = null
    private var recyclerWeakness: RecyclerView? = null
    private var recyclerEvolution: RecyclerView? = null
    private var recyclerPreEvolution: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(
            R.layout.fragment_pokemon_detail, container,
            false
        )
        val pokemon: Pokemon? = if (arguments!!["num"] == null) arguments?.getInt("position")?.let {
            Common.commonPokemonList[it]
        } else
            Common.findPokemonByNum(arguments?.getString("num"))
        pokemonImg =
            itemView.findViewById<View>(R.id.pokemon_image) as ImageView
        pokemonName = itemView.findViewById<View>(R.id.name) as TextView
        pokemonHeight = itemView.findViewById<View>(R.id.height) as TextView
        pokemonWeight = itemView.findViewById<View>(R.id.weight) as TextView
        recyclerType = itemView.findViewById<View>(R.id.recycler_type) as RecyclerView

        recyclerType!!.setHasFixedSize(true)
        recyclerType!!.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        recyclerWeakness =
            itemView.findViewById<View>(R.id.recycler_weakness) as RecyclerView
        recyclerWeakness!!.setHasFixedSize(true)
        recyclerWeakness!!.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        recyclerPreEvolution =
            itemView.findViewById<View>(R.id.recycler_pre_evolution) as RecyclerView
        recyclerPreEvolution!!.setHasFixedSize(true)
        recyclerPreEvolution!!.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        recyclerEvolution =
            itemView.findViewById<View>(R.id.recycler_evolution) as RecyclerView
        recyclerEvolution!!.setHasFixedSize(true)
        recyclerEvolution!!.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val typeAdapter = activity?.let { pokemon?.type?.let { it1 -> PokemonTypeAdapter(it, it1) } }
        this.recyclerType!!.adapter = typeAdapter

        val typeWeakness = activity?.let { pokemon?.weaknesses?.let { it1 -> PokemonTypeAdapter(it, it1) } }
        this.recyclerWeakness!!.adapter = typeWeakness

        val preEvolution = activity?.let { pokemon?.prev_evolution?.let { it1 -> PokemonEvolutionAdapter(it, it1) } }
        this.recyclerPreEvolution!!.adapter = preEvolution

        val nextEvolution = activity?.let { pokemon?.next_evolution?.let { it1 -> PokemonEvolutionAdapter(it, it1) } }
        this.recyclerEvolution!!.adapter = nextEvolution

        setDetailsPokemon(pokemon)
        return itemView
    }

    private fun setDetailsPokemon(pokemon: Pokemon?) {
        Glide.with(activity!!).load(pokemon!!.img).into(pokemonImg!!)
        pokemonName!!.text = pokemon.name
        pokemonWeight!!.text = String.format("Weight: %s", pokemon.weight)
        pokemonHeight!!.text = String.format("Height: %s", pokemon.height)
    }

    companion object {
        private var instance: PokemonDetail? = null
        fun getInstance(): PokemonDetail? {
            if (instance == null) instance =
                PokemonDetail()
            return instance
        }
    }
}