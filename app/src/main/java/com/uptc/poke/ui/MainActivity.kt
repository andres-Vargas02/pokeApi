package com.uptc.poke.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.uptc.poke.databinding.ActivityMainBinding
import com.uptc.poke.viewmodel.PokemonViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonAdapter = PokemonAdapter(emptyList())

        binding.rvPokemons.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pokemonAdapter
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                if (uiState.isLoading) {
                    binding.progressBar.visibility = android.view.View.VISIBLE
                } else {
                    binding.progressBar.visibility = android.view.View.GONE
                    pokemonAdapter.pokemons = uiState.pokemons ?: emptyList()
                    pokemonAdapter.notifyDataSetChanged()
                }
            }
        }

        viewModel.fetchPokemons()
    }
}
