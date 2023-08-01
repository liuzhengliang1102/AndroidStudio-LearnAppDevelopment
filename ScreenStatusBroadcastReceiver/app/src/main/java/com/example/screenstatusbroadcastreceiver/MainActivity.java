package com.example.screenstatusbroadcastreceiver;


import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "lzl-test";
    private ScreenReceiver mScreenReceiver;
    private TextView mTextView;
    private String mString = new String();
    private String mStringLife = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.textView);
        checkScreenStatusByPowerManager();
        registerScreenReceiver();
        printLife("onCreate");
    }

    /* 获取屏幕状态通过PowerManager */
    private void checkScreenStatusByPowerManager() {
        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        // Android 4.4W (KitKat Wear)系统及以上使用新接口获取亮屏状态
        if (Build.VERSION.SDK_INT >= 20) {
            isScreenOn = pm.isInteractive();
        } else {
            isScreenOn = pm.isScreenOn();
        }
        if (isScreenOn) {
            printScreenStatus("first On");
        } else {
            printScreenStatus("first Off");
        }
    }

    /* 注册监听 */
    private void registerScreenReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mScreenReceiver = new ScreenReceiver();
        registerReceiver(mScreenReceiver, filter);
    }

    /* 监听亮屏和灭屏事件 */
    private class ScreenReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                printScreenStatus("On");
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                printScreenStatus("Off");
            }
        }
    }

    private String getNowTimeMs() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        return simpleDateFormat.format(new Date());
    }

    private void printLife(String string) {
        Log.d(TAG, "[" + getNowTimeMs() + "]" + string);
        mStringLife = String.format("%s[%s] %s: %s\n", mStringLife, getNowTimeMs(), "Main", string);
    }

    private void printScreenStatus(String string) {
        Log.d(TAG, "[" + getNowTimeMs() + "]" + "screen is " + string);
        mString = String.format("%s[%s] screen is: %s\n", mString, getNowTimeMs(), string);
        mTextView.setText(mString);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onRestart...");
        printLife("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onStart...");
        printLife("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onResume...");
        printLife("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onPause...");
        printLife("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onStop...");
        printLife("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onDestroy...");
        printLife("onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onNewIntent...");
        printLife("onNewIntent");
    }
}
