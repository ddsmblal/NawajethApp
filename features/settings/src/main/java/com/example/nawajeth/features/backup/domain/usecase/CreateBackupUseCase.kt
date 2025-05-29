package com.example.nawajeth.features.backup.domain.usecase

import com.example.nawajeth.features.backup.domain.repository.BackupRepository
import javax.inject.Inject

class CreateBackupUseCase @Inject constructor(
    private val backupRepository: BackupRepository
) {
    suspend operator fun invoke(path: String): Result<Boolean> {
        return backupRepository.createBackup(path)
    }
}
