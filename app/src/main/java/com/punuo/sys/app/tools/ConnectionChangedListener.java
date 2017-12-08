package com.punuo.sys.app.tools;

import android.util.Log;

import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;

/**
 * Created by asus on 2017/8/14.
 */

public class ConnectionChangedListener implements ConnectionClassManager.ConnectionClassStateChangeListener {
    private static final String TAG = "ConnectionChangedListen";
    @Override
    public void onBandwidthStateChange(ConnectionQuality bandwidthState) {
        Log.d(TAG, bandwidthState.toString());
    }
}
