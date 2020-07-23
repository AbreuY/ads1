package com.bort.ads1;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;

public class VungleActivity extends AppCompatActivity {

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

        final PlayAdCallback vunglePlayAdCallback = new PlayAdCallback() {
            @Override
            public void onAdStart(String id) {
                // Ad experience started
                appendlog("onAdStart");
            }

            @Override
            public void onAdEnd(String id, boolean completed, boolean isCTAClicked) {
                appendlog("onAdEnd completed:" + completed + " isCTAClicked:" + isCTAClicked);
            }

            @Override
            public void onAdEnd(String id) {
                appendlog("onAdEnd");
            }

            @Override
            public void onAdClick(String id) {
                // User clicked on ad
                appendlog("onAdClick");
            }

            @Override
            public void onAdRewarded(String id) {
                // User earned reward for watching an ad
                appendlog("onAdRewarded");
            }

            @Override
            public void onAdLeftApplication(String id) {
                // User has left app during an ad experience
                appendlog("onAdLeftApplication");
            }

            @Override
            public void onError(String id, VungleException exception) {
                // Ad failed to play
                appendlog("onError");
            }
        };

        init_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Init Button Pressed");
                Vungle.init(appId, getApplicationContext(), new InitCallback() {
                    @Override
                    public void onSuccess() {
                        // SDK has successfully initialized
                        appendlog("onSuccess");
                    }

                    @Override
                    public void onError(VungleException exception) {
                        // SDK has failed to initialize
                        appendlog("onError");
                    }

                    @Override
                    public void onAutoCacheAdAvailable(String placementId) {
                        // Ad has become available to play for a cache optimized placement
                        appendlog("onAutoCacheAdAvailable");
                    }
                });
            }
        });

        init_status_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Init Status Button Pressed");
                appendlog("" + Vungle.isInitialized());
            }
        });

        load_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Load Button Pressed");
                if (Vungle.isInitialized()) {
                    Vungle.loadAd(placementId, new LoadAdCallback() {
                        @Override
                        public void onAdLoad(String placementReferenceId) {
                            appendlog("onAdLoad");
                        }

                        @Override
                        public void onError(String placementReferenceId, VungleException exception) {
                            appendlog("onError");
                        }
                    });
                }
            }
        });

        checkCanPlayAd_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Check Can Play Button Pressed");
                appendlog("can play ad:" + Vungle.canPlayAd(placementId));
            }
        });

        play_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                appendlog(logtag + "Play Button Pressed");
                Vungle.playAd(placementId, null, vunglePlayAdCallback);
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
