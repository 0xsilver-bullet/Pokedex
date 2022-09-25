package com.example.pokedex.di

import com.example.pokedex.data.repsitory.PokemonRepository
import com.example.pokedex.data.repsitory.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonModule {

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(pokemonRepository: PokemonRepositoryImpl): PokemonRepository
}