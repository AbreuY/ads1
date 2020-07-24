package com.adviewer.gc.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.InterstitialListener;

public class IronsourceActivity extends AppCompatActivity {
    private Button load_btn, play_btn, init_btn, init_status_btn, checkCanPlayAd_btn;
    private TextView log_tv, legend_textview;
    private ScrollView scrollview;
    private String appKey, placementName;
    private String TAG = "adwatcher ironsource";
    private String logtag = "Ad Watcher:";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ironsource2);
        context = this;
        load_btn = findViewById(R.id.load);
        play_btn = findViewById(R.id.play);
        log_tv = findViewById(R.id.log);
        scrollview = findViewById(R.id.scrollview);
        init_btn = findViewById(R.id.init);
        init_status_btn = findViewById(R.id.init_status);
        checkCanPlayAd_btn = findViewById(R.id.playcheck);
        legend_textview = findViewById(R.id.legend);
        appKey = "ce7adbcd";
        placementName = "DefaultInterstitial";


        IronSource.setInterstitialListener(new InterstitialListener() {
            /**
             Invoked when Interstitial Ad is ready to be shown after load function was called.
             */
            @Override
            public void onInterstitialAdReady() {
                appendlog("onInterstitialAdReady");
            }
            /**
             invoked when there is no Interstitial Ad available after calling load function.
             */
            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
                appendlog("onInterstitialAdLoadFailed");
            }
            /**
             Invoked when the Interstitial Ad Unit is opened
             */
            @Override
            public void onInterstitialAdOpened() {
                appendlog("onInterstitialAdOpened");
            }
            /*
             * Invoked when the ad is closed and the user is about to return to the application.
             */
            @Override
            public void onInterstitialAdClosed() {
                appendlog("onInterstitialAdClosed");
            }
            /*
             * Invoked when the ad was opened and shown successfully.
             */
            @Override
            public void onInterstitialAdShowSucceeded() {
                appendlog("onInterstitialAdShowSucceeded");
            }
            /**
             * Invoked when Interstitial ad failed to show.
             // @param error - An object which represents the reason of showInterstitial failure.
             */
            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {
                appendlog("onInterstitialAdShowFailed");
            }
            /*
             * Invoked when the end user clicked on the interstitial ad.
             */
            @Override
            public void onInterstitialAdClicked() {
                appendlog("onInterstitialAdClicked");
            }
        });

        IronSource.init(this, appKey, IronSource.AD_UNIT.INTERSTITIAL);

        legend_textview.setText("Callbacks:\n" +
                "onInterstitialAdReady\n" +
                "onInterstitialAdLoadFailed\n" +
                "onInterstitialAdOpened\n" +
                "onInterstitialAdClosed\n" +
                "onInterstitialAdShowSucceeded\n" +
                "onInterstitialAdShowFailed\n" +
                "onInterstitialAdClicked");

        init_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Init Button Pressed");

            }
        });

        load_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Load Button Pressed");
                IronSource.loadInterstitial();
            }
        });

        checkCanPlayAd_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Check Can Play Button Pressed");
                appendlog("isInterstitialReady:" + IronSource.isInterstitialReady());
            }
        });

        play_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Play Button Pressed");
                IronSource.showInterstitial(placementName);
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

    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }
}
