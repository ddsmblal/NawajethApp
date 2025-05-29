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
public final class GetDentalWorksByPatientIdUseCase_Factory implements Factory<GetDentalWorksByPatientIdUseCase> {
  private final Provider<DentalWorkRepository> dentalWorkRepositoryProvider;

  public GetDentalWorksByPatientIdUseCase_Factory(
      Provider<DentalWorkRepository> dentalWorkRepositoryProvider) {
    this.dentalWorkRepositoryProvider = dentalWorkRepositoryProvider;
  }

  @Override
  public GetDentalWorksByPatientIdUseCase get() {
    return newInstance(dentalWorkRepositoryProvider.get());
  }

  public static GetDentalWorksByPatientIdUseCase_Factory create(
      Provider<DentalWorkRepository> dentalWorkRepositoryProvider) {
    return new GetDentalWorksByPatientIdUseCase_Factory(dentalWorkRepositoryProvider);
  }

  public static GetDentalWorksByPatientIdUseCase newInstance(
      DentalWorkRepository dentalWorkRepository) {
    return new GetDentalWorksByPatientIdUseCase(dentalWorkRepository);
  }
}
