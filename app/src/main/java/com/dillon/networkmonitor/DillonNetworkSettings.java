package com.dillon.networkmonitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.io.IOException;

public class DillonNetworkSettings {

    public static TextView networkConnectionStateTextView;

    public static TextView wifiStateTextView;
    public static TextView wifiSsidTextView;
    public static TextView wifiRssiTextView;

    public static TextView cellularStateTextView;
    public static TextView cellularPhoneTypeTextView;
    public static TextView cellularNetworkTypeTextView;
    public static TextView cellularOperatorTextView;
    public static TextView cellularRoamingTextView;
    public static TextView cellularRssiTextView;

    public static Context mContext;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                // if (mNetworkInfo.isConnectingOrConnected() {
                if (mNetworkInfo.isConnected()) {
                    return true;
                }
            }
        }
        return false;

        // return (mConnectivityManager != null && mConnectivityManager.getActiveNetworkInfo().isConnectingOrConencted()) ? true : false;
    }

    public static boolean isOnline() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    public static void setNetworkStatus() {
        // if (isNetworkAvailable(mContext)) {
        if (isOnline()) {
            networkConnectionStateTextView.setText("Online");
        }
        else {
            networkConnectionStateTextView.setText("Offline");
        }
    }

    public static void setWifiStatusValues() {
        WifiManager wifiManager = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);

        int wifiState = wifiManager.getWifiState();
        switch (wifiState) {
            case WifiManager.WIFI_STATE_DISABLED:
                wifiStateTextView.setText(R.string.wifi_state_disabled);
                break;
            case WifiManager.WIFI_STATE_DISABLING:
                wifiStateTextView.setText(R.string.wifi_state_disabling);
                break;
            case WifiManager.WIFI_STATE_ENABLED:
                wifiStateTextView.setText(R.string.wifi_state_enabled);
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                wifiStateTextView.setText(R.string.wifi_state_enabling);
                break;
            case WifiManager.WIFI_STATE_UNKNOWN:
                wifiStateTextView.setText(R.string.wifi_state_unknown);
                break;
            default:
                wifiStateTextView.setText(R.string.wifi_state_unknown);
                break;
        }

        wifiSsidTextView.setText(wifiManager.getConnectionInfo().getSSID());

        wifiRssiTextView.setText("" + wifiManager.getConnectionInfo().getRssi());
    }

    public static void setCellularStatusValues(final TelephonyManager telephonyManager) {
        DillonNetworkSettings.setCellularPhoneTypeValue(telephonyManager.getPhoneType());
        DillonNetworkSettings.setCellularNetworkTypeValue(telephonyManager.getNetworkType());
    }

    public static void setCellularServiceStateValue(final ServiceState serviceState) {
        switch (serviceState.getState()) {
            case ServiceState.STATE_EMERGENCY_ONLY:
                cellularStateTextView.setText(R.string.cellular_state_emergency_only);
                break;
            case ServiceState.STATE_IN_SERVICE:
                cellularStateTextView.setText(R.string.cellular_state_in_service);
                break;
            case ServiceState.STATE_OUT_OF_SERVICE:
                cellularStateTextView.setText(R.string.cellular_state_out_of_service);
                break;
            case ServiceState.STATE_POWER_OFF:
                cellularStateTextView.setText(R.string.cellular_state_power_off);
                break;
            default:
                cellularStateTextView.setText(R.string.cellular_state_unknown);
                break;
        }

        cellularOperatorTextView.setText(serviceState.getOperatorAlphaLong());

        if (serviceState.getRoaming()) {
            cellularRoamingTextView.setText("True");
        }
        else {
            cellularRoamingTextView.setText("False");
        }
    }

    public static void setCellularPhoneTypeValue(final int type) {
        switch (type) {
            case TelephonyManager.PHONE_TYPE_CDMA:
                cellularPhoneTypeTextView.setText("CDMA");
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                cellularPhoneTypeTextView.setText("GSM");
                break;
            case TelephonyManager.PHONE_TYPE_NONE:
                cellularPhoneTypeTextView.setText("None");
                break;
            case TelephonyManager.PHONE_TYPE_SIP:
                cellularPhoneTypeTextView.setText("SIP");
                break;
            default:
                cellularPhoneTypeTextView.setText("Unknown");
                break;
        }
    }

    public static void setCellularNetworkTypeValue(final int type) {
        switch (type) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                cellularNetworkTypeTextView.setText("1xRTT");
                break;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                cellularNetworkTypeTextView.setText("CDMA");
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                cellularNetworkTypeTextView.setText("EDGE");
                break;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                cellularNetworkTypeTextView.setText("EHRPD");
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                cellularNetworkTypeTextView.setText("EVDO_0");
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                cellularNetworkTypeTextView.setText("EVDO_A");
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                cellularNetworkTypeTextView.setText("EVDO_B");
                break;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                cellularNetworkTypeTextView.setText("GPRS");
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                cellularNetworkTypeTextView.setText("HSDPA");
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                cellularNetworkTypeTextView.setText("HSPA");
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                cellularNetworkTypeTextView.setText("HSPAP");
                break;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                cellularNetworkTypeTextView.setText("HSUPA");
                break;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                cellularNetworkTypeTextView.setText("IDEN");
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                cellularNetworkTypeTextView.setText("LTE");
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                cellularNetworkTypeTextView.setText("UMTS");
                break;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                cellularNetworkTypeTextView.setText("Unknown");
                break;
            default:
                cellularNetworkTypeTextView.setText("Unknown");
                break;
        }
    }

    public static void setCellularRssiValue(final int rssi) {
        cellularRssiTextView.setText("" + rssi);
    }

    public static void setCellularDataActivityValue(final int direction) {
        switch (direction) {
            case TelephonyManager.DATA_ACTIVITY_NONE:

                break;
            case TelephonyManager.DATA_ACTIVITY_IN:

                break;
            case TelephonyManager.DATA_ACTIVITY_OUT:

                break;
            case TelephonyManager.DATA_ACTIVITY_INOUT:

                break;
            case TelephonyManager.DATA_ACTIVITY_DORMANT:

                break;
            default:

                break;
        }
    }
}
