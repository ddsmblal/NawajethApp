package com.example.nawajeth.features.dentalchart.domain.model

data class ToothCondition(
    val id: Long = 0,
    val toothNumber: Int,
    val symbolId: Long,
    val x: Float,
    val y: Float,
    val color: Long,
    val notes: String = "",
    val patientId: Long = 0,
    val createdAt: Long = System.currentTimeMillis()
)
