package com.example.pokedex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.pokedex.common.Common
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val showDetail = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                if(intent.action!!.toString() == Common.KEY_ENABLE_HOME){
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                    supportActionBar!!.setDisplayShowHomeEnabled(true)

                    val detailFragment = PokemonDetail.getInstance()
                    val position = intent.getIntExtra("position", -1)
                    val bundle = Bundle()
                    bundle.putInt("position", position)
                    if (detailFragment != null) {
                        detailFragment.arguments = bundle
                    }

                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    if (detailFragment != null) {
                        fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment)
                    }
                    fragmentTransaction.addToBackStack("detail")
                    fragmentTransaction.commit()

                    val pokemon = Common.commonPokemonList[position]
                    toolbar.title = pokemon.name
                }
            }
        }
    }

    private val showEvolution = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                if(intent.action!!.toString() == Common.KEY_NUM_EVOLUTION){
                    val detailFragment = PokemonDetail.getInstance()
                    val bundle = Bundle()
                    val num = intent.getStringExtra("num")
                    bundle.putString("num", num)
                    if (detailFragment != null) {
                        detailFragment.arguments = bundle
                    }

                    val fragmentTransaction = supportFragmentManager.beginTransaction()
                    if (detailFragment != null) {
                        fragmentTransaction.remove(detailFragment)
                    }
                    if (detailFragment != null) {
                        fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment)
                    }
                    fragmentTransaction.addToBackStack("detail")
                    fragmentTransaction.commit()

                    val pokemon = Common.findPokemonByNum(num)
                    if (pokemon != null) {
                        toolbar.title = pokemon.name
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Pokédex"
        setSupportActionBar(toolbar)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter(Common.KEY_ENABLE_HOME))

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showEvolution, IntentFilter(Common.KEY_NUM_EVOLUTION))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                toolbar.title = "Pokédex"

                supportFragmentManager.popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE)

                supportActionBar!!.setDisplayShowHomeEnabled(false)
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            }
        }
        return true
    }
}