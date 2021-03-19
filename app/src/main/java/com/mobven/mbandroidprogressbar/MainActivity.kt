package com.mobven.mbandroidprogressbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.mobven.mbandroidprogressbar.ProgressBarActivity.Companion.TYPE
import com.mobven.mbandroidprogressbar.ProgressBarActivity.Companion.TYPE_CIRCULAR
import com.mobven.mbandroidprogressbar.ProgressBarActivity.Companion.TYPE_HORIZONTAL
import com.mobven.mbandroidprogressbar.ProgressBarActivity.Companion.TYPE_VERTICAL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.circular).setOnClickListener {
            startActivity(Intent(this,ProgressBarActivity::class.java).apply {
                putExtra(TYPE, TYPE_CIRCULAR)
            })
        }

        findViewById<MaterialButton>(R.id.vertical).setOnClickListener {
            startActivity(Intent(this,ProgressBarActivity::class.java).apply {
                putExtra(TYPE, TYPE_VERTICAL)
            })
        }

        findViewById<MaterialButton>(R.id.horizontal).setOnClickListener {
            startActivity(Intent(this,ProgressBarActivity::class.java).apply {
                putExtra(TYPE, TYPE_HORIZONTAL)
            })
        }
    }
}