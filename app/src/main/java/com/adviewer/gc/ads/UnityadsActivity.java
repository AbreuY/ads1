package com.adviewer.gc.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import kotlin.Unit;


public class UnityadsActivity extends AppCompatActivity {

    private Button play_btn, init_btn, checkCanPlayAd_btn;
    private TextView log_tv, legend_textview;
    private ScrollView scrollview;
    private String TAG = "adwatcher vungle";
    private String logtag = "Ad Watcher:";
    private Context context;

    private String unityGameID = "3730971";
    private String placementId = "testplacement";
    private UnityAdsListener myAdsListener;
    private boolean testmode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unityads);
        play_btn = findViewById(R.id.play);
        log_tv = findViewById(R.id.log);
        scrollview = findViewById(R.id.scrollview);
        init_btn = findViewById(R.id.init);
        checkCanPlayAd_btn = findViewById(R.id.playcheck);
        legend_textview = findViewById(R.id.legend);
        context = this;

        myAdsListener = new UnityAdsListener ();

        legend_textview.setText("Callbacks:\n" +
                "onUnityAdsReady\n" +
                "onUnityAdsStart\n" +
                "onUnityAdsFinish\n" +
                "onUnityAdsError");

        init_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Init Button Pressed");
               init_unityads();
            }
        });

        checkCanPlayAd_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + UnityAds.isReady(placementId));
            }
        });
        play_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Play Button Pressed");
                play_ad();
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

    public void init_unityads(){
        UnityAds.initialize(this, unityGameID, myAdsListener, testmode);
    }

    public void play_ad(){
        UnityAds.show(this, placementId);
    }

    // Implement the IUnityAdsListener interface methods:
    private class UnityAdsListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady (String placementId) {
            // Implement functionality for an ad being ready to show.
            appendlog("onUnityAdsReady");
        }

        @Override
        public void onUnityAdsStart (String placementId) {
            // Implement functionality for a user starting to watch an ad.
            appendlog("onUnityAdsStart");
        }

        @Override
        public void onUnityAdsFinish (String placementId, UnityAds.FinishState finishState) {
            // Implement functionality for a user finishing an ad.
            appendlog("onUnityAdsFinish");
        }

        @Override
        public void onUnityAdsError (UnityAds.UnityAdsError error, String message) {
            // Implement functionality for a Unity Ads service error occurring.
            appendlog("onUnityAdsError");
        }
    }
}