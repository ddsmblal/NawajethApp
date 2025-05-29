package com.example.nawajeth.features.dentalchart.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.nawajeth.features.dentalchart.domain.model.DentalSymbol
import com.example.nawajeth.features.dentalchart.domain.model.ToothCondition
import kotlin.math.roundToInt

@Composable
fun DentalChartCanvas(
    modifier: Modifier = Modifier,
    toothConditions: List<ToothCondition>,
    availableSymbols: List<DentalSymbol>,
    selectedSymbolId: Long?,
    onToothClick: (Int) -> Unit,
    onAddCondition: (ToothCondition) -> Unit
) {
    // حالة التحويل (التكبير/التصغير والتحريك)
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    
    // حالة اللمس والسحب
    var dragStartPosition by remember { mutableStateOf<Offset?>(null) }
    var currentDragPosition by remember { mutableStateOf<Offset?>(null) }
    
    // حالة التحويل للتكبير/التصغير والتحريك
    val transformableState = rememberTransformableState { zoomChange, panChange, _ ->
        scale = (scale * zoomChange).coerceIn(0.5f, 3f)
        offset += panChange
    }
    
    // الرسم التفاعلي
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .clipToBounds()
            .transformable(state = transformableState)
            .pointerInput(Unit) {
                detectTapGestures { position ->
                    // تحديد السن الذي تم النقر عليه
                    val toothIndex = getToothIndexAtPosition(
                        position = position,
                        scale = scale,
                        offset = offset,
                        size = size
                    )
                    
                    if (toothIndex != null) {
                        onToothClick(toothIndex)
                    }
                }
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { position ->
                        dragStartPosition = position
                        currentDragPosition = position
                    },
                    onDrag = { change, _ ->
                        currentDragPosition = change.position
                    },
                    onDragEnd = {
                        // إذا كان هناك رمز محدد وتم السحب على سن
                        if (selectedSymbolId != null && dragStartPosition != null && currentDragPosition != null) {
                            val startToothIndex = getToothIndexAtPosition(
                                position = dragStartPosition!!,
                                scale = scale,
                                offset = offset,
                                size = size
                            )
                            
                            if (startToothIndex != null) {
                                val selectedSymbol = availableSymbols.find { it.id == selectedSymbolId }
                                if (selectedSymbol != null) {
                                    onAddCondition(
                                        ToothCondition(
                                            toothNumber = startToothIndex,
                                            symbolId = selectedSymbolId,
                                            x = dragStartPosition!!.x,
                                            y = dragStartPosition!!.y,
                                            color = selectedSymbol.defaultColor
                                        )
                                    )
                                }
                            }
                        }
                        
                        dragStartPosition = null
                        currentDragPosition = null
                    }
                )
            }
    ) {
        // تطبيق التحويل (التكبير/التصغير والتحريك)
        translate(offset.x, offset.y) {
            // رسم مخطط الأسنان
            drawDentalChart(scale)
            
            // رسم حالات الأسنان
            toothConditions.forEach { condition ->
                val symbol = availableSymbols.find { it.id == condition.symbolId }
                if (symbol != null) {
                    drawSymbol(
                        symbol = symbol,
                        position = Offset(condition.x, condition.y),
                        color = Color(condition.color),
                        scale = scale
                    )
                }
            }
            
            // رسم خط السحب الحالي إذا كان هناك سحب جاري
            if (dragStartPosition != null && currentDragPosition != null && selectedSymbolId != null) {
                drawLine(
                    color = Color.Gray,
                    start = dragStartPosition!!,
                    end = currentDragPosition!!,
                    strokeWidth = 2f * scale
                )
            }
        }
    }
}

// دالة لرسم مخطط الأسنان
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawDentalChart(scale: Float) {
    // رسم الفك العلوي
    drawUpperJaw(scale)
    
    // رسم الفك السفلي
    drawLowerJaw(scale)
}

// دالة لرسم الفك العلوي
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawUpperJaw(scale: Float) {
    val centerX = size.width / 2
    val startY = size.height * 0.2f
    val toothWidth = 40f * scale
    val toothHeight = 50f * scale
    val gapBetweenTeeth = 5f * scale
    
    // رسم 16 سن في الفك العلوي (8 على كل جانب)
    for (i in 0 until 16) {
        val toothNumber = if (i < 8) i + 1 else 17 - (i - 7)
        val x = if (i < 8) {
            centerX - (8 - i) * (toothWidth + gapBetweenTeeth)
        } else {
            centerX + (i - 8) * (toothWidth + gapBetweenTeeth)
        }
        
        drawTooth(
            toothNumber = toothNumber,
            position = Offset(x, startY),
            size = Size(toothWidth, toothHeight),
            isUpper = true
        )
    }
}

// دالة لرسم الفك السفلي
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawLowerJaw(scale: Float) {
    val centerX = size.width / 2
    val startY = size.height * 0.6f
    val toothWidth = 40f * scale
    val toothHeight = 50f * scale
    val gapBetweenTeeth = 5f * scale
    
    // رسم 16 سن في الفك السفلي (8 على كل جانب)
    for (i in 0 until 16) {
        val toothNumber = if (i < 8) 32 - i else 17 + (i - 8)
        val x = if (i < 8) {
            centerX - (8 - i) * (toothWidth + gapBetweenTeeth)
        } else {
            centerX + (i - 8) * (toothWidth + gapBetweenTeeth)
        }
        
        drawTooth(
            toothNumber = toothNumber,
            position = Offset(x, startY),
            size = Size(toothWidth, toothHeight),
            isUpper = false
        )
    }
}

// دالة لرسم سن واحد
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawTooth(
    toothNumber: Int,
    position: Offset,
    size: Size,
    isUpper: Boolean
) {
    // رسم حدود السن
    drawRect(
        color = Color.Black,
        topLeft = position,
        size = size,
        style = Stroke(width = 2f)
    )
    
    // رسم رقم السن
    drawContext.canvas.nativeCanvas.drawText(
        toothNumber.toString(),
        position.x + size.width / 2,
        position.y + size.height / 2,
        android.graphics.Paint().apply {
            color = android.graphics.Color.BLACK
            textSize = 12f * (size.width / 40f)
            textAlign = android.graphics.Paint.Align.CENTER
        }
    )
}

// دالة لرسم رمز
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawSymbol(
    symbol: DentalSymbol,
    position: Offset,
    color: Color,
    scale: Float
) {
    val symbolSize = 30f * scale
    
    when (symbol.type) {
        "circle" -> {
            drawCircle(
                color = color,
                radius = symbolSize / 2,
                center = position,
                style = Stroke(width = 2f * scale)
            )
        }
        "cross" -> {
            drawLine(
                color = color,
                start = Offset(position.x - symbolSize / 2, position.y - symbolSize / 2),
                end = Offset(position.x + symbolSize / 2, position.y + symbolSize / 2),
                strokeWidth = 2f * scale
            )
            drawLine(
                color = color,
                start = Offset(position.x + symbolSize / 2, position.y - symbolSize / 2),
                end = Offset(position.x - symbolSize / 2, position.y + symbolSize / 2),
                strokeWidth = 2f * scale
            )
        }
        "square" -> {
            drawRect(
                color = color,
                topLeft = Offset(position.x - symbolSize / 2, position.y - symbolSize / 2),
                size = Size(symbolSize, symbolSize),
                style = Stroke(width = 2f * scale)
            )
        }
        "triangle" -> {
            val path = androidx.compose.ui.graphics.Path().apply {
                moveTo(position.x, position.y - symbolSize / 2)
                lineTo(position.x + symbolSize / 2, position.y + symbolSize / 2)
                lineTo(position.x - symbolSize / 2, position.y + symbolSize / 2)
                close()
            }
            drawPath(
                path = path,
                color = color,
                style = Stroke(width = 2f * scale)
            )
        }
        "star" -> {
            // تنفيذ رسم النجمة
            val outerRadius = symbolSize / 2
            val innerRadius = outerRadius * 0.4f
            val path = androidx.compose.ui.graphics.Path()
            
            for (i in 0 until 10) {
                val radius = if (i % 2 == 0) outerRadius else innerRadius
                val angle = Math.PI * 2 * i / 10 - Math.PI / 2
                val x = position.x + radius * kotlin.math.cos(angle).toFloat()
                val y = position.y + radius * kotlin.math.sin(angle).toFloat()
                
                if (i == 0) {
                    path.moveTo(x, y)
                } else {
                    path.lineTo(x, y)
                }
            }
            path.close()
            
            drawPath(
                path = path,
                color = color,
                style = Stroke(width = 2f * scale)
            )
        }
        else -> {
            // رمز افتراضي (دائرة)
            drawCircle(
                color = color,
                radius = symbolSize / 2,
                center = position,
                style = Stroke(width = 2f * scale)
            )
        }
    }
}

// دالة لتحديد رقم السن عند موقع معين
private fun getToothIndexAtPosition(
    position: Offset,
    scale: Float,
    offset: Offset,
    size: androidx.compose.ui.geometry.Size
): Int? {
    val adjustedPosition = Offset(
        position.x - offset.x,
        position.y - offset.y
    )
    
    val centerX = size.width / 2
    val upperJawY = size.height * 0.2f
    val lowerJawY = size.height * 0.6f
    val toothWidth = 40f * scale
    val toothHeight = 50f * scale
    val gapBetweenTeeth = 5f * scale
    
    // التحقق من الفك العلوي
    if (adjustedPosition.y >= upperJawY && adjustedPosition.y <= upperJawY + toothHeight) {
        for (i in 0 until 16) {
            val toothNumber = if (i < 8) i + 1 else 17 - (i - 7)
            val x = if (i < 8) {
                centerX - (8 - i) * (toothWidth + gapBetweenTeeth)
            } else {
                centerX + (i - 8) * (toothWidth + gapBetweenTeeth)
            }
            
            if (adjustedPosition.x >= x && adjustedPosition.x <= x + toothWidth) {
                return toothNumber
            }
        }
    }
    
    // التحقق من الفك السفلي
    if (adjustedPosition.y >= lowerJawY && adjustedPosition.y <= lowerJawY + toothHeight) {
        for (i in 0 until 16) {
            val toothNumber = if (i < 8) 32 - i else 17 + (i - 8)
            val x = if (i < 8) {
                centerX - (8 - i) * (toothWidth + gapBetweenTeeth)
            } else {
                centerX + (i - 8) * (toothWidth + gapBetweenTeeth)
            }
            
            if (adjustedPosition.x >= x && adjustedPosition.x <= x + toothWidth) {
                return toothNumber
            }
        }
    }
    
    return null
}
