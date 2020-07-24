package com.bort.ads1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val admob_btn = findViewById<Button>(R.id.admob)
        val vungle_btn = findViewById<Button>(R.id.vungle)
        val ironsource_btn = findViewById<Button>(R.id.ironsource)

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

    }
}