package com.dillon.networkmonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Broadcast Receiver that is registered to received CONNECTIVITY_CHANGE events.
 */
public class DillonNetworkMonitorReceiver extends BroadcastReceiver {

    private static final String CLASSTAG = DillonNetworkMonitorReceiver.class.getSimpleName() + " ";

    private boolean networkWasConnected = false;

    @Override
    public void onReceive(Context context, Intent intent) {
         /**** check intent action???
         if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {

         }
         ************/

        Log.d(Constants.LOGTAG, CLASSTAG + " +++++++++++++++++");
        Log.d(Constants.LOGTAG, CLASSTAG + " onReceive - Enter");
        Log.d(Constants.LOGTAG, CLASSTAG + " +++++++++++++++++");

        // boolean networkConnected = intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY);
        boolean networkConnected = DillonNetworkSettings.isNetworkAvailable(context);

        Log.d(Constants.LOGTAG, " " + DillonNetworkMonitorReceiver.CLASSTAG + "EXTRA_NO_CONNECTIVITY = " + intent.getExtras().getBoolean(ConnectivityManager.EXTRA_NO_CONNECTIVITY));
        Log.d(Constants.LOGTAG, " " + DillonNetworkMonitorReceiver.CLASSTAG + "IsNetworkAvailable = " + networkConnected);

        // Process the network connection change, but use an intent service
        // since a Broadcast Receiver runs on the main thread.
        //
        // Intent service is used to update app status and data when the
        // network goes switches between offline and online modes.
        Intent myIntent = new Intent(context, DillonNetworkStatusChangeIntentService.class);
        if ((!networkWasConnected) && (networkConnected)) {
            myIntent.putExtra(Constants.NETWORK_STATUS_STRING, Constants.NETWORK_ONLINE_STRING);
            context.startService(intent);
        }
        else if ((networkWasConnected) && (!networkConnected)) {
            myIntent.putExtra(Constants.NETWORK_STATUS_STRING, Constants.NETWORK_OFFLINE_STRING);
            context.startService(intent);
        }

        networkWasConnected = networkConnected;

        DillonNetworkSettings.setNetworkStatus();
        DillonNetworkSettings.setWifiStatusValues();
        DillonNetworkSettings.setCellularStatusValues((TelephonyManager)context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE));

        Log.d(Constants.LOGTAG, CLASSTAG + " ----------------");
        Log.d(Constants.LOGTAG, CLASSTAG + " onReceive - Exit");
        Log.d(Constants.LOGTAG, CLASSTAG + " ----------------");
    }
}
