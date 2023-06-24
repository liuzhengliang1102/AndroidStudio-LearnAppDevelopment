package com.example.displaycolormodetest;


import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "lzl-test-MainActivity";
    private Display mDisplay;
    private ImageView mImageView;
    private TextView mTextView;
    private Button mButton_srgb;
    private Button mButton_p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); //设置屏幕不随手机旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //设置屏幕直向显示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //设置屏幕全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //设置屏幕不进入休眠

        boolean isWcgSupport = getResources().getConfiguration().isScreenWideColorGamut();
        Log.d(TAG, "getResources().getConfiguration().isScreenWideColorGamut(): " + isWcgSupport);

        mDisplay = getWindowManager().getDefaultDisplay();
        Log.d(TAG, "display.isWideColorGamut(): " + mDisplay.isWideColorGamut());
        Log.d(TAG, "display.getPixelFormat(): " + mDisplay.getPixelFormat());
        Log.d(TAG, "display.getPreferredWideGamutColorSpace(): " + mDisplay.getPreferredWideGamutColorSpace());
        Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());

        mImageView = (ImageView)findViewById(R.id.imageView);

        getWindow().setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
        mTextView = (TextView)findViewById(R.id.textView);
        mTextView.setText("P3色域图片使用P3色域显示");
        mTextView.setTextColor(Color.RED);

        mButton_srgb = (Button)findViewById(R.id.button_srgb);
        mButton_srgb.setOnClickListener(this);
        mButton_p3 = (Button)findViewById(R.id.button_p3);
        mButton_p3.setOnClickListener(this);

        Button button_goto = (Button)findViewById(R.id.button_goto);
        button_goto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button)v;
        if (button == mButton_srgb) {
            // COLOR_MODE_DEFAULT for colorMode indicating that the activity
            // should use the default color mode (sRGB, low dynamic range).
            getWindow().setColorMode(ActivityInfo.COLOR_MODE_DEFAULT);
            mTextView.setText("P3色域图片使用sRGB色域显示");
            Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());
        } else if (button == mButton_p3) {
            // COLOR_MODE_WIDE_COLOR_GAMUT of colorMode indicating that the activity
            // should use a wide color gamut if the presentation display supports it.
            getWindow().setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
            mTextView.setText("P3色域图片使用P3色域显示");
            Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());
        } else if (v.getId() == R.id.button_goto) {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        } else {
            Log.e(TAG, "Unknown button id!");
        }
    }
}