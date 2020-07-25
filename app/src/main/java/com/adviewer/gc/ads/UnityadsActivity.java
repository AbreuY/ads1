package com.adviewer.gc.ads;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;


public class UnityadsActivity extends AppCompatActivity {

    private Button load_btn, play_btn, init_btn, init_status_btn, checkCanPlayAd_btn;
    private TextView log_tv, legend_textview;
    private ScrollView scrollview;
    private String appId, placementId;
    private String TAG = "adwatcher vungle";
    private String logtag = "Ad Watcher:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vungle);
        load_btn = findViewById(R.id.load);
        play_btn = findViewById(R.id.play);
        log_tv = findViewById(R.id.log);
        scrollview = findViewById(R.id.scrollview);
        init_btn = findViewById(R.id.init);
        init_status_btn = findViewById(R.id.init_status);
        checkCanPlayAd_btn = findViewById(R.id.playcheck);
        legend_textview = findViewById(R.id.legend);

        appId = "5a91fbcce1b3413c03002403";
        placementId = "DEFAULT-4820184";

        legend_textview.setText("Callbacks:\n" +
                "Init onSuccess\n" +
                "Init onError\n" +
                "Init onAutoCacheAdAvailable\n" +
                "Load onAdLoad, onError\n" +
                "onAdStart, onAdEnd\n" +
                "onAdClick, onAdRewarded\n" +
                "onAdLeftApplication onError\n" +
                "onAdClosed");



        init_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Init Button Pressed");
            }
        });

        init_status_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Init Status Button Pressed");
            }
        });

        load_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Load Button Pressed");

            }
        });
        checkCanPlayAd_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Check Can Play Button Pressed");
            }
        });
        play_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Play Button Pressed");
            }
        });
    }

    private void appendlog(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run(){
                if (log_tv.length() > 0)
                    log_tv.append("\n");
                log_tv.append(text);
            }
        });

        scrollview.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(View.FOCUS_DOWN);
            }
        }, 500);
    }
}