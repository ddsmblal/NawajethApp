package com.manus.nawajethapp.ui.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas as AndroidCanvas // Alias to avoid conflict with Compose Canvas
import android.graphics.Paint as AndroidPaint
import android.graphics.RectF
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Category // Placeholder for symbols
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource // For SVG if using a library
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manus.nawajethapp.data.model.MedicalSymbol
import com.manus.nawajethapp.data.model.PhotographicImage
import com.manus.nawajethapp.data.model.XrayImage
import com.manus.nawajethapp.viewmodel.ImageViewModel
import com.manus.nawajethapp.viewmodel.SettingsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

// (Keep existing saveUriToTempFile, saveBitmapToFile, XrayImageListScreen, copyToPersistentStorage, PhotographicImageListScreen, ImageItemCard)
// ... (previous code for these functions remains unchanged)

fun saveUriToTempFile(context: android.content.Context, uri: Uri, patientId: Long, type: String): String? {
    val inputStream = context.contentResolver.openInputStream(uri) ?: return null
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "${type}_${patientId}_${timestamp}_temp.jpg" // Assuming JPG for simplicity from URI
    val directory = File(context.cacheDir, "temp_images")
    if (!directory.exists()) {
        directory.mkdirs()
    }
    val file = File(directory, fileName)
    val outputStream = FileOutputStream(file)
    inputStream.copyTo(outputStream)
    inputStream.close()
    outputStream.close()
    return file.absolutePath
}

suspend fun saveBitmapToFile(context: Context, bitmap: Bitmap, patientId: Long, originalFileName: String, type: String): String? = withContext(Dispatchers.IO) {
    return@withContext try {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        // Ensure a unique name, perhaps based on original or a new timestamp
        val newFileName = "${type}_${patientId}_${timestamp}_annotated.png" // Save as PNG to preserve transparency if symbols are drawn
        val directory = File(context.filesDir, "patient_images")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val file = File(directory, newFileName)
        FileOutputStream(file).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) // Use PNG for annotations
        }
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

suspend fun copyToPersistentStorage(context: Context, tempFile: File, patientId: Long, type: String): String? = withContext(Dispatchers.IO) {
    return@withContext try {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileExtension = tempFile.extension
        val fileName = "${type}_${patientId}_$timestamp.$fileExtension"
        val directory = File(context.filesDir, "patient_images")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val persistentFile = File(directory, fileName)
        tempFile.copyTo(persistentFile, overwrite = true)
        persistentFile.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XrayImageListScreen(
    patientId: Long,
    patientName: String,
    imageViewModel: ImageViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onImageClicked: (XrayImage) -> Unit
) {
    val context = LocalContext.current
    val xrayImages by imageViewModel.getXrayImagesForPatient(patientId).observeAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()
    var showDeleteDialog by remember { mutableStateOf<XrayImage?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                coroutineScope.launch {
                    val tempPath = saveUriToTempFile(context, it, patientId, "xray")
                    tempPath?.let {
                        val persistentPath = copyToPersistentStorage(context, File(it), patientId, "xray")
                        persistentPath?.let {
                            val newXray = XrayImage(patientId = patientId, imagePath = persistentPath)
                            imageViewModel.insertXrayImage(newXray)
                        }
                        File(it).delete() // Delete temp file
                    }
                }
            }
        }
    )
    
    if (showDeleteDialog != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = null },
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this X-Ray image?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog?.let { imageToDelete ->
                            imageViewModel.deleteXrayImage(imageToDelete)
                            coroutineScope.launch(Dispatchers.IO) { File(imageToDelete.imagePath).delete() }
                        }
                        showDeleteDialog = null
                    }
                ) { Text("Delete") }
            },
            dismissButton = { TextButton(onClick = { showDeleteDialog = null }) { Text("Cancel") } }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("X-Rays for $patientName") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { imagePickerLauncher.launch("image/*") }) {
                Icon(Icons.Filled.AddAPhoto, "Add X-Ray")
            }
        }
    ) {
        paddingValues ->
        if (xrayImages.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No X-Ray images found. Tap \'+\' to add.")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(paddingValues).padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(xrayImages) { image ->
                    ImageItemCard(imagePath = image.imagePath, description = image.description ?: "X-Ray",
                        onImageClick = { onImageClicked(image) },
                        onDeleteClick = { showDeleteDialog = image }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotographicImageListScreen(
    patientId: Long,
    patientName: String,
    imageViewModel: ImageViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onImageClicked: (PhotographicImage) -> Unit
) {
    val context = LocalContext.current
    val photoImages by imageViewModel.getPhotographicImagesForPatient(patientId).observeAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()
    var showDeleteDialog by remember { mutableStateOf<PhotographicImage?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                coroutineScope.launch {
                    val tempPath = saveUriToTempFile(context, it, patientId, "photo")
                    tempPath?.let {
                        val persistentPath = copyToPersistentStorage(context, File(it), patientId, "photo")
                        persistentPath?.let {
                            val newPhoto = PhotographicImage(patientId = patientId, imagePath = persistentPath)
                            imageViewModel.insertPhotographicImage(newPhoto)
                        }
                        File(it).delete()
                    }
                }
            }
        }
    )
    
    if (showDeleteDialog != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = null },
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this photographic image?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog?.let { imageToDelete ->
                            imageViewModel.deletePhotographicImage(imageToDelete)
                            coroutineScope.launch(Dispatchers.IO) { File(imageToDelete.imagePath).delete() }
                        }
                        showDeleteDialog = null
                    }
                ) { Text("Delete") }
            },
            dismissButton = { TextButton(onClick = { showDeleteDialog = null }) { Text("Cancel") } }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Photos for $patientName") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { imagePickerLauncher.launch("image/*") }) {
                Icon(Icons.Filled.AddAPhoto, "Add Photo")
            }
        }
    ) {
        paddingValues ->
        if (photoImages.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No photographic images found. Tap \'+\' to add.")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(paddingValues).padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(photoImages) { image ->
                    ImageItemCard(imagePath = image.imagePath, description = image.caption ?: "Photo",
                        onImageClick = { onImageClicked(image) },
                        onDeleteClick = { showDeleteDialog = image }
                    )
                }
            }
        }
    }
}

@Composable
fun ImageItemCard(imagePath: String, description: String, onImageClick: () -> Unit, onDeleteClick: () -> Unit) {
    var imageBitmapState by remember { mutableStateOf<ImageBitmap?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(imagePath) {
        isLoading = true
        imageBitmapState = withContext(Dispatchers.IO) {
            try {
                val file = File(imagePath)
                if (file.exists()) {
                    BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap()
                } else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        isLoading = false
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onImageClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (imageBitmapState != null) {
                Image(
                    bitmap = imageBitmapState!!,
                    contentDescription = description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text("Image not found", modifier = Modifier.align(Alignment.Center).padding(8.dp))
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .padding(8.dp)
            ) {
                Text(description, style = MaterialTheme.typography.bodySmall, color = Color.White, maxLines = 1)
            }
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.TopEnd).padding(4.dp).background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f), shape = MaterialTheme.shapes.small)
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Image", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

data class PlacedSymbol(
    val symbol: MedicalSymbol,
    var offset: Offset,
    var size: Float = 50f, // Default size in dp, will be converted to pixels
    val id: Int = System.identityHashCode(Offset) // Unique ID for this instance
)

data class PathProperties(val color: Color = Color.Red, val strokeWidth: Float = 5f)

fun androidx.compose.ui.geometry.Size.toIntSize(): androidx.compose.ui.unit.IntSize {
    return androidx.compose.ui.unit.IntSize(width.toInt(), height.toInt())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XrayImageDetailScreen(
    xrayImage: XrayImage,
    imageViewModel: ImageViewModel = viewModel(),
    settingsViewModel: SettingsViewModel = viewModel(), // Added for symbols
    onNavigateBack: () -> Unit
) {
    var description by remember { mutableStateOf(xrayImage.description ?: "") }
    var originalImageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Drawing paths
    val paths = remember { mutableStateListOf<Pair<Path, PathProperties>>() }
    var currentPath by remember { mutableStateOf(Path()) }
    var currentPathProperties by remember { mutableStateOf(PathProperties()) }
    var showColorPicker by remember { mutableStateOf(false) }
    var showBrushSizePicker by remember { mutableStateOf(false) }

    // Medical Symbols
    val medicalSymbols by settingsViewModel.allMedicalSymbols.observeAsState(emptyList())
    var selectedSymbolForPlacement by remember { mutableStateOf<MedicalSymbol?>(null) }
    val placedSymbols = remember { mutableStateListOf<PlacedSymbol>() }
    var showSymbolPalette by remember { mutableStateOf(false) }

    // For capturing the composable with annotations
    var canvasSize by remember { mutableStateOf<androidx.compose.ui.geometry.Size?>(null) }
    val density = LocalDensity.current.density

    LaunchedEffect(xrayImage.imagePath) {
        isLoading = true
        originalImageBitmap = withContext(Dispatchers.IO) {
            try {
                val file = File(xrayImage.imagePath)
                if (file.exists()) BitmapFactory.decodeFile(file.absolutePath) else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        isLoading = false
    }

    fun captureAndSaveAnnotatedImage() {
        coroutineScope.launch {
            val baseBitmap = originalImageBitmap ?: return@launch
            val targetWidth = canvasSize?.width?.toInt() ?: baseBitmap.width
            val targetHeight = canvasSize?.height?.toInt() ?: baseBitmap.height

            // Create a new bitmap to draw on, matching the display size of the image
            val annotatedBitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888)
            val androidCanvas = AndroidCanvas(annotatedBitmap)

            // Draw the original image, scaled to fit the canvas display size
            val srcRect = RectF(0f, 0f, baseBitmap.width.toFloat(), baseBitmap.height.toFloat())
            val dstRect = RectF(0f, 0f, targetWidth.toFloat(), targetHeight.toFloat())
            androidCanvas.drawBitmap(baseBitmap, null, dstRect, null)

            val paint = AndroidPaint().apply {
                isAntiAlias = true
                style = AndroidPaint.Style.STROKE
                strokeCap = AndroidPaint.Cap.ROUND
                strokeJoin = AndroidPaint.Join.ROUND
            }
            // Scale factor if canvas size is different from bitmap original size
            // This assumes paths are drawn relative to the displayed image size on canvas
            paths.forEach { (path, props) ->
                paint.color = props.color.toArgb()
                paint.strokeWidth = props.strokeWidth * density // Convert Dp to Px if props.strokeWidth is in Dp
                // The path from Compose Canvas is already in pixels relative to the Canvas.
                // If the Canvas matches the annotatedBitmap size, no further scaling of path itself is needed.
                androidCanvas.drawPath(path.asAndroidPath(), paint) // asAndroidPath() might be needed if Path is Compose Path
            }
            
            // Draw placed symbols
            // This requires loading symbol bitmaps and drawing them at scaled positions
            placedSymbols.forEach { placed ->
                val symbolBitmap = withContext(Dispatchers.IO) {
                    try {
                        if (placed.symbol.symbolType == "png" && File(placed.symbol.symbolData).exists()) {
                            BitmapFactory.decodeFile(placed.symbol.symbolData)
                        } else null // Add SVG handling later if needed
                    } catch (e: Exception) { null }
                }
                symbolBitmap?.let {
                    val symbolSizePx = placed.size * density
                    val symbolRect = RectF(
                        placed.offset.x - symbolSizePx / 2,
                        placed.offset.y - symbolSizePx / 2,
                        placed.offset.x + symbolSizePx / 2,
                        placed.offset.y + symbolSizePx / 2
                    )
                    androidCanvas.drawBitmap(it, null, symbolRect, null)
                    it.recycle() // Recycle if not needed anymore
                }
            }

            val newImagePath = saveBitmapToFile(context, annotatedBitmap, xrayImage.patientId, File(xrayImage.imagePath).name, "xray_annotated")
            newImagePath?.let {
                // Decide: update original XrayImage to new path, or create a new XrayImage entry for annotated version?
                // For now, let's assume we update the existing one's path and description.
                val updatedXray = xrayImage.copy(imagePath = it, description = description, lastModified = System.currentTimeMillis())
                imageViewModel.updateXrayImage(updatedXray)
                // onNavigateBack() // Or show a success message
            }
            annotatedBitmap.recycle()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("X-Ray Details & Annotate") },
                navigationIcon = { IconButton(onClick = onNavigateBack) { Icon(Icons.Filled.ArrowBack, "Back") } },
                actions = {
                    IconButton(onClick = { showSymbolPalette = !showSymbolPalette }) { Icon(Icons.Filled.Category, "Symbols") }
                    IconButton(onClick = { showColorPicker = true }) { Icon(Icons.Filled.Brush, "Brush Color") }
                    IconButton(onClick = { showBrushSizePicker = true }) { Icon(Icons.Filled.Edit, "Brush Size") }
                    IconButton(onClick = { captureAndSaveAnnotatedImage() }) { Icon(Icons.Filled.Save, "Save Annotated Image") }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp))
            } else if (originalImageBitmap != null) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth().background(Color.LightGray)
                    .pointerInput(Unit) { // For placing symbols
                        detectTapGestures {
                            offset -> 
                            selectedSymbolForPlacement?.let {
                                symbolToPlace ->
                                placedSymbols.add(PlacedSymbol(symbol = symbolToPlace, offset = offset))
                                selectedSymbolForPlacement = null // Deselect after placing one
                            }
                        }
                    }
                ) {
                    Image(
                        bitmap = originalImageBitmap!!.asImageBitmap(),
                        contentDescription = "X-Ray Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit // Fit ensures the whole image is visible
                    )
                    Canvas(modifier = Modifier.fillMaxSize()
                        .pointerInput(Unit) { // For drawing paths
                            detectDragGestures(
                                onDragStart = { offset -> 
                                    if (selectedSymbolForPlacement == null) { // Only draw if no symbol is selected for placement
                                        currentPath.moveTo(offset.x, offset.y)
                                    }
                                },
                                onDrag = { change, dragAmount -> 
                                    if (selectedSymbolForPlacement == null) {
                                        currentPath.lineTo(change.position.x, change.position.y); change.consume()
                                    }
                                },
                                onDragEnd = { 
                                    if (selectedSymbolForPlacement == null && !currentPath.isEmpty) {
                                        paths.add(Pair(Path(currentPath), currentPathProperties)) // Add a copy
                                        currentPath.reset() // Reset for next drawing
                                    }
                                }
                            )
                        }
                        .drawWithCache { // To get canvas size
                            canvasSize = size
                            onDrawWithContent {
                                drawContent() // Draws the Image composable underneath
                                paths.forEach { (path, props) ->
                                    drawPath(path, props.color, style = Stroke(width = props.strokeWidth))
                                }
                                if (!currentPath.isEmpty) {
                                    drawPath(currentPath, currentPathProperties.color, style = Stroke(width = currentPathProperties.strokeWidth))
                                }
                                // Draw placed symbols
                                val symbolPaint = AndroidPaint()
                                placedSymbols.forEach { placed ->
                                    // This is a simplified drawing for Compose Canvas. For actual image saving, Android Canvas is better.
                                    // Here, we just draw a placeholder or try to load and draw the symbol bitmap directly.
                                    // For PNG symbols:
                                    if (placed.symbol.symbolType == "png") {
                                        try {
                                            val symbolFile = File(placed.symbol.symbolData)
                                            if (symbolFile.exists()) {
                                                val bmp = BitmapFactory.decodeFile(symbolFile.absolutePath).asImageBitmap()
                                                drawImage(
                                                    image = bmp,
                                                    dstOffset = IntOffset((placed.offset.x - placed.size / 2 * density).roundToInt(), (placed.offset.y - placed.size / 2 * density).roundToInt()),
                                                    dstSize = androidx.compose.ui.unit.IntSize((placed.size * density).toInt(), (placed.size * density).toInt())
                                                )
                                            }
                                        } catch (e: Exception) { /* Draw a placeholder */ 
                                            drawCircle(Color.Magenta, radius = 10f, center = placed.offset)
                                        }
                                    }
                                    // Add SVG handling if required (more complex)
                                }
                            }
                        }
                    ) 
                }

                if (showSymbolPalette) {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceVariant).padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(medicalSymbols) { symbol ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.clickable { selectedSymbolForPlacement = symbol; showSymbolPalette = false; }
                                                 .padding(8.dp)
                                                 .border(if(selectedSymbolForPlacement == symbol) 2.dp else 0.dp, MaterialTheme.colorScheme.primary)
                            ) {
                                // Display symbol preview (e.g., first char or a placeholder icon)
                                if (symbol.symbolType == "png") {
                                    val symBmp = remember(symbol.symbolData) {
                                        try { BitmapFactory.decodeFile(symbol.symbolData)?.asImageBitmap() } catch (e:Exception) { null }
                                    }
                                    if (symBmp != null) {
                                        Image(bitmap = symBmp, contentDescription = symbol.symbolName, modifier = Modifier.size(40.dp))
                                    } else {
                                        Icon(Icons.Filled.Category, contentDescription = symbol.symbolName, modifier = Modifier.size(40.dp))
                                    }
                                } else {
                                    Icon(Icons.Filled.Category, contentDescription = symbol.symbolName, modifier = Modifier.size(40.dp)) // Placeholder for SVG
                                }
                                Text(symbol.symbolName, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }

            } else {
                Text("X-Ray image not found.", modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description/Notes") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    if (showColorPicker) {
        ColorPickerDialog(currentColor = currentPathProperties.color, onColorSelected = { currentPathProperties = currentPathProperties.copy(color = it); showColorPicker = false }, onDismiss = {showColorPicker = false})
    }
    if (showBrushSizePicker){
        BrushSizePickerDialog(currentSize = currentPathProperties.strokeWidth, onSizeSelected = {currentPathProperties = currentPathProperties.copy(strokeWidth = it); showBrushSizePicker = false}, onDismiss = {showBrushSizePicker = false})
    }
}

@Composable
fun ColorPickerDialog(currentColor: Color, onColorSelected: (Color) -> Unit, onDismiss: () -> Unit) {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Black, Color.White)
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Brush Color") },
        text = {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color)
                            .clickable { onColorSelected(color) }
                            .border(if (color == currentColor) 2.dp else Dp.Hairline, if (color == currentColor) MaterialTheme.colorScheme.outline else Color.Gray)
                    )
                }
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Close") } }
    )
}

@Composable
fun BrushSizePickerDialog(currentSize: Float, onSizeSelected: (Float) -> Unit, onDismiss: () -> Unit) {
    var sliderPosition by remember { mutableStateOf(currentSize) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Brush Size") },
        text = {
            Column {
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    valueRange = 1f..20f,
                    steps = 18 
                )
                Text("Size: ${sliderPosition.toInt()}", modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSizeSelected(sliderPosition)
                onDismiss()
            }) { Text("OK") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotographicImageDetailScreen(
    photographicImage: PhotographicImage, 
    imageViewModel: ImageViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    var caption by remember { mutableStateOf(photographicImage.caption ?: "") }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(photographicImage.imagePath) {
        isLoading = true
        imageBitmap = withContext(Dispatchers.IO) {
            try {
                val file = File(photographicImage.imagePath)
                if (file.exists()) {
                    BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap()
                } else null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Photographic Image Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        coroutineScope.launch {
                            imageViewModel.updatePhotographicImage(photographicImage.copy(caption = caption, lastModified = System.currentTimeMillis()))
                            // Add toast or confirmation
                        }
                    }) {
                        Icon(Icons.Filled.Save, "Save Caption")
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (imageBitmap != null) {
                Image(
                    bitmap = imageBitmap!!,
                    contentDescription = "Photographic Image",
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            } else {
                Text("Photographic image not found or path not set.", modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = caption,
                onValueChange = { caption = it },
                label = { Text("Caption/Notes") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )
        }
    }
}

