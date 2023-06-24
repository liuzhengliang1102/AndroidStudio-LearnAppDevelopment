package com.example.activitylifetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = "lzl-test-SecondaryActivity";
    private String mString = new String();
    private TextView mTextView;

    private void printLifecycle(String string) {
        Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + string);
        mString = String.format("%s[%s] %s: %s\n", mString, Utils.getNowTimeMs(), "Secondary", string);
        mTextView.setText(mString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        if (savedInstanceState == null) {
            mTextView = (TextView)findViewById(R.id.textView_secondary);
            findViewById(R.id.button_return).setOnClickListener(this);
        }

        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onCreate...");
        printLifecycle("onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onRestart...");
        printLifecycle("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onStart...");
        printLifecycle("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onResume...");
        printLifecycle("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onPause...");
        printLifecycle("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onStop...");
        printLifecycle("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onDestroy...");
        printLifecycle("onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onNewIntent...");
        printLifecycle("onNewIntent");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_return) {
            finish();
        }
    }
}