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
public final class AddPatientUseCase_Factory implements Factory<AddPatientUseCase> {
  private final Provider<PatientRepository> patientRepositoryProvider;

  private final Provider<GetLicenseStatusUseCase> getLicenseStatusUseCaseProvider;

  public AddPatientUseCase_Factory(Provider<PatientRepository> patientRepositoryProvider,
      Provider<GetLicenseStatusUseCase> getLicenseStatusUseCaseProvider) {
    this.patientRepositoryProvider = patientRepositoryProvider;
    this.getLicenseStatusUseCaseProvider = getLicenseStatusUseCaseProvider;
  }

  @Override
  public AddPatientUseCase get() {
    return newInstance(patientRepositoryProvider.get(), getLicenseStatusUseCaseProvider.get());
  }

  public static AddPatientUseCase_Factory create(
      Provider<PatientRepository> patientRepositoryProvider,
      Provider<GetLicenseStatusUseCase> getLicenseStatusUseCaseProvider) {
    return new AddPatientUseCase_Factory(patientRepositoryProvider, getLicenseStatusUseCaseProvider);
  }

  public static AddPatientUseCase newInstance(PatientRepository patientRepository,
      GetLicenseStatusUseCase getLicenseStatusUseCase) {
    return new AddPatientUseCase(patientRepository, getLicenseStatusUseCase);
  }
}
