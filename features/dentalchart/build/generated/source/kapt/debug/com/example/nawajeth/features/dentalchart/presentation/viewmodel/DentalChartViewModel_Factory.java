package com.example.nawajeth.features.dentalchart.presentation.viewmodel;

import com.example.nawajeth.features.dentalchart.domain.repository.DentalChartRepository;
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
public final class DentalChartViewModel_Factory implements Factory<DentalChartViewModel> {
  private final Provider<DentalChartRepository> dentalChartRepositoryProvider;

  public DentalChartViewModel_Factory(
      Provider<DentalChartRepository> dentalChartRepositoryProvider) {
    this.dentalChartRepositoryProvider = dentalChartRepositoryProvider;
  }

  @Override
  public DentalChartViewModel get() {
    return newInstance(dentalChartRepositoryProvider.get());
  }

  public static DentalChartViewModel_Factory create(
      Provider<DentalChartRepository> dentalChartRepositoryProvider) {
    return new DentalChartViewModel_Factory(dentalChartRepositoryProvider);
  }

  public static DentalChartViewModel newInstance(DentalChartRepository dentalChartRepository) {
    return new DentalChartViewModel(dentalChartRepository);
  }
}
