package com.example.nawajeth.features.settings.domain.usecase;

import com.example.nawajeth.features.settings.domain.repository.SettingsRepository;
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
public final class GetLicenseStatusUseCase_Factory implements Factory<GetLicenseStatusUseCase> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public GetLicenseStatusUseCase_Factory(Provider<SettingsRepository> settingsRepositoryProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public GetLicenseStatusUseCase get() {
    return newInstance(settingsRepositoryProvider.get());
  }

  public static GetLicenseStatusUseCase_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new GetLicenseStatusUseCase_Factory(settingsRepositoryProvider);
  }

  public static GetLicenseStatusUseCase newInstance(SettingsRepository settingsRepository) {
    return new GetLicenseStatusUseCase(settingsRepository);
  }
}
