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
public final class GetAllPatientsUseCase_Factory implements Factory<GetAllPatientsUseCase> {
  private final Provider<PatientRepository> patientRepositoryProvider;

  public GetAllPatientsUseCase_Factory(Provider<PatientRepository> patientRepositoryProvider) {
    this.patientRepositoryProvider = patientRepositoryProvider;
  }

  @Override
  public GetAllPatientsUseCase get() {
    return newInstance(patientRepositoryProvider.get());
  }

  public static GetAllPatientsUseCase_Factory create(
      Provider<PatientRepository> patientRepositoryProvider) {
    return new GetAllPatientsUseCase_Factory(patientRepositoryProvider);
  }

  public static GetAllPatientsUseCase newInstance(PatientRepository patientRepository) {
    return new GetAllPatientsUseCase(patientRepository);
  }
}
