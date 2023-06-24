package com.example.activitylifetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "lzl-test-MainActivity";
    private String mString = new String();
    private TextView mTextView;

    private void printLife(String string) {
        Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + string);
        mString = String.format("%s[%s] %s: %s\n", mString, Utils.getNowTimeMs(), "Main", string);
        mTextView.setText(mString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mTextView = (TextView)findViewById(R.id.textViewz_main);
            findViewById(R.id.button_to_next).setOnClickListener(this);
        }

        //Log.d(TAG, "[" + Utils.getNowTimeMs() + "]" + "onCreate...");
        printLife("onCreate");
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_to_next) {
            startActivity(new Intent(this, SecondaryActivity.class));
        }
    }
}