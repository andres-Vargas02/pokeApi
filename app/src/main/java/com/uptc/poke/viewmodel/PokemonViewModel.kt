package com.uptc.poke.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uptc.poke.api.RetrofitClient
import com.uptc.poke.models.Pokemon
import com.uptc.poke.models.PokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(
    val isLoading: Boolean = false,
    var pokemons: List<Pokemon>? = null,
    val error: String? = null
)

class PokemonViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun fetchPokemons() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getPokemons()
                _uiState.value = UiState(isLoading = false, pokemons = response.results)
            } catch (e: Exception) {
                _uiState.value = UiState(isLoading = false, error = e.message)
            }
        }
    }

    fun fetchPokemonDetails(id: Int, onSuccess: (PokemonDetails) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val details = RetrofitClient.apiService.getPokemonDetailsById(id)
                onSuccess(details)
            } catch (e: Exception) {
                onError(e.message ?: "Error fetching details")
            }
        }
    }
}
