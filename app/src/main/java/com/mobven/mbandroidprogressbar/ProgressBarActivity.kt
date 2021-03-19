package com.mobven.mbandroidprogressbar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.mobven.progress.MBCircularProgressBar
import com.mobven.progress.MBHorizontalProgressBar
import com.mobven.progress.MBVerticalProgressBar

class ProgressBarActivity: AppCompatActivity() {

    private lateinit var circularProgressBar: MBCircularProgressBar
    private lateinit var verticalProgressBar: MBVerticalProgressBar
    private lateinit var horizontalProgressBar: MBHorizontalProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        val type = intent.getIntExtra(TYPE,0)

        var progress = 50f
        val increaseButton = findViewById<MaterialButton>(R.id.button_increase)
        val decreaseButton = findViewById<MaterialButton>(R.id.button_decrease)
        circularProgressBar = findViewById(R.id.circular_progress_bar)
        verticalProgressBar = findViewById(R.id.vertical_progress_bar)
        horizontalProgressBar = findViewById(R.id.horizontal_progress_bar)

        when(type){
            TYPE_CIRCULAR -> circularProgressBar.visibility = View.VISIBLE
            TYPE_VERTICAL -> verticalProgressBar.visibility = View.VISIBLE
            TYPE_HORIZONTAL -> horizontalProgressBar.visibility = View.VISIBLE
        }

        increaseButton.setOnClickListener {
            if (progress != 100f){
                progress += 10f
                setProgress(type, progress)
            }
        }

        decreaseButton.setOnClickListener {
            if (progress != 0f){
                progress -= 10f
                setProgress(type, progress)
            }
        }
    }

    private fun setProgress(type: Int,progress: Float){
        when(type){
            TYPE_CIRCULAR -> circularProgressBar.setProgressWithAnimation(progress)
            TYPE_VERTICAL -> verticalProgressBar.setProgressWithAnimation(progress)
            TYPE_HORIZONTAL -> horizontalProgressBar.setProgressWithAnimation(progress)
        }
    }

    companion object {

        const val TYPE = "TYPE"
        const val TYPE_CIRCULAR = 0
        const val TYPE_VERTICAL = 1
        const val TYPE_HORIZONTAL = 2

    }
}