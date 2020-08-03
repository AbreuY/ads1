package com.adviewer.gc.ads

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.Button
import com.google.android.gms.ads.RequestConfiguration
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val admob_btn = findViewById<Button>(R.id.admob)
        val vungle_btn = findViewById<Button>(R.id.vungle)
        val ironsource_btn = findViewById<Button>(R.id.ironsource)
        val unityads_btn = findViewById<Button>(R.id.unityads)

        //ironsource works 100% but they did not make account live
        ironsource_btn.visibility = INVISIBLE

        admob_btn.setOnClickListener {
            val intent = Intent(this, AdmobActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        vungle_btn.setOnClickListener {
            val intent = Intent(this, VungleActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        ironsource_btn.setOnClickListener {
            val intent = Intent(this, IronsourceActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        unityads_btn.setOnClickListener {
            val intent = Intent(this, UnityadsActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

    }
}