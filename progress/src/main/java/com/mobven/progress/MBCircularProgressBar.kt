package com.mobven.progress

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import kotlin.math.min
import kotlin.math.roundToInt

class MBCircularProgressBar : MBProgressBar {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var maxRadius = 0
    private var centerX = 0f
    private var centerY = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        maxRadius = (min(width, height) / 2) - (fgStrokeWidth / 2).roundToInt()
        centerX = width / 2f
        centerY = height / 2f
        canvas?.let {
            it.drawCircle(centerX, centerY, maxRadius.toFloat(), bgPaint)
            it.drawArc(
                centerX - maxRadius,
                centerY - maxRadius,
                centerX + maxRadius,
                centerY + maxRadius,
                START_ANGLE,
                normalizedProgress * 360,
                false,
                fgPaint
            )
        }
    }

    companion object {
        private const val START_ANGLE = 270f
    }

}