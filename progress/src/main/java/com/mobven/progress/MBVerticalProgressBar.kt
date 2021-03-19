package com.mobven.progress

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

class MBVerticalProgressBar : MBProgressBar {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var centerX = 0f
    private var margin = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        centerX = width / 2f
        margin = fgStrokeWidth / 2
        canvas?.let {
            it.drawLine(centerX, height.toFloat() - margin, centerX, margin, bgPaint)
            it.drawLine(
                centerX,
                height.toFloat() - margin,
                centerX,
                (height - margin) - ((height - fgStrokeWidth) * normalizedProgress),
                fgPaint
            )
        }
    }
}