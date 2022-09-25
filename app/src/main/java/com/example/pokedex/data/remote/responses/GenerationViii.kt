package com.example.pokedex.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GenerationViii(
    val icons: IconsX
)

data class IconsX(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_female")
    val frontFemale: Any
)
