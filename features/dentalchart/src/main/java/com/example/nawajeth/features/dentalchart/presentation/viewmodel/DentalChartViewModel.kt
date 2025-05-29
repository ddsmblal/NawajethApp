package com.example.nawajeth.features.dentalchart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol
import com.example.nawajeth.features.dentalchart.domain.model.ToothCondition
import com.example.nawajeth.features.dentalchart.domain.repository.DentalChartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DentalChartViewModel @Inject constructor(
    private val dentalChartRepository: DentalChartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DentalChartUiState())
    val uiState: StateFlow<DentalChartUiState> = _uiState.asStateFlow()

    fun loadPatientDentalChart(patientId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                // تحميل جميع الرموز المتاحة
                dentalChartRepository.getAllSymbols().collect { symbols ->
                    _uiState.update { it.copy(availableSymbols = symbols) }
                }
                
                // تحميل حالات أسنان المريض
                dentalChartRepository.getToothConditionsByPatientId(patientId).collect { conditions ->
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            patientId = patientId,
                            toothConditions = conditions
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun selectSymbol(symbolId: Long?) {
        _uiState.update { it.copy(selectedSymbolId = symbolId) }
    }

    fun selectTooth(toothNumber: Int) {
        _uiState.update { it.copy(selectedToothNumber = toothNumber) }
    }

    fun addToothCondition(condition: ToothCondition) {
        viewModelScope.launch {
            try {
                // إضافة حالة السن مع ربطها بالمريض الحالي
                val conditionWithPatient = condition.copy(patientId = _uiState.value.patientId)
                dentalChartRepository.addToothCondition(conditionWithPatient)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun updateToothCondition(condition: ToothCondition) {
        viewModelScope.launch {
            try {
                dentalChartRepository.updateToothCondition(condition)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun deleteToothCondition(conditionId: Long) {
        viewModelScope.launch {
            try {
                dentalChartRepository.deleteToothCondition(conditionId)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class DentalChartUiState(
    val isLoading: Boolean = false,
    val patientId: Long = 0,
    val availableSymbols: List<DentalSymbol> = emptyList(),
    val toothConditions: List<ToothCondition> = emptyList(),
    val selectedSymbolId: Long? = null,
    val selectedToothNumber: Int? = null,
    val error: String? = null
)
