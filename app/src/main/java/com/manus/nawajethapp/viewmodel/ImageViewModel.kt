package com.manus.nawajethapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.manus.nawajethapp.data.local.AppDatabase
import com.manus.nawajethapp.data.model.PhotographicImage
import com.manus.nawajethapp.data.model.XrayImage
import com.manus.nawajethapp.data.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ImageRepository

    init {
        val xrayDao = AppDatabase.getDatabase(application).xrayImageDao()
        val photoDao = AppDatabase.getDatabase(application).photographicImageDao()
        repository = ImageRepository(xrayDao, photoDao)
    }

    // XrayImage functions
    fun getXrayImagesForPatient(patientId: Long): LiveData<List<XrayImage>> {
        return repository.getXrayImagesForPatient(patientId)
    }

    fun insertXrayImage(image: XrayImage) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertXrayImage(image)
    }

    fun updateXrayImage(image: XrayImage) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateXrayImage(image)
    }

    fun deleteXrayImage(image: XrayImage) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteXrayImage(image)
    }

    // PhotographicImage functions
    fun getPhotographicImagesForPatient(patientId: Long): LiveData<List<PhotographicImage>> {
        return repository.getPhotographicImagesForPatient(patientId)
    }

    fun insertPhotographicImage(image: PhotographicImage) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertPhotographicImage(image)
    }

    fun updatePhotographicImage(image: PhotographicImage) = viewModelScope.launch(Dispatchers.IO) {
        repository.updatePhotographicImage(image)
    }

    fun deletePhotographicImage(image: PhotographicImage) = viewModelScope.launch(Dispatchers.IO) {
        repository.deletePhotographicImage(image)
    }
}

