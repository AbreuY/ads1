package com.bort.ads1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.vungle.warren.AdConfig;
import com.vungle.warren.InitCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;

public class VungleActivity extends AppCompatActivity {
    private Button loadAdBtn;
    private Button playAdBtn;
    private TextView logTextView;
    private ScrollView scrollView;
    private Context context = this;
    private AdConfig globalAdConfig;
    private final String appId = "5a91fbcce1b3413c03002403";
    private final String placementID1 = "DEFAULT-4820184";
    private final String placementID2 = "INT-2079169";
    private String LOG_TAG = "berttest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vungle);
        //getSupportActionBar().hide();
        loadAdBtn = findViewById(R.id.load);
        playAdBtn = findViewById(R.id.play);
        logTextView = findViewById(R.id.log);
        scrollView = findViewById(R.id.scrollview);

        Vungle.init(appId, getApplicationContext(), new InitCallback() {
            @Override
            public void onSuccess() {
                // SDK has successfully initialized
                log("init onSuccess");
            }

            @Override
            public void onError(VungleException exception) {
                // SDK has failed to initialize
            }

            @Override
            public void onAutoCacheAdAvailable(String placementId) {
                // Ad has become available to play for a cache optimized placement
            }
        };

        loadAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("Load Ad button pressed");
            }
        });

        playAdBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                log("Play Ad button pressed");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void log(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run(){
                if (logTextView.length() > 0)
                    logTextView.append("\n");
                logTextView.append(text);
            }
        });

        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        }, 500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    };
}

