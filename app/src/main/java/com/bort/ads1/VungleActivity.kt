package com.bort.ads1

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.vungle.warren.InitCallback
import com.vungle.warren.Vungle
import com.vungle.warren.error.VungleException
import javax.security.auth.callback.Callback

class VungleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admob_activity)
        /*
        val context = this
        val init_btn = findViewById<Button>(R.id.init)
        val init_status_btn = findViewById<Button>(R.id.init_status)
        val load_btn = findViewById<Button>(R.id.load)
        val play_btn = findViewById<Button>(R.id.play)
        val logTextView = findViewById<TextView>(R.id.log)
        val scrollView = findViewById<ScrollView>(R.id.scrollview)
        val legend_textview = findViewById<TextView>(R.id.legend)
*/


    }
}