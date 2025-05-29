package com.example.nawajeth.features.settings.presentation.viewmodel;

import com.example.nawajeth.features.settings.domain.usecase.ActivateAppUseCase;
import com.example.nawajeth.features.settings.domain.usecase.GetDeviceIdUseCase;
import com.example.nawajeth.features.settings.domain.usecase.GetLicenseStatusUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ActivationViewModel_Factory implements Factory<ActivationViewModel> {
  private final Provider<GetLicenseStatusUseCase> getLicenseStatusUseCaseProvider;

  private final Provider<GetDeviceIdUseCase> getDeviceIdUseCaseProvider;

  private final Provider<ActivateAppUseCase> activateAppUseCaseProvider;

  public ActivationViewModel_Factory(
      Provider<GetLicenseStatusUseCase> getLicenseStatusUseCaseProvider,
      Provider<GetDeviceIdUseCase> getDeviceIdUseCaseProvider,
      Provider<ActivateAppUseCase> activateAppUseCaseProvider) {
    this.getLicenseStatusUseCaseProvider = getLicenseStatusUseCaseProvider;
    this.getDeviceIdUseCaseProvider = getDeviceIdUseCaseProvider;
    this.activateAppUseCaseProvider = activateAppUseCaseProvider;
  }

  @Override
  public ActivationViewModel get() {
    return newInstance(getLicenseStatusUseCaseProvider.get(), getDeviceIdUseCaseProvider.get(), activateAppUseCaseProvider.get());
  }

  public static ActivationViewModel_Factory create(
      Provider<GetLicenseStatusUseCase> getLicenseStatusUseCaseProvider,
      Provider<GetDeviceIdUseCase> getDeviceIdUseCaseProvider,
      Provider<ActivateAppUseCase> activateAppUseCaseProvider) {
    return new ActivationViewModel_Factory(getLicenseStatusUseCaseProvider, getDeviceIdUseCaseProvider, activateAppUseCaseProvider);
  }

  public static ActivationViewModel newInstance(GetLicenseStatusUseCase getLicenseStatusUseCase,
      GetDeviceIdUseCase getDeviceIdUseCase, ActivateAppUseCase activateAppUseCase) {
    return new ActivationViewModel(getLicenseStatusUseCase, getDeviceIdUseCase, activateAppUseCase);
  }
}
