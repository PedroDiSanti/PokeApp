package com.example.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.adapter.PokemonListAdapter
import com.example.pokedex.common.Common
import com.example.pokedex.retrofit.IPokemonList
import com.example.pokedex.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class PokemonList : Fragment() {
    private var compositeDisposable = CompositeDisposable()
    private var iPokemonList:IPokemonList

    private lateinit var recycleView:RecyclerView

    init {
        val retrofit: Retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView:View = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        recycleView = itemView.findViewById(R.id.pokemon_recycleView) as RecyclerView
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = GridLayoutManager(activity, 2)

        val itemDecoration = ItemOffsetDecoration(activity!!, R.dimen.spacing)
        recycleView.addItemDecoration(itemDecoration)

        fetchData()

        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ pokemonDex ->
                Common.commonPokemonList = pokemonDex.pokemon!!
                val adapter = PokemonListAdapter(activity!!, Common.commonPokemonList)

                this.recycleView.adapter = adapter
            })
    }
}