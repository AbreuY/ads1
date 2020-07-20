package com.bort.ads1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.admob_activity.*


class AdmobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admob_activity)
        val context = this
        val init_btn = findViewById<Button>(R.id.init)
        val init_status_btn = findViewById<Button>(R.id.init_status)
        val load_btn = findViewById<Button>(R.id.load)
        val play_btn = findViewById<Button>(R.id.play)
        val logTextView = findViewById<TextView>(R.id.log)
        val scrollView = findViewById<ScrollView>(R.id.scrollview)
        val legend_textview = findViewById<TextView>(R.id.legend)

        val mInterstitialAd = InterstitialAd(context)
        val appid = "ca-app-pub-3940256099942544/1033173712"

        legend_textview.setText("Callbacks:\n" +
                "Init Status\n" +
                "onAdLoaded\n" +
                "onAdFailedToLoad\n" +
                "onAdOpened\n" +
                "onAdClicked\n" +
                "onAdLeftApplication\n" +
                "onAdClosed")

        fun appendlog(text: String) {
            runOnUiThread {
                if (logTextView.length() > 0) logTextView.append("\n")
                logTextView.append(text)
            }
            scrollView.postDelayed(Runnable { scrollView.fullScroll(View.FOCUS_DOWN) }, 500)
        }

        init_btn.setOnClickListener {
            appendlog("Ad Watcher: Init Button Pressed")
            MobileAds.initialize(context)
            mInterstitialAd.adUnitId = appid
            mInterstitialAd.adListener = object: AdListener() {
                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                    appendlog("onAdLoaded")
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    // Code to be executed when an ad request fails.
                    appendlog("onAdFailedToLoad")
                }

                override fun onAdOpened() {
                    // Code to be executed when the ad is displayed.
                    appendlog("onAdOpened")
                }

                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                    appendlog("onAdClicked")
                }

                override fun onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                    appendlog("onAdLeftApplication")
                }

                override fun onAdClosed() {
                    // Code to be executed when the interstitial ad is closed.
                    appendlog("onAdClosed")
                }
            }
        }



        init_status_btn.setOnClickListener {
            appendlog("Ad Watcher: Init Status Button Pressed")
            val initstatus_map = MobileAds.getInitializationStatus().adapterStatusMap
            for ((k, v) in initstatus_map) {
                appendlog("$k : ${v.initializationState}")
            }
        }

        load_btn.setOnClickListener {
            appendlog("Ad Watcher: Load Button Pressed")
            mInterstitialAd.loadAd(AdRequest.Builder().build())
        }

        play_btn.setOnClickListener {
            appendlog("Ad Watcher: Play Button Pressed")
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
        }
    }
}