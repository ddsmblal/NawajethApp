package com.example.nawajeth.features.financial.domain.repository;

import com.example.nawajeth.features.financial.domain.model.Payment;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH&J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u000f\u001a\u00020\u0003H&J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0011\u001a\u00020\u0003H&J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0013\u001a\u00020\u0003H&J$\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H&J!\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/example/nawajeth/features/financial/domain/repository/PaymentRepository;", "", "addPayment", "", "payment", "error/NonExistentClass", "(Lerror/NonExistentClass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePayment", "", "paymentId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPayments", "Lkotlinx/coroutines/flow/Flow;", "", "getPaymentById", "id", "getPaymentsByDentalWorkId", "dentalWorkId", "getPaymentsByPatientId", "patientId", "getPaymentsForPeriod", "startDate", "endDate", "getTotalPaymentsForPeriod", "", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePayment", "financial_debug"})
public abstract interface PaymentRepository {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<Payment>> getAllPayments();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<Payment>> getPaymentsByPatientId(long patientId);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<Payment>> getPaymentsByDentalWorkId(long dentalWorkId);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<Payment> getPaymentById(long id);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object addPayment(@org.jetbrains.annotations.NotNull
    Payment payment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updatePayment(@org.jetbrains.annotations.NotNull
    Payment payment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deletePayment(long paymentId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalPaymentsForPeriod(long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<Payment>> getPaymentsForPeriod(long startDate, long endDate);
}