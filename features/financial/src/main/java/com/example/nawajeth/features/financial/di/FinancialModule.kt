package com.example.nawajeth.features.financial.di

import com.example.nawajeth.features.financial.data.repository.PaymentRepositoryImpl
import com.example.nawajeth.features.financial.domain.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FinancialModule {
    
    @Binds
    @Singleton
    abstract fun bindPaymentRepository(
        paymentRepositoryImpl: PaymentRepositoryImpl
    ): PaymentRepository
}
