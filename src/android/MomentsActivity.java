package com.oomph.lotadata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.util.Log;
import com.lotadata.moments.Moments;
import com.lotadata.moments.MomentsClient;

public class MomentsActivity extends Activity {

    private static final String TAG = "LOTADATA";
    private Moments momentsClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "==> MomentsActivity onCreate");

        momentsClient = MomentsClient.getInstance(this);

        // Retrieve intent for MomentsPlugin
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if(name != null) {
            Log.d(TAG, "EVENT NAME: " + name);
            if (momentsClient != null) {
                Log.d(TAG, "TRUE");
                momentsClient.recordEvent(name);
            } else {
                Log.d(TAG, "FALSE");
            }
        }

        finish();

//        forceMainActivityReload();
    }

    private void forceMainActivityReload() {
        PackageManager pm = getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(getApplicationContext().getPackageName());
        startActivity(launchIntent);
    }

    protected void onDestroy() {;
        if (momentsClient != null) {
            momentsClient.disconnect();
        }
        super.onDestroy();
    }
}