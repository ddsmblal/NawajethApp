package com.example.nawajeth.features.settings.domain.model

data class LicenseStatus(
    val isActivated: Boolean,
    val deviceId: String,
    val activationDate: Long? = null
)
