package com.oomph.lotadata;

import android.content.Intent;
import android.os.Bundle;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import com.lotadata.moments.Moments;
import com.lotadata.moments.MomentsClient;

public class MomentsPlugin extends CordovaPlugin {

    private static final String RECORD_EVENT = "recordEvent";
    private Moments momentsClient;

    @Override
    public void onNewIntent(Intent intent) {
        cordova.getActivity().setIntent(intent);
//        AppsFlyerLib.getInstance().sendDeepLinkData(cordova.getActivity());

        momentsClient = MomentsClient.getInstance(cordova.getActivity());
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (RECORD_EVENT.equals(action)) {
            String name = data.getString(0);
            callbackContext.success(name);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onDestroy() {
        momentsClient.disconnect();
        super.onDestroy();
    }
}
