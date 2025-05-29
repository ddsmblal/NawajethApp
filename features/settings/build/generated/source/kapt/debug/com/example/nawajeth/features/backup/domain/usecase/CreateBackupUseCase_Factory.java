package com.example.nawajeth.features.backup.domain.usecase;

import com.example.nawajeth.features.backup.domain.repository.BackupRepository;
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
public final class CreateBackupUseCase_Factory implements Factory<CreateBackupUseCase> {
  private final Provider<BackupRepository> backupRepositoryProvider;

  public CreateBackupUseCase_Factory(Provider<BackupRepository> backupRepositoryProvider) {
    this.backupRepositoryProvider = backupRepositoryProvider;
  }

  @Override
  public CreateBackupUseCase get() {
    return newInstance(backupRepositoryProvider.get());
  }

  public static CreateBackupUseCase_Factory create(
      Provider<BackupRepository> backupRepositoryProvider) {
    return new CreateBackupUseCase_Factory(backupRepositoryProvider);
  }

  public static CreateBackupUseCase newInstance(BackupRepository backupRepository) {
    return new CreateBackupUseCase(backupRepository);
  }
}
