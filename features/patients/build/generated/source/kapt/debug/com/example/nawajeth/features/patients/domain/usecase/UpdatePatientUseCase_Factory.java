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
public final class UpdatePatientUseCase_Factory implements Factory<UpdatePatientUseCase> {
  private final Provider<PatientRepository> patientRepositoryProvider;

  public UpdatePatientUseCase_Factory(Provider<PatientRepository> patientRepositoryProvider) {
    this.patientRepositoryProvider = patientRepositoryProvider;
  }

  @Override
  public UpdatePatientUseCase get() {
    return newInstance(patientRepositoryProvider.get());
  }

  public static UpdatePatientUseCase_Factory create(
      Provider<PatientRepository> patientRepositoryProvider) {
    return new UpdatePatientUseCase_Factory(patientRepositoryProvider);
  }

  public static UpdatePatientUseCase newInstance(PatientRepository patientRepository) {
    return new UpdatePatientUseCase(patientRepository);
  }
}
