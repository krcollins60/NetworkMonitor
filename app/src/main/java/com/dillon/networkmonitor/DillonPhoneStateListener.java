package com.dillon.networkmonitor;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.util.Log;

/**
 * Receives events related to Phone state changes.
 * We use this to get RSSI (signal strength).
 * Note that signal strength values vary among device manufactures so we cannot
 * reliably use this value to determine if cellular signal is strong enough for
 * communications or too low to avoid communications.
 */

class DillonPhoneStateListener extends PhoneStateListener {

    private static final String CLASSTAG = DillonPhoneStateListener.class.getSimpleName() + " ";
    private Context mContext;


    public DillonPhoneStateListener(Context context) {
        mContext = context;
    }

    @Override
    public void onDataActivity(int direction) {
        super.onDataActivity(direction);

        Log.v(Constants.LOGTAG, CLASSTAG + "onDataActivity - Enter");
        DillonNetworkSettings.setCellularDataActivityValue(direction);
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);

        Log.v(Constants.LOGTAG, CLASSTAG + "onServiceStateChanged - Enter");
        DillonNetworkSettings.setCellularServiceStateValue(serviceState);
    }


    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);

        Log.v(Constants.LOGTAG, CLASSTAG + "onSignalStrengthsChanged - Enter, SS = " + signalStrength.getGsmSignalStrength());

        int rssi;

        // GSM valid values are (0-31, 99) as defined in TS 27.007 8.5
        if (signalStrength.isGsm()) {
            rssi = signalStrength.getGsmSignalStrength();
            if (rssi != 99) {
                rssi = (rssi * 2) - 113;  // convert to dBm
            }
        } else {
            rssi = signalStrength.getCdmaDbm();
        }

        DillonNetworkSettings.setCellularRssiValue(rssi);
    }
}
