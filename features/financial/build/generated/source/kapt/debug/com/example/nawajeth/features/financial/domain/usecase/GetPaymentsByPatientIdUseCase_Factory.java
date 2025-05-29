package com.example.nawajeth.features.financial.domain.usecase;

import com.example.nawajeth.features.financial.domain.repository.PaymentRepository;
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
public final class GetPaymentsByPatientIdUseCase_Factory implements Factory<GetPaymentsByPatientIdUseCase> {
  private final Provider<PaymentRepository> paymentRepositoryProvider;

  public GetPaymentsByPatientIdUseCase_Factory(
      Provider<PaymentRepository> paymentRepositoryProvider) {
    this.paymentRepositoryProvider = paymentRepositoryProvider;
  }

  @Override
  public GetPaymentsByPatientIdUseCase get() {
    return newInstance(paymentRepositoryProvider.get());
  }

  public static GetPaymentsByPatientIdUseCase_Factory create(
      Provider<PaymentRepository> paymentRepositoryProvider) {
    return new GetPaymentsByPatientIdUseCase_Factory(paymentRepositoryProvider);
  }

  public static GetPaymentsByPatientIdUseCase newInstance(PaymentRepository paymentRepository) {
    return new GetPaymentsByPatientIdUseCase(paymentRepository);
  }
}
