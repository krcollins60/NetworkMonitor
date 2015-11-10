package com.dillon.networkmonitor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String CLASSTAG = MainActivity.class.getSimpleName() + " ";

    private TelephonyManager mTelephonyManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        DillonNetworkSettings.mContext = getApplicationContext();

        DillonNetworkSettings.networkConnectionStateTextView = (TextView) findViewById(R.id.network_status_value_textview_id);

        DillonNetworkSettings.wifiStateTextView = (TextView) findViewById(R.id.wifi_state_value_textview_id);
        DillonNetworkSettings.wifiSsidTextView  = (TextView) findViewById(R.id.wifi_ssid_value_textview_id);
        DillonNetworkSettings.wifiRssiTextView  = (TextView) findViewById(R.id.wifi_rssi_value_textview_id);

        DillonNetworkSettings.cellularStateTextView       = (TextView) findViewById(R.id.cellular_state_value_textview_id);
        DillonNetworkSettings.cellularPhoneTypeTextView   = (TextView) findViewById(R.id.cellular_phone_type_value_textview_id);
        DillonNetworkSettings.cellularNetworkTypeTextView = (TextView) findViewById(R.id.cellular_network_type_value_textview_id);
        DillonNetworkSettings.cellularOperatorTextView    = (TextView) findViewById(R.id.cellular_operator_value_textview_id);
        DillonNetworkSettings.cellularRoamingTextView     = (TextView) findViewById(R.id.cellular_roaming_value_textview_id);
        DillonNetworkSettings.cellularRssiTextView        = (TextView) findViewById(R.id.cellular_rssi_value_textview_id);

        mTelephonyManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager != null) {
            mTelephonyManager.listen(new DillonPhoneStateListener(this),
                    PhoneStateListener.LISTEN_CALL_STATE
                            | PhoneStateListener.LISTEN_CELL_INFO // Requires API 17
                            | PhoneStateListener.LISTEN_CELL_LOCATION
                            | PhoneStateListener.LISTEN_DATA_ACTIVITY
                            | PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
                            | PhoneStateListener.LISTEN_SERVICE_STATE   // onServiceStateChanged()
                            | PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
                            | PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
                            | PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.v(Constants.LOGTAG, CLASSTAG + "onResume - Enter");

        DillonNetworkSettings.setNetworkStatus();
        DillonNetworkSettings.setWifiStatusValues();
        DillonNetworkSettings.setCellularStatusValues(mTelephonyManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(Constants.LOGTAG, CLASSTAG + "onDestroy - Enter");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**********************************************
    private void setNetworkStatus() {
       if (DillonNetworkSettings.IsNetworkAvailable(this)) {
           DillonNetworkSettings.networkConnectionStateTextView.setText("Online");
       }
       else {
           DillonNetworkSettings.networkConnectionStateTextView.setText("Offline");
       }
    }

    private void setWifiStatusValues() {
        WifiManager wifiManager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);

        int wifiState = wifiManager.getWifiState();
        switch (wifiState) {
            case WifiManager.WIFI_STATE_DISABLED:
                DillonNetworkSettings.wifiStateTextView.setText(R.string.wifi_state_disabled);
                break;
            case WifiManager.WIFI_STATE_DISABLING:
                DillonNetworkSettings.wifiStateTextView.setText(R.string.wifi_state_disabling);
                break;
            case WifiManager.WIFI_STATE_ENABLED:
                DillonNetworkSettings.wifiStateTextView.setText(R.string.wifi_state_enabled);
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                DillonNetworkSettings.wifiStateTextView.setText(R.string.wifi_state_enabling);
                break;
            case WifiManager.WIFI_STATE_UNKNOWN:
                DillonNetworkSettings.wifiStateTextView.setText(R.string.wifi_state_unknown);
                break;
            default:
                DillonNetworkSettings.wifiStateTextView.setText(R.string.wifi_state_unknown);
                break;
        }

        DillonNetworkSettings.wifiSsidTextView.setText(wifiManager.getConnectionInfo().getSSID());

        DillonNetworkSettings.wifiRssiTextView.setText("" + wifiManager.getConnectionInfo().getRssi());
    }

    private void setCellularStatusValues() {


    }
     *************************/
}
