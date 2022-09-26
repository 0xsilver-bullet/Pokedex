package com.example.pokedex.data.repsitory

import com.example.pokedex.data.remote.PokeApi
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.remote.responses.PokemonList
import com.example.pokedex.util.Resource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            Resource.Success(
                pokeApi.getPokemonList(
                    limit = limit,
                    offset = offset
                )
            )
        } catch (e: Exception) {
            // TODO: implement better error handling
            Resource.Error("Unexpected Error")
        }
        return response
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return try {
            Resource.Success(
                pokeApi.getPokemonInfo(pokemonName)
            )
        } catch (e: Exception) {
            // TODO: implement better error handling
            Resource.Error("Unexpected Error")
        }
    }
}