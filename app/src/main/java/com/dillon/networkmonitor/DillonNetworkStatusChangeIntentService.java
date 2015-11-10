package com.dillon.networkmonitor;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * This service is started to perform actions related to going between Offline and Online modes.
 */
public class DillonNetworkStatusChangeIntentService extends IntentService {

    private static final String CLASSTAG = DillonNetworkStatusChangeIntentService.class.getSimpleName() + " ";

    // private SQLiteDatabase dillonDB = null;  // for reading and fetching data when going between offline & online modes

    public DillonNetworkStatusChangeIntentService() {
        super("DillonNetworkStatusChangeIntentService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Log.d(Constants.LOGTAG, CLASSTAG + "onHandleIntent - Enter");

        SystemClock.sleep(10000);  // avoid bounces
        if (DillonNetworkSettings.isNetworkAvailable(getBaseContext())) {
            SystemClock.sleep(10000);  // double confirm
            if (DillonNetworkSettings.isNetworkAvailable(getBaseContext())) {
                performOnlineModeSwitch();
            }
        }
        else if (!DillonNetworkSettings.isNetworkAvailable(getBaseContext())) {
            performOfflineModeSwitch();
        }

        Log.d(Constants.LOGTAG, CLASSTAG + "onHandleIntent - Exit");
    }

    private void performOnlineModeSwitch() {
        Log.d(Constants.LOGTAG, CLASSTAG + "performOnlineModeSwitch - Enter");
    }

    private void performOfflineModeSwitch() {
        Log.d(Constants.LOGTAG, CLASSTAG + "performOfflineModeSwitch - Enter");
    }
}
