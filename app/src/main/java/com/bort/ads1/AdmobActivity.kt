package com.bort.ads1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.admob_activity.*


class AdmobActivity : AppCompatActivity() {
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admob_activity)

        val init = findViewById<Button>(R.id.init)
        val init_status = findViewById<Button>(R.id.init_status)
        val load = findViewById<Button>(R.id.load)
        val play = findViewById<Button>(R.id.play)


        init.setOnClickListener {
            MobileAds.initialize(context) {}
        }

        init_status.setOnClickListener {
            val initstatus = MobileAds.getInitializationStatus()
            initstatus.



            logthis(MobileAds.getInitializationStatus())
        }


    }

    fun logthis(text: CharSequence) {
        val logTextView = findViewById<TextView>(R.id.log)
        val scrollView = findViewById<ScrollView>(R.id.scrollview)

        runOnUiThread {
            if (logTextView.length() > 0) logTextView.append("\n")
            logTextView.append(text)
        }
        scrollView.postDelayed(Runnable { scrollView.fullScroll(View.FOCUS_DOWN) }, 500)
    }
}