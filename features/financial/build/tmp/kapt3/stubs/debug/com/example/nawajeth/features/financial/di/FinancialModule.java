package com.example.nawajeth.features.financial.di;

import com.example.nawajeth.features.financial.data.repository.PaymentRepositoryImpl;
import com.example.nawajeth.features.financial.domain.repository.PaymentRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/example/nawajeth/features/financial/di/FinancialModule;", "", "()V", "bindPaymentRepository", "Lcom/example/nawajeth/features/financial/domain/repository/PaymentRepository;", "paymentRepositoryImpl", "Lcom/example/nawajeth/features/financial/data/repository/PaymentRepositoryImpl;", "financial_debug"})
@dagger.Module
public abstract class FinancialModule {
    
    public FinancialModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.example.nawajeth.features.financial.domain.repository.PaymentRepository bindPaymentRepository(@org.jetbrains.annotations.NotNull
    com.example.nawajeth.features.financial.data.repository.PaymentRepositoryImpl paymentRepositoryImpl);
}