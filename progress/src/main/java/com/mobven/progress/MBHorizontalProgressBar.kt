package com.mobven.progress

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

class MBHorizontalProgressBar : MBProgressBar {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var centerY = 0f
    private var margin = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        centerY = height / 2f
        margin = fgStrokeWidth / 2
        canvas?.let {
            it.drawLine(margin, centerY, width.toFloat() - margin, centerY, bgPaint)
            it.drawLine(
                margin,
                centerY,
                margin + ((width - fgStrokeWidth) * normalizedProgress),
                centerY,
                fgPaint
            )
        }
    }
}