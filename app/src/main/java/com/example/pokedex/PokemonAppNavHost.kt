package com.example.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokedex.pokemonlist.PokemonListScreen

@Composable
fun PokemonAppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "pokemon_list_screen"
    ) {
        composable("pokemon_list_screen") {
            PokemonListScreen(navController = navController)
        }
        composable(
            "pokemon_detail_screen/{pokemonName}/{dominantColor}",
            arguments = listOf(
                navArgument("pokemonName") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("dominantColor") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let {
                    Color(color)
                } ?: Color.White
            }
            // TODO: call the screen composable
        }
    }
}