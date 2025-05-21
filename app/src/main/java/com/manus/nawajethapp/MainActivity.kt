package com.manus.nawajethapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.manus.nawajethapp.ui.license.LicenseActivationScreen
import com.manus.nawajethapp.ui.patient.AddPatientScreen
import com.manus.nawajethapp.ui.patient.PatientDetailScreen
import com.manus.nawajethapp.ui.patient.PatientListScreen
import com.manus.nawajethapp.ui.theme.NawajethAppTheme
import com.manus.nawajethapp.ui.session.TreatmentSessionListScreen
import com.manus.nawajethapp.ui.session.AddEditTreatmentSessionScreen
import com.manus.nawajethapp.ui.task.TreatmentTaskListScreen
import com.manus.nawajethapp.ui.task.AddEditTreatmentTaskScreen
import com.manus.nawajethapp.ui.image.XrayImageListScreen
import com.manus.nawajethapp.ui.image.PhotographicImageListScreen
import com.manus.nawajethapp.ui.image.XrayImageDetailScreen
import com.manus.nawajethapp.ui.image.PhotographicImageDetailScreen
import com.manus.nawajethapp.ui.settings.SettingsScreen
import com.manus.nawajethapp.ui.settings.MedicalSymbolsScreen
import com.manus.nawajethapp.ui.settings.WorkDefinitionsScreen
import com.manus.nawajethapp.ui.settings.BackupRestoreScreen
import com.manus.nawajethapp.viewmodel.ImageViewModel
import com.manus.nawajethapp.viewmodel.LicenseViewModel
import com.manus.nawajethapp.viewmodel.PatientViewModel
import com.manus.nawajethapp.viewmodel.SettingsViewModel
import com.manus.nawajethapp.viewmodel.TreatmentViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NawajethAppTheme {
                NawajethApp()
            }
        }
    }
}

@Composable
fun NawajethApp() {
    val navController = rememberNavController()
    // تهيئة LicenseViewModel للتحقق من حالة النسخة التجريبية عند بدء التطبيق
    val licenseViewModel: LicenseViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.PatientList.route) {
        composable(Screen.PatientList.route) {
            PatientListScreen(
                patientViewModel = viewModel(),
                onAddPatientClicked = { navController.navigate(Screen.AddPatient.route) },
                onPatientClicked = { patientId ->
                    navController.navigate(Screen.PatientDetail.createRoute(patientId))
                },
                onNavigateToSettings = { navController.navigate(Screen.Settings.route) }
            )
        }
        composable(Screen.AddPatient.route) {
            AddPatientScreen(
                patientViewModel = viewModel(),
                licenseViewModel = licenseViewModel,
                onNavigateBack = { navController.popBackStack() },
                onPatientAdded = { 
                    navController.popBackStack() // العودة للقائمة بعد الإضافة
                },
                onNavigateToLicenseActivation = { navController.navigate(Screen.LicenseActivation.route) }
            )
        }
        composable(
            route = Screen.PatientDetail.route,
            arguments = listOf(navArgument("patientId") { type = NavType.LongType })
        ) {
            backStackEntry ->
            val patientId = backStackEntry.arguments?.getLong("patientId") ?: 0
            PatientDetailScreen(
                patientId = patientId,
                patientViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() },
                onNavigateToSessions = { navController.navigate(Screen.TreatmentSessionList.createRoute(patientId)) },
                onNavigateToXrays = { navController.navigate(Screen.XrayImageList.createRoute(patientId, "PatientXRay")) },
                onNavigateToPhotos = { navController.navigate(Screen.PhotographicImageList.createRoute(patientId, "PatientPhoto")) }
            )
        }
        composable(
            route = Screen.TreatmentSessionList.route,
            arguments = listOf(navArgument("patientId") { type = NavType.LongType })
        ) {
            backStackEntry ->
            val patientId = backStackEntry.arguments?.getLong("patientId") ?: 0
            TreatmentSessionListScreen(
                patientId = patientId,
                treatmentViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() },
                onAddSessionClicked = { navController.navigate(Screen.AddEditTreatmentSession.createRoute(patientId, -1L)) },
                onSessionClicked = { sessionId -> navController.navigate(Screen.TreatmentTaskList.createRoute(sessionId)) }
            )
        }
        composable(
            route = Screen.AddEditTreatmentSession.route,
            arguments = listOf(
                navArgument("patientId") { type = NavType.LongType },
                navArgument("sessionId") { type = NavType.LongType }
            )
        ) {
            backStackEntry ->
            val patientId = backStackEntry.arguments?.getLong("patientId") ?: 0
            val sessionId = backStackEntry.arguments?.getLong("sessionId") ?: -1L
            AddEditTreatmentSessionScreen(
                patientId = patientId,
                sessionId = sessionId,
                treatmentViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() },
                onSessionSaved = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.TreatmentTaskList.route,
            arguments = listOf(navArgument("sessionId") { type = NavType.LongType })
        ) {
            backStackEntry ->
            val sessionId = backStackEntry.arguments?.getLong("sessionId") ?: 0
            TreatmentTaskListScreen(
                sessionId = sessionId,
                treatmentViewModel = viewModel(),
                settingsViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() },
                onAddTaskClicked = { navController.navigate(Screen.AddEditTreatmentTask.createRoute(sessionId, -1L)) },
                onTaskClicked = { taskId -> navController.navigate(Screen.AddEditTreatmentTask.createRoute(sessionId, taskId)) }
            )
        }
        composable(
            route = Screen.AddEditTreatmentTask.route,
            arguments = listOf(
                navArgument("sessionId") { type = NavType.LongType },
                navArgument("taskId") { type = NavType.LongType }
            )
        ) {
            backStackEntry ->
            val sessionId = backStackEntry.arguments?.getLong("sessionId") ?: 0
            val taskId = backStackEntry.arguments?.getLong("taskId") ?: -1L
            AddEditTreatmentTaskScreen(
                sessionId = sessionId,
                taskId = taskId,
                treatmentViewModel = viewModel(),
                settingsViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() },
                onTaskSaved = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.XrayImageList.route,
            arguments = listOf(
                navArgument("patientId") { type = NavType.LongType },
                navArgument("patientName") { type = NavType.StringType }
            )
        ) {
            backStackEntry ->
            val patientId = backStackEntry.arguments?.getLong("patientId") ?: 0
            val patientName = backStackEntry.arguments?.getString("patientName") ?: "Patient"
            XrayImageListScreen(
                patientId = patientId,
                patientName = patientName,
                imageViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() },
                onImageClicked = { xrayImage -> navController.navigate(Screen.XrayImageDetail.createRoute(xrayImage.id)) }
            )
        }
        composable(
            route = Screen.PhotographicImageList.route,
            arguments = listOf(
                navArgument("patientId") { type = NavType.LongType },
                navArgument("patientName") { type = NavType.StringType }
            )
        ) {
            backStackEntry ->
            val patientId = backStackEntry.arguments?.getLong("patientId") ?: 0
            val patientName = backStackEntry.arguments?.getString("patientName") ?: "Patient"
            PhotographicImageListScreen(
                patientId = patientId,
                patientName = patientName,
                imageViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() },
                onImageClicked = { photoImage -> navController.navigate(Screen.PhotographicImageDetail.createRoute(photoImage.id)) }
            )
        }
        composable(
            route = Screen.XrayImageDetail.route,
            arguments = listOf(navArgument("xrayImageId") { type = NavType.LongType })
        ) {
            backStackEntry ->
            val xrayImageId = backStackEntry.arguments?.getLong("xrayImageId") ?: 0
            val imageViewModel: ImageViewModel = viewModel()
            val xrayImage = imageViewModel.getXrayById(xrayImageId).value
            xrayImage?.let {
                XrayImageDetailScreen(
                    xrayImage = it,
                    imageViewModel = imageViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
        composable(
            route = Screen.PhotographicImageDetail.route,
            arguments = listOf(navArgument("photoImageId") { type = NavType.LongType })
        ) {
            backStackEntry ->
            val photoImageId = backStackEntry.arguments?.getLong("photoImageId") ?: 0
            val imageViewModel: ImageViewModel = viewModel()
            val photoImage = imageViewModel.getPhotoById(photoImageId).value
            photoImage?.let {
                PhotographicImageDetailScreen(
                    photographicImage = it,
                    imageViewModel = imageViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                settingsViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() },
                onNavigateToMedicalSymbols = { navController.navigate(Screen.MedicalSymbols.route) },
                onNavigateToWorkDefinitions = { navController.navigate(Screen.WorkDefinitions.route) },
                onNavigateToBackupRestore = { navController.navigate(Screen.BackupRestore.route) },
                onNavigateToLicenseActivation = { navController.navigate(Screen.LicenseActivation.route) } // إضافة التنقل لشاشة التفعيل
            )
        }
        composable(Screen.MedicalSymbols.route) {
            MedicalSymbolsScreen(
                settingsViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.WorkDefinitions.route) {
            WorkDefinitionsScreen(
                settingsViewModel = viewModel(),
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.BackupRestore.route) {
            BackupRestoreScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        // إضافة مسار التنقل لشاشة تفعيل النسخة الكاملة
        composable(Screen.LicenseActivation.route) {
            LicenseActivationScreen(
                licenseViewModel = licenseViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object PatientList : Screen("patient_list")
    object AddPatient : Screen("add_patient")
    object PatientDetail : Screen("patient_detail/{patientId}") {
        fun createRoute(patientId: Long) = "patient_detail/$patientId"
    }
    object TreatmentSessionList : Screen("treatment_session_list/{patientId}") {
        fun createRoute(patientId: Long) = "treatment_session_list/$patientId"
    }
    object AddEditTreatmentSession : Screen("add_edit_treatment_session/{patientId}/{sessionId}") {
        fun createRoute(patientId: Long, sessionId: Long) = "add_edit_treatment_session/$patientId/$sessionId"
    }
    object TreatmentTaskList : Screen("treatment_task_list/{sessionId}") {
        fun createRoute(sessionId: Long) = "treatment_task_list/$sessionId"
    }
    object AddEditTreatmentTask : Screen("add_edit_treatment_task/{sessionId}/{taskId}") {
        fun createRoute(sessionId: Long, taskId: Long) = "add_edit_treatment_task/$sessionId/$taskId"
    }
    object XrayImageList : Screen("xray_image_list/{patientId}/{patientName}") {
        fun createRoute(patientId: Long, patientName: String) = "xray_image_list/$patientId/$patientName"
    }
    object PhotographicImageList : Screen("photographic_image_list/{patientId}/{patientName}") {
        fun createRoute(patientId: Long, patientName: String) = "photographic_image_list/$patientId/$patientName"
    }
    object XrayImageDetail : Screen("xray_image_detail/{xrayImageId}") {
        fun createRoute(xrayImageId: Long) = "xray_image_detail/$xrayImageId"
    }
    object PhotographicImageDetail : Screen("photographic_image_detail/{photoImageId}") {
        fun createRoute(photoImageId: Long) = "photographic_image_detail/$photoImageId"
    }
    object Settings : Screen("settings")
    object MedicalSymbols : Screen("medical_symbols")
    object WorkDefinitions : Screen("work_definitions")
    object BackupRestore : Screen("backup_restore")
    object LicenseActivation : Screen("license_activation") // إضافة مسار شاشة تفعيل النسخة الكاملة
}
