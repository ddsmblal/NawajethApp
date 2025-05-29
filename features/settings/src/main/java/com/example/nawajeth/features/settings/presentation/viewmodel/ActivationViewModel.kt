package com.example.nawajeth.features.settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nawajeth.features.settings.domain.usecase.ActivateAppUseCase
import com.example.nawajeth.features.settings.domain.usecase.GetDeviceIdUseCase
import com.example.nawajeth.features.settings.domain.usecase.GetLicenseStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivationViewModel @Inject constructor(
    private val getLicenseStatusUseCase: GetLicenseStatusUseCase,
    private val getDeviceIdUseCase: GetDeviceIdUseCase,
    private val activateAppUseCase: ActivateAppUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActivationUiState())
    val uiState: StateFlow<ActivationUiState> = _uiState.asStateFlow()

    init {
        loadLicenseStatus()
    }

    private fun loadLicenseStatus() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                val licenseStatus = getLicenseStatusUseCase()
                val deviceId = getDeviceIdUseCase()
                
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isActivated = licenseStatus.isActivated,
                        deviceId = deviceId,
                        activationDate = licenseStatus.activationDate
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        activationError = e.message
                    )
                }
            }
        }
    }

    fun updateActivationCode(code: String) {
        _uiState.update { it.copy(activationCode = code, activationError = null) }
    }

    fun activateApp() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            val result = activateAppUseCase(_uiState.value.activationCode)
            
            result.fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isActivated = true,
                            successMessage = "تم تفعيل التطبيق بنجاح!",
                            activationError = null
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            activationError = error.message
                        )
                    }
                }
            )
        }
    }

    fun copyDeviceId() {
        // سيتم تنفيذ هذه الوظيفة في الطبقة العليا (UI)
    }
}

data class ActivationUiState(
    val isLoading: Boolean = false,
    val isActivated: Boolean = false,
    val deviceId: String = "",
    val activationCode: String = "",
    val activationDate: Long? = null,
    val activationError: String? = null,
    val successMessage: String? = null
)
