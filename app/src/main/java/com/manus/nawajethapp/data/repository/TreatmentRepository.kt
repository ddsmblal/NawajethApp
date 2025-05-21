package com.manus.nawajethapp.data.repository

import androidx.lifecycle.LiveData
import com.manus.nawajethapp.data.local.TreatmentSessionDao
import com.manus.nawajethapp.data.local.TreatmentTaskDao
import com.manus.nawajethapp.data.model.TreatmentSession
import com.manus.nawajethapp.data.model.TreatmentTask

class TreatmentRepository(
    private val treatmentSessionDao: TreatmentSessionDao,
    private val treatmentTaskDao: TreatmentTaskDao
) {

    // TreatmentSession operations
    fun getSessionsForPatient(patientId: Long): LiveData<List<TreatmentSession>> {
        return treatmentSessionDao.getSessionsForPatient(patientId)
    }

    fun getSessionById(sessionId: Long): LiveData<TreatmentSession> {
        return treatmentSessionDao.getSessionById(sessionId)
    }

    suspend fun insertSession(session: TreatmentSession): Long {
        return treatmentSessionDao.insertSession(session)
    }

    suspend fun updateSession(session: TreatmentSession) {
        treatmentSessionDao.updateSession(session)
    }

    suspend fun deleteSession(session: TreatmentSession) {
        treatmentSessionDao.deleteSession(session)
    }

    // TreatmentTask operations
    fun getTasksForSession(sessionId: Long): LiveData<List<TreatmentTask>> {
        return treatmentTaskDao.getTasksForSession(sessionId)
    }

    suspend fun insertTask(task: TreatmentTask): Long {
        return treatmentTaskDao.insertTask(task)
    }

    suspend fun updateTask(task: TreatmentTask) {
        treatmentTaskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TreatmentTask) {
        treatmentTaskDao.deleteTask(task)
    }
}

