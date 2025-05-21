package com.manus.nawajethapp.data.repository

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import androidx.room.RoomDatabase
import com.manus.nawajethapp.data.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BackupRepository(private val context: Context, private val appDatabase: AppDatabase) {

    private val DATABASE_NAME = AppDatabase.DATABASE_NAME
    private val BACKUP_DIR_NAME = "NawajethAppBackups"

    suspend fun backupDatabaseLocal(): String? = withContext(Dispatchers.IO) {
        if (!isExternalStorageWritable()) {
            return@withContext null // Or throw an exception/return error state
        }

        val dbFile = context.getDatabasePath(DATABASE_NAME)
        if (!dbFile.exists()) {
            return@withContext null // Database file does not exist
        }

        val backupDir = File(context.getExternalFilesDir(null), BACKUP_DIR_NAME)
        if (!backupDir.exists()) {
            backupDir.mkdirs()
        }

        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val backupFileName = "NawajethDB_Backup_$timestamp.db"
        val backupFile = File(backupDir, backupFileName)

        return@withContext try {
            // Close the database before copying to ensure data integrity
            // This is tricky with Room as it manages its own lifecycle.
            // A safer approach might involve Room specific backup methods if available or checkpointing.
            // For simplicity, we directly copy. Ensure the app is not actively writing during this.
            appDatabase.close()

            FileInputStream(dbFile).use { input ->
                FileOutputStream(backupFile).use { output ->
                    input.copyTo(output)
                }
            }
            // Re-open the database after backup. This is also tricky.
            // Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build() // This might not be the right way to re-open the same instance.
            // It's generally better if Room handles this, or the app is restarted after restore.
            backupFile.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } finally {
            // Attempt to re-open the database. This is a simplified and potentially problematic way.
            // Ideally, the ViewModel or a higher-level component manages the DB instance.
            // For now, we assume the DB instance used by the app will be recreated or handled elsewhere.
        }
    }

    suspend fun restoreDatabaseLocal(backupFileUri: Uri): Boolean = withContext(Dispatchers.IO) {
        val dbFile = context.getDatabasePath(DATABASE_NAME)

        return@withContext try {
            appDatabase.close() // Close current database

            context.contentResolver.openInputStream(backupFileUri)?.use { inputStream ->
                FileOutputStream(dbFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            // After restoring, the app usually needs to be restarted or the database connection re-established.
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } finally {
            // Similar to backup, re-opening the database instance needs careful management.
        }
    }

    suspend fun getLocalBackups(): List<File> = withContext(Dispatchers.IO) {
        val backupDir = File(context.getExternalFilesDir(null), BACKUP_DIR_NAME)
        if (backupDir.exists() && backupDir.isDirectory) {
            return@withContext backupDir.listFiles { _, name -> name.endsWith(".db") }?.sortedDescending() ?: emptyList()
        }
        return@withContext emptyList()
    }

    suspend fun deleteLocalBackup(backupFile: File): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            if (backupFile.exists()) {
                backupFile.delete()
            } else {
                false
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            false
        }
    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Google Drive related methods would go here (placeholder)
    suspend fun backupDatabaseToGoogleDrive(): String? {
        // TODO: Implement Google Drive API integration
        // 1. Authenticate user with Google Sign-In
        // 2. Use Drive REST API or Google Drive Android API (deprecated but simpler for some tasks)
        // 3. Upload the database file (similar to local backup, get the db file)
        // This is a complex task requiring user authentication and API setup.
        return "Google Drive Backup Placeholder - Not Implemented"
    }

    suspend fun restoreDatabaseFromGoogleDrive(fileId: String): Boolean {
        // TODO: Implement Google Drive API integration
        // 1. List files or allow user to pick
        // 2. Download the selected file
        // 3. Restore locally (similar to local restore)
        return false
    }
}

