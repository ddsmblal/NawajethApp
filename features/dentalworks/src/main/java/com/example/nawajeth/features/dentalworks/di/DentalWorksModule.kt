package com.example.nawajeth.features.dentalworks.di

import com.example.nawajeth.features.dentalworks.data.repository.DentalWorkRepositoryImpl
import com.example.nawajeth.features.dentalworks.domain.repository.DentalWorkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DentalWorksModule {
    
    @Binds
    @Singleton
    abstract fun bindDentalWorkRepository(
        dentalWorkRepositoryImpl: DentalWorkRepositoryImpl
    ): DentalWorkRepository
}
