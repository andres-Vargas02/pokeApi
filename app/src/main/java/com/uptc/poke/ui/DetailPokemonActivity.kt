package com.uptc.poke.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.uptc.poke.databinding.DetailPokemonBinding
import com.uptc.poke.viewmodel.PokemonViewModel

class DetailPokemonActivity : AppCompatActivity() {

    private lateinit var binding: DetailPokemonBinding
    private val viewModel: PokemonViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonId = intent.getIntExtra("POKEMON_ID", 0)

        viewModel.fetchPokemonDetails(pokemonId, { details ->
            binding.tvPokemonName2.text = details.name
            binding.tvPokemonHeight.text = "Altura: ${details.height}"
            binding.tvPokemonWeight.text = "Peso: ${details.weight}"
            binding.tvPokemonExperience.text = "Experiencia: ${details.base_experience}"

            Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png")
                .into(binding.ivPokemonImage2)
        }, { error ->
            // Manejar el error
        })
    }
}
