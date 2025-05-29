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
public final class ActivateAppUseCase_Factory implements Factory<ActivateAppUseCase> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public ActivateAppUseCase_Factory(Provider<SettingsRepository> settingsRepositoryProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public ActivateAppUseCase get() {
    return newInstance(settingsRepositoryProvider.get());
  }

  public static ActivateAppUseCase_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new ActivateAppUseCase_Factory(settingsRepositoryProvider);
  }

  public static ActivateAppUseCase newInstance(SettingsRepository settingsRepository) {
    return new ActivateAppUseCase(settingsRepository);
  }
}
