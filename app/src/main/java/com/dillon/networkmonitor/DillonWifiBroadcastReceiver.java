package com.dillon.networkmonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.List;

/**
 * Broadcast Receiver that is registered to received WIFI change events.
 */

public class DillonWifiBroadcastReceiver extends BroadcastReceiver {
    WifiManager mWifiManager;

    private static final String CLASSTAG = DillonWifiBroadcastReceiver.class.getSimpleName() + " ";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v(Constants.LOGTAG, CLASSTAG + "onReceive - Enter");

        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (mWifiManager.isWifiEnabled()) {
            List<ScanResult> results = mWifiManager.getScanResults();
            for (ScanResult result : results) {
                Log.i("DillonWifiBroadcastReceiver ", "result.SSID: " + result.SSID + "result.capabilities: " + result
                        .capabilities + "result.frequency: " + result.frequency + "result.level: "
                        + result.level);
            }
        }

        DillonNetworkSettings.setNetworkStatus();
        DillonNetworkSettings.setWifiStatusValues();
        // DillonNetworkSettings.setCellularStatusValues((TelephonyManager)context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE));
    }
}