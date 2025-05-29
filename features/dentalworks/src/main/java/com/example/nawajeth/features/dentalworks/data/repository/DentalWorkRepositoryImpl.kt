package com.example.nawajeth.features.dentalworks.data.repository

import com.example.nawajeth.data.db.dao.DentalWorkDao
import com.example.nawajeth.data.db.entity.DentalWorkEntity
import com.example.nawajeth.features.dentalworks.domain.model.DentalWork
import com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DentalWorkRepositoryImpl @Inject constructor(
    private val dentalWorkDao: DentalWorkDao
) : DentalWorkRepository {
    
    override fun getAllDentalWorks(): Flow<List<DentalWork>> {
        return dentalWorkDao.getAllDentalWorks().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getDentalWorksByPatientId(patientId: Long): Flow<List<DentalWork>> {
        return dentalWorkDao.getDentalWorksByPatientId(patientId).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getDentalWorkById(id: Long): Flow<DentalWork?> {
        return dentalWorkDao.getDentalWorkById(id).map { entity ->
            entity?.toDomain()
        }
    }
    
    override suspend fun addDentalWork(dentalWork: DentalWork): Long {
        return dentalWorkDao.insertDentalWork(dentalWork.toEntity())
    }
    
    override suspend fun updateDentalWork(dentalWork: DentalWork) {
        dentalWorkDao.updateDentalWork(dentalWork.toEntity())
    }
    
    override suspend fun deleteDentalWork(dentalWorkId: Long) {
        dentalWorkDao.deleteDentalWork(dentalWorkId)
    }
    
    override suspend fun getCompletedWorkCount(startDate: Long, endDate: Long): Int {
        return dentalWorkDao.getCompletedWorkCount(startDate, endDate)
    }
    
    override fun getCompletedWorks(startDate: Long, endDate: Long): Flow<List<DentalWork>> {
        return dentalWorkDao.getCompletedWorks(startDate, endDate).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun getDentalWorkTotalForPatient(patientId: Long): Double {
        return dentalWorkDao.getDentalWorkTotalForPatient(patientId)
    }
    
    override suspend fun getDentalWorkPaidForPatient(patientId: Long): Double {
        return dentalWorkDao.getDentalWorkPaidForPatient(patientId)
    }
    
    private fun DentalWorkEntity.toDomain(): DentalWork {
        return DentalWork(
            id = id,
            patientId = patientId,
            procedureName = procedureName,
            cost = cost,
            paidAmount = paidAmount,
            date = date,
            isCompleted = isCompleted
        )
    }
    
    private fun DentalWork.toEntity(): DentalWorkEntity {
        return DentalWorkEntity(
            id = id,
            patientId = patientId,
            procedureName = procedureName,
            cost = cost,
            paidAmount = paidAmount,
            date = date,
            isCompleted = isCompleted
        )
    }
}
