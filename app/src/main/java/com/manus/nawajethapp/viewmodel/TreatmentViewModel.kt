package com.manus.nawajethapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.manus.nawajethapp.data.local.AppDatabase
import com.manus.nawajethapp.data.model.TreatmentSession
import com.manus.nawajethapp.data.model.TreatmentTask
import com.manus.nawajethapp.data.repository.TreatmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal

class TreatmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TreatmentRepository
    val currentSessionTasks = MutableLiveData<List<TreatmentTask>>()
    val currentSession = MutableLiveData<TreatmentSession?>()

    val totalDueForCurrentSession: LiveData<BigDecimal> = Transformations.map(currentSessionTasks) {
        tasks -> tasks.fold(BigDecimal.ZERO) { acc, task -> acc.add(task.amountDue) }
    }

    val totalPaidForCurrentSession: LiveData<BigDecimal> = Transformations.map(currentSessionTasks) {
        tasks -> tasks.fold(BigDecimal.ZERO) { acc, task -> acc.add(task.amountPaid) }
    }

    val remainingForCurrentSession: LiveData<BigDecimal> = MediatorLiveData<BigDecimal>().apply {
        addSource(totalDueForCurrentSession) { due ->
            value = (due ?: BigDecimal.ZERO).subtract(totalPaidForCurrentSession.value ?: BigDecimal.ZERO)
        }
        addSource(totalPaidForCurrentSession) { paid ->
            value = (totalDueForCurrentSession.value ?: BigDecimal.ZERO).subtract(paid ?: BigDecimal.ZERO)
        }
    }

    init {
        val sessionDao = AppDatabase.getDatabase(application).treatmentSessionDao()
        val taskDao = AppDatabase.getDatabase(application).treatmentTaskDao()
        repository = TreatmentRepository(sessionDao, taskDao)
    }

    fun loadSessionDetails(sessionId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            // Fetch session and tasks
            // This is a simplified way; ideally, repository methods would return LiveData directly or use Flows
            val session = repository.getSessionById(sessionId) // Assuming this can fetch non-LiveData or you adapt
            // For tasks, we are already observing them based on sessionId in the UI layer
            // but if we need to trigger a load or have a single source of truth here:
            // currentSession.postValue(session) // if getSessionById is suspend fun returning TreatmentSession
            // currentSessionTasks.postValue(repository.getTasksForSessionSuspend(sessionId)) // if such a method exists
        }
    }

    // TreatmentSession functions
    fun getSessionsForPatient(patientId: Long): LiveData<List<TreatmentSession>> {
        return repository.getSessionsForPatient(patientId)
    }

    fun getSessionById(sessionId: Long): LiveData<TreatmentSession> {
        // Update currentSession when a specific session is fetched
        // This might be better handled by observing this LiveData in the ViewModel
        // and then updating `currentSession` and `currentSessionTasks` accordingly.
        return repository.getSessionById(sessionId) // Keep this for UI observation
    }

    fun insertSession(session: TreatmentSession) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertSession(session)
    }

    fun updateSession(session: TreatmentSession) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateSession(session)
    }

    fun deleteSession(session: TreatmentSession) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteSession(session)
    }

    fun closeSession(session: TreatmentSession) = viewModelScope.launch(Dispatchers.IO) {
        val updatedSession = session.copy(isClosed = true)
        repository.updateSession(updatedSession)
    }

    // TreatmentTask functions
    fun getTasksForSession(sessionId: Long): LiveData<List<TreatmentTask>> {
        // This LiveData will be observed by the UI and can also update `currentSessionTasks`
        return Transformations.switchMap(repository.getSessionById(sessionId)) { session ->
            currentSession.value = session // Keep track of the current session
            repository.getTasksForSession(sessionId).also {
                // Update currentSessionTasks whenever tasks for the current session change
                // This creates a link if the UI observes this method directly.
                // Alternatively, the UI observes currentSessionTasks and we update it here.
            }
        }
        // A more direct way if currentSession is already set:
        // return repository.getTasksForSession(sessionId)
    }
    
    // This is a more controlled way to update currentSessionTasks
    fun loadTasksForSession(sessionId: Long): LiveData<List<TreatmentTask>> {
        val tasksLiveData = repository.getTasksForSession(sessionId)
        // Use MediatorLiveData to update currentSessionTasks when tasksLiveData changes
        val mediator = MediatorLiveData<List<TreatmentTask>>()
        mediator.addSource(tasksLiveData) {
            currentSessionTasks.value = it
            mediator.value = it // also update the mediator's value if it's returned/observed
        }
        return mediator // or return tasksLiveData directly if UI handles observation
    }


    fun insertTask(task: TreatmentTask) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTask(task)
    }

    fun updateTask(task: TreatmentTask) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTask(task)
    }

    fun deleteTask(task: TreatmentTask) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTask(task)
    }
}

