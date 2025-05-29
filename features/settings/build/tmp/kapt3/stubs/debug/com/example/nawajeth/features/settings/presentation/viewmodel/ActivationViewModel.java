package com.example.nawajeth.features.settings.presentation.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.nawajeth.features.settings.domain.usecase.ActivateAppUseCase;
import com.example.nawajeth.features.settings.domain.usecase.GetDeviceIdUseCase;
import com.example.nawajeth.features.settings.domain.usecase.GetLicenseStatusUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\b\u0010\u0013\u001a\u00020\u0011H\u0002J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0017"}, d2 = {"Lcom/example/nawajeth/features/settings/presentation/viewmodel/ActivationViewModel;", "Landroidx/lifecycle/ViewModel;", "getLicenseStatusUseCase", "Lcom/example/nawajeth/features/settings/domain/usecase/GetLicenseStatusUseCase;", "getDeviceIdUseCase", "Lcom/example/nawajeth/features/settings/domain/usecase/GetDeviceIdUseCase;", "activateAppUseCase", "Lcom/example/nawajeth/features/settings/domain/usecase/ActivateAppUseCase;", "(Lcom/example/nawajeth/features/settings/domain/usecase/GetLicenseStatusUseCase;Lcom/example/nawajeth/features/settings/domain/usecase/GetDeviceIdUseCase;Lcom/example/nawajeth/features/settings/domain/usecase/ActivateAppUseCase;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/nawajeth/features/settings/presentation/viewmodel/ActivationUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "activateApp", "", "copyDeviceId", "loadLicenseStatus", "updateActivationCode", "code", "", "settings_debug"})
public final class ActivationViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.nawajeth.features.settings.domain.usecase.GetLicenseStatusUseCase getLicenseStatusUseCase = null;
    private final com.example.nawajeth.features.settings.domain.usecase.GetDeviceIdUseCase getDeviceIdUseCase = null;
    private final com.example.nawajeth.features.settings.domain.usecase.ActivateAppUseCase activateAppUseCase = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.nawajeth.features.settings.presentation.viewmodel.ActivationUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.nawajeth.features.settings.presentation.viewmodel.ActivationUiState> uiState = null;
    
    @javax.inject.Inject
    public ActivationViewModel(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.settings.domain.usecase.GetLicenseStatusUseCase getLicenseStatusUseCase, @org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.settings.domain.usecase.GetDeviceIdUseCase getDeviceIdUseCase, @org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.settings.domain.usecase.ActivateAppUseCase activateAppUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.nawajeth.features.settings.presentation.viewmodel.ActivationUiState> getUiState() {
        return null;
    }
    
    private final void loadLicenseStatus() {
    }
    
    public final void updateActivationCode(@org.jetbrains.annotations.NotNull
    java.lang.String code) {
    }
    
    public final void activateApp() {
    }
    
    public final void copyDeviceId() {
    }
}