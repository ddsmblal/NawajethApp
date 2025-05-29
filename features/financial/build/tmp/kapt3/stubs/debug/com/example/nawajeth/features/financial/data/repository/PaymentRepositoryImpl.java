package com.example.nawajeth.features.financial.data.repository;

import com.example.nawajeth.data.db.dao.PaymentDao;
import com.example.nawajeth.data.db.entity.PaymentEntity;
import com.example.nawajeth.features.financial.domain.model.Payment;
import com.example.nawajeth.features.financial.domain.repository.PaymentRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00100\u000fH\u0016J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00100\u000f2\u0006\u0010\u0014\u001a\u00020\u0007H\u0016J\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00100\u000f2\u0006\u0010\u0016\u001a\u00020\u0007H\u0016J$\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00100\u000f2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J!\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0011\u0010\u001e\u001a\u00020\u0003*\u00020\u0003H\u0002\u00a2\u0006\u0002\u0010\u001fJ\u0011\u0010 \u001a\u00020\u0003*\u00020\u0003H\u0002\u00a2\u0006\u0002\u0010\u001fR\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/example/nawajeth/features/financial/data/repository/PaymentRepositoryImpl;", "Lcom/example/nawajeth/features/financial/domain/repository/PaymentRepository;", "paymentDao", "error/NonExistentClass", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "addPayment", "", "payment", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePayment", "", "paymentId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPayments", "Lkotlinx/coroutines/flow/Flow;", "", "getPaymentById", "id", "getPaymentsByDentalWorkId", "dentalWorkId", "getPaymentsByPatientId", "patientId", "getPaymentsForPeriod", "startDate", "endDate", "getTotalPaymentsForPeriod", "", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePayment", "toDomain", "(Lerror/NonExistentClass;)Lerror/NonExistentClass;", "toEntity", "financial_debug"})
public final class PaymentRepositoryImpl implements com.example.nawajeth.features.financial.domain.repository.PaymentRepository {
    private final PaymentDao paymentDao = null;
    
    @javax.inject.Inject
    public PaymentRepositoryImpl(@org.jetbrains.annotations.NotNull
    PaymentDao paymentDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<Payment>> getAllPayments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<Payment>> getPaymentsByPatientId(long patientId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<Payment>> getPaymentsByDentalWorkId(long dentalWorkId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<Payment> getPaymentById(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object addPayment(@org.jetbrains.annotations.NotNull
    Payment payment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object updatePayment(@org.jetbrains.annotations.NotNull
    Payment payment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object deletePayment(long paymentId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getTotalPaymentsForPeriod(long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<Payment>> getPaymentsForPeriod(long startDate, long endDate) {
        return null;
    }
    
    private final Payment toDomain(error.NonExistentClass $this$toDomain) {
        return null;
    }
    
    private final PaymentEntity toEntity(error.NonExistentClass $this$toEntity) {
        return null;
    }
}