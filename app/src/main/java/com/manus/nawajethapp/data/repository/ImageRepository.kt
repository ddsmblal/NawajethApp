package com.manus.nawajethapp.data.repository

import androidx.lifecycle.LiveData
import com.manus.nawajethapp.data.local.PhotographicImageDao
import com.manus.nawajethapp.data.local.XrayImageDao
import com.manus.nawajethapp.data.model.PhotographicImage
import com.manus.nawajethapp.data.model.XrayImage

class ImageRepository(
    private val xrayImageDao: XrayImageDao,
    private val photographicImageDao: PhotographicImageDao
) {

    // XrayImage operations
    fun getXrayImagesForPatient(patientId: Long): LiveData<List<XrayImage>> {
        return xrayImageDao.getXrayImagesForPatient(patientId)
    }

    suspend fun insertXrayImage(image: XrayImage): Long {
        return xrayImageDao.insertXrayImage(image)
    }

    suspend fun updateXrayImage(image: XrayImage) {
        xrayImageDao.updateXrayImage(image)
    }

    suspend fun deleteXrayImage(image: XrayImage) {
        xrayImageDao.deleteXrayImage(image)
    }

    // PhotographicImage operations
    fun getPhotographicImagesForPatient(patientId: Long): LiveData<List<PhotographicImage>> {
        return photographicImageDao.getPhotographicImagesForPatient(patientId)
    }

    suspend fun insertPhotographicImage(image: PhotographicImage): Long {
        return photographicImageDao.insertPhotographicImage(image)
    }

    suspend fun updatePhotographicImage(image: PhotographicImage) {
        photographicImageDao.updatePhotographicImage(image)
    }

    suspend fun deletePhotographicImage(image: PhotographicImage) {
        photographicImageDao.deletePhotographicImage(image)
    }
}

