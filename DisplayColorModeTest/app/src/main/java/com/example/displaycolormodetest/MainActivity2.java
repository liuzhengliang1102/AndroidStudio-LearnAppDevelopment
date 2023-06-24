package com.example.displaycolormodetest;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "lzl-test-MainActivity2";
    private Display mDisplay;
    private ImageView mImageView_sRGB;
    private ImageView mImageView_p3;
    private ImageView mImageView_BT2020;
    private TextView mTextView;
    private Button mButton_srgb;
    private Button mButton_p3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); //设置屏幕不随手机旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //设置屏幕直向显示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //设置屏幕全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //设置屏幕不进入休眠

        mImageView_sRGB = (ImageView)findViewById(R.id.imageView_srgb);
        mImageView_p3 = (ImageView)findViewById(R.id.imageView_p3);
        mImageView_BT2020 = (ImageView)findViewById(R.id.imageView_bt2020);

        getWindow().setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
        mTextView = (TextView)findViewById(R.id.textView2);
        mTextView.setText("P3色域显示");
        mTextView.setTextColor(Color.RED);
        mButton_srgb = (Button)findViewById(R.id.button2_srgb);
        mButton_srgb.setOnClickListener(this);
        mButton_p3 = (Button)findViewById(R.id.button2_p3);
        mButton_p3.setOnClickListener(this);

        Button button_return = (Button)findViewById(R.id.button_return);
        button_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button)v;
        if (button == mButton_srgb) {
            // COLOR_MODE_DEFAULT for colorMode indicating that the activity
            // should use the default color mode (sRGB, low dynamic range).
            getWindow().setColorMode(ActivityInfo.COLOR_MODE_DEFAULT);
            mTextView.setText("sRGB色域显示");
            Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());
        } else if (button == mButton_p3) {
            // COLOR_MODE_WIDE_COLOR_GAMUT of colorMode indicating that the activity
            // should use a wide color gamut if the presentation display supports it.
            getWindow().setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
            mTextView.setText("P3色域显示");
            Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());
        } else if (v.getId() == R.id.button_return){
            finish();
        } else {
            Log.e(TAG, "Unknown button id!");
        }
    }
}