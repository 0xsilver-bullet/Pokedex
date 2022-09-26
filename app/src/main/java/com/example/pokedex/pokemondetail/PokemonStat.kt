package com.example.pokedex.pokemondetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.util.parseStatToAbbr
import com.example.pokedex.util.parseStatToColor

@Composable
fun PokemonBaseStats(
    pokemon: Pokemon,
    animDelayedPerItem: Int = 100
) {
    val maxBaseState = remember {
        pokemon.stats.maxOf { it.baseStat }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Base stats:",
            fontSize = 20.sp,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        pokemon.stats.forEachIndexed { index, stat ->
            PokemonStat(
                stateName = parseStatToAbbr(stat),
                stateValue = stat.baseStat,
                statMaxValue = maxBaseState,
                stateColor = parseStatToColor(stat),
                animDelay = index * animDelayedPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PokemonStat(
    stateName: String,
    stateValue: Int,
    statMaxValue: Int,
    stateColor: Color,
    height: Dp = 28.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            stateValue / statMaxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(
                if (isSystemInDarkTheme())
                    Color(0xFF505050)
                else Color.LightGray
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(currentPercent.value)
                .clip(CircleShape)
                .background(stateColor)
                .padding(horizontal = 8.dp)
        ) {
            Text(text = stateName, fontWeight = FontWeight.Bold)
            Text(
                text = (currentPercent.value * statMaxValue).toInt().toString(),
                fontWeight = FontWeight.Bold
            )
        }
    }
}