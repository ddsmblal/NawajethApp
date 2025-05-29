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
public final class DeletePatientUseCase_Factory implements Factory<DeletePatientUseCase> {
  private final Provider<PatientRepository> patientRepositoryProvider;

  public DeletePatientUseCase_Factory(Provider<PatientRepository> patientRepositoryProvider) {
    this.patientRepositoryProvider = patientRepositoryProvider;
  }

  @Override
  public DeletePatientUseCase get() {
    return newInstance(patientRepositoryProvider.get());
  }

  public static DeletePatientUseCase_Factory create(
      Provider<PatientRepository> patientRepositoryProvider) {
    return new DeletePatientUseCase_Factory(patientRepositoryProvider);
  }

  public static DeletePatientUseCase newInstance(PatientRepository patientRepository) {
    return new DeletePatientUseCase(patientRepository);
  }
}
