package com.uptc.poke.api

import com.uptc.poke.models.PokemonResponse
import com.uptc.poke.models.PokemonDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon?limit=20")
    suspend fun getPokemons(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetailsById(@Path("id") id: Int): PokemonDetails
}
