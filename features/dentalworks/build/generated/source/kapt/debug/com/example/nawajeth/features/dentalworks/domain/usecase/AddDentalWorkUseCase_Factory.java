package com.example.nawajeth.features.dentalworks.domain.usecase;

import com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository;
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
public final class AddDentalWorkUseCase_Factory implements Factory<AddDentalWorkUseCase> {
  private final Provider<DentalWorkRepository> dentalWorkRepositoryProvider;

  public AddDentalWorkUseCase_Factory(Provider<DentalWorkRepository> dentalWorkRepositoryProvider) {
    this.dentalWorkRepositoryProvider = dentalWorkRepositoryProvider;
  }

  @Override
  public AddDentalWorkUseCase get() {
    return newInstance(dentalWorkRepositoryProvider.get());
  }

  public static AddDentalWorkUseCase_Factory create(
      Provider<DentalWorkRepository> dentalWorkRepositoryProvider) {
    return new AddDentalWorkUseCase_Factory(dentalWorkRepositoryProvider);
  }

  public static AddDentalWorkUseCase newInstance(DentalWorkRepository dentalWorkRepository) {
    return new AddDentalWorkUseCase(dentalWorkRepository);
  }
}
