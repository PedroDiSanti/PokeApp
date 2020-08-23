package com.example.pokedex.`interface`

import android.view.View

interface IPokemonClickListener {
    fun onClick(view: View, position: Int)
}