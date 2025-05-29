package com.example.nawajeth.features.dentalchart.domain.model

data class DentalSymbol(
    val id: Long = 0,
    val name: String,
    val type: String, // circle, cross, square, triangle, star
    val defaultColor: Long, // لون RGB بتنسيق Long
    val description: String = ""
)
