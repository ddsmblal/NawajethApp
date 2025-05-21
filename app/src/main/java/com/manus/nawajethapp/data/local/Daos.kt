package com.manus.nawajethapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.manus.nawajethapp.data.model.MedicalWorkTemplate
import com.manus.nawajethapp.data.model.Patient
import com.manus.nawajethapp.data.model.PhotographicImage
import com.manus.nawajethapp.data.model.TreatmentSession
import com.manus.nawajethapp.data.model.TreatmentTask
import com.manus.nawajethapp.data.model.XrayImage

@Dao
interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: Patient): Long

    @Update
    suspend fun updatePatient(patient: Patient)

    @Delete
    suspend fun deletePatient(patient: Patient)

    @Query("SELECT * FROM patients ORDER BY fullName ASC")
    fun getAllPatients(): LiveData<List<Patient>> // Or use Flow

    @Query("SELECT * FROM patients WHERE id = :patientId")
    fun getPatientById(patientId: Long): LiveData<Patient> // Or use Flow

    @Query("SELECT * FROM patients WHERE fullName LIKE :query")
    fun searchPatients(query: String): LiveData<List<Patient>> // Or use Flow
}

@Dao
interface TreatmentSessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: TreatmentSession): Long

    @Update
    suspend fun updateSession(session: TreatmentSession)

    @Delete
    suspend fun deleteSession(session: TreatmentSession)

    @Query("SELECT * FROM treatment_sessions WHERE patientId = :patientId ORDER BY sessionDate DESC")
    fun getSessionsForPatient(patientId: Long): LiveData<List<TreatmentSession>>

    @Query("SELECT * FROM treatment_sessions WHERE id = :sessionId")
    fun getSessionById(sessionId: Long): LiveData<TreatmentSession>
}

@Dao
interface TreatmentTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TreatmentTask): Long

    @Update
    suspend fun updateTask(task: TreatmentTask)

    @Delete
    suspend fun deleteTask(task: TreatmentTask)

    @Query("SELECT * FROM treatment_tasks WHERE sessionId = :sessionId ORDER BY id ASC")
    fun getTasksForSession(sessionId: Long): LiveData<List<TreatmentTask>>
}

@Dao
interface XrayImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertXrayImage(image: XrayImage): Long

    @Update
    suspend fun updateXrayImage(image: XrayImage)

    @Delete
    suspend fun deleteXrayImage(image: XrayImage)

    @Query("SELECT * FROM xray_images WHERE patientId = :patientId ORDER BY uploadDate DESC")
    fun getXrayImagesForPatient(patientId: Long): LiveData<List<XrayImage>>
}

@Dao
interface PhotographicImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotographicImage(image: PhotographicImage): Long

    @Update
    suspend fun updatePhotographicImage(image: PhotographicImage)

    @Delete
    suspend fun deletePhotographicImage(image: PhotographicImage)

    @Query("SELECT * FROM photographic_images WHERE patientId = :patientId ORDER BY uploadDate DESC")
    fun getPhotographicImagesForPatient(patientId: Long): LiveData<List<PhotographicImage>>
}

@Dao
interface MedicalWorkTemplateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTemplate(template: MedicalWorkTemplate): Long

    @Update
    suspend fun updateTemplate(template: MedicalWorkTemplate)

    @Delete
    suspend fun deleteTemplate(template: MedicalWorkTemplate)

    @Query("SELECT * FROM medical_work_templates ORDER BY workName ASC")
    fun getAllTemplates(): LiveData<List<MedicalWorkTemplate>>
}

