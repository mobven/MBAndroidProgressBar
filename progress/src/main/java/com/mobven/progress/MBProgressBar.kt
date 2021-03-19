package com.mobven.progress

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat

open class MBProgressBar : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        attrs?.let(::readAttributes)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        attrs?.let(::readAttributes)
    }

    var bgStrokeWidth =
        context.resources.getDimension(R.dimen.mb_progress_bg_stroke)
        set(value){
            field = value
            invalidate()
        }
    var fgStrokeWidth =
        context.resources.getDimension(R.dimen.mb_progress_fg_stroke)
        set(value){
            field = value
            invalidate()
        }

    protected var bgPaint = createBgPaint(ContextCompat.getColor(context, R.color.white_alpha_30))
        set(value){
            field = value
            invalidate()
        }
    protected var fgPaint = createFgPaint(ContextCompat.getColor(context, R.color.white))
        set(value){
            field = value
            invalidate()
        }

    protected var normalizedProgress = 0f

    var min = 0f
        set(value) {
            field = value
            calculateNormalizedProgress()
            invalidate()
        }

    var max = 100f
        set(value) {
            field = value
            calculateNormalizedProgress()
            invalidate()
        }

    var progress = 0f
        set(value) {
            field = value
            calculateNormalizedProgress()
            invalidate()
        }

    private fun readAttributes(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.MBAndroidProgressBar, 0, 0
        ).apply {
            min = getFloat(R.styleable.MBAndroidProgressBar_min, 0f)
            max = getFloat(R.styleable.MBAndroidProgressBar_max, 100f)
            progress = if(getFloat(R.styleable.MBAndroidProgressBar_progress, 0f) > max){
                max
            }
            else {
                getFloat(R.styleable.MBAndroidProgressBar_progress, 0f)
            }
            fgStrokeWidth = getDimension(R.styleable.MBAndroidProgressBar_fgWidth, context.resources.getDimension(R.dimen.mb_progress_fg_stroke))
            bgStrokeWidth = getDimension(R.styleable.MBAndroidProgressBar_bgWidth, context.resources.getDimension(R.dimen.mb_progress_bg_stroke))

            bgPaint = createBgPaint(
                getColor(
                    R.styleable.MBAndroidProgressBar_bgColor,
                    ContextCompat.getColor(context, R.color.white_alpha_30)
                )
            )

            fgPaint = createFgPaint(
                getColor(
                    R.styleable.MBAndroidProgressBar_fgColor,
                    ContextCompat.getColor(context, R.color.white)
                )
            )

            recycle()
        }
    }

    private fun createBgPaint(withColor: Int) = Paint(ANTI_ALIAS_FLAG).apply {
        color = withColor
        style = Paint.Style.STROKE
        strokeWidth = bgStrokeWidth
        strokeCap = Paint.Cap.ROUND
    }

    private fun createFgPaint(withColor: Int) = Paint(ANTI_ALIAS_FLAG).apply {
        color = withColor
        style = Paint.Style.STROKE
        strokeWidth = fgStrokeWidth
        strokeCap = Paint.Cap.ROUND
    }

    private fun calculateNormalizedProgress() {
        normalizedProgress = (progress - min) / (max - min)
    }

    @SuppressLint("AnimatorKeep")
    fun setProgressWithAnimation(progress: Float){
        val tempProgress = if (progress > max) max else progress
        ObjectAnimator.ofFloat(this,"progress",tempProgress).apply {
            duration = ANIMATION_DURATION
            interpolator = DecelerateInterpolator()
        }.start()
    }

    companion object{
        private const val ANIMATION_DURATION = 1250L
    }
}