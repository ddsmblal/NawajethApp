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
public final class GetDeviceIdUseCase_Factory implements Factory<GetDeviceIdUseCase> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public GetDeviceIdUseCase_Factory(Provider<SettingsRepository> settingsRepositoryProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public GetDeviceIdUseCase get() {
    return newInstance(settingsRepositoryProvider.get());
  }

  public static GetDeviceIdUseCase_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new GetDeviceIdUseCase_Factory(settingsRepositoryProvider);
  }

  public static GetDeviceIdUseCase newInstance(SettingsRepository settingsRepository) {
    return new GetDeviceIdUseCase(settingsRepository);
  }
}
