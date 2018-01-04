package com.oomph.lotadata;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;

import com.lotadata.moments.Moments;
import com.lotadata.moments.MomentsClient;
import org.json.JSONObject;

import java.util.Map;

public class MomentsPlugin extends CordovaPlugin {

    private static final String TAG = "LOTADATA";
    private static final String RECORD_EVENT = "recordEvent";
    private Moments momentsClient;

//    @Override
//    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
//        super.initialize(cordova, webView);
//    }
//
//    @Override
//    public void onNewIntent(Intent intent) {
//        cordova.getActivity().setIntent(intent);
//        Context context = cordova.getActivity().getApplicationContext();
//        Intent intent = new Intent(context, com.moxoomphindonesia.gmobi.MainActivity.class);
//
//        cordova.getActivity().startActivity(intent);
//        momentsClient = MomentsClient.getInstance(cordova.getActivity());
//    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "Executing...");

//        if("recordEvent".equals(action))
//        {
//            return trackEvent(data, callbackContext);
//        }
//        return false;

        if (RECORD_EVENT.equals(action)) {
            String name = data.getString(0);
            Context context = cordova.getActivity().getApplicationContext();
            Intent intent = new Intent(context, com.moxoomphindonesia.gmobi.MainActivity.class);
            intent.putExtra("name",name);
            context.startActivity(intent);
//            momentsClient.recordEvent(name);
            callbackContext.success(name);
            return true;
        } else {
            return false;
        }
    }

    private boolean trackEvent(JSONArray parameters, final CallbackContext callbackContext) {
        String eventName;
        Map<String, Object> eventValues = null;
        try{
            eventName = parameters.getString(0);
        }
        catch (JSONException e){
            e.printStackTrace();
            return true;
        }

        if(eventName == null || eventName.trim().length()==0){
            return true;
        }
        Log.d(TAG, eventName);
        Context c = this.cordova.getActivity().getApplicationContext();
        momentsClient.recordEvent(eventName);

        return true;
    }

    @Override
    public void onDestroy() {
        momentsClient.disconnect();
        super.onDestroy();
    }
}
