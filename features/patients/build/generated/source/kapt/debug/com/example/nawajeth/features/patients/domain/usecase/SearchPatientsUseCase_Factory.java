package com.example.nawajeth.features.patients.domain.usecase;

import com.example.nawajeth.features.patients.domain.repository.PatientRepository;
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
public final class SearchPatientsUseCase_Factory implements Factory<SearchPatientsUseCase> {
  private final Provider<PatientRepository> patientRepositoryProvider;

  public SearchPatientsUseCase_Factory(Provider<PatientRepository> patientRepositoryProvider) {
    this.patientRepositoryProvider = patientRepositoryProvider;
  }

  @Override
  public SearchPatientsUseCase get() {
    return newInstance(patientRepositoryProvider.get());
  }

  public static SearchPatientsUseCase_Factory create(
      Provider<PatientRepository> patientRepositoryProvider) {
    return new SearchPatientsUseCase_Factory(patientRepositoryProvider);
  }

  public static SearchPatientsUseCase newInstance(PatientRepository patientRepository) {
    return new SearchPatientsUseCase(patientRepository);
  }
}
