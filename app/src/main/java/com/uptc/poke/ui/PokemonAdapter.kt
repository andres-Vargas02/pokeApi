package com.uptc.poke.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uptc.poke.databinding.ItemPokemonBinding
import com.uptc.poke.models.Pokemon

class PokemonAdapter(
    var pokemons: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }



    override fun getItemCount() = pokemons.size

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.tvPokemonName.text = pokemon.name

            Glide.with(binding.root.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.getPokemonId()}.png")
                .into(binding.ivPokemonImage)

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, DetailPokemonActivity::class.java)
                intent.putExtra("POKEMON_ID", pokemon.getPokemonId())
                context.startActivity(intent)
            }
        }
    }
}
