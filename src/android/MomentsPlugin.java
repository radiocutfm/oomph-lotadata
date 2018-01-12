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

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "Executing...");

        if (RECORD_EVENT.equals(action)) {
            String name = data.getString(0);

            // Sent intent to MomentsActivity
            Context context = cordova.getActivity().getApplicationContext();
            Intent intent = new Intent(context, MomentsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name",name);
            context.startActivity(intent);

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

}
