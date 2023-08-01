package com.example.widecolorgamuttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "lzl-test-MainActivity";

    private TextView mTextViewMainWcgShow;

    private ColorSpace mColorSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); //设置屏幕不随手机旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //设置屏幕竖屏显示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //设置屏幕全屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //设置屏幕不进入休眠

        boolean isWcgSupport = getResources().getConfiguration().isScreenWideColorGamut();
        Log.d(TAG, "getResources().getConfiguration().isScreenWideColorGamut(): " + isWcgSupport);

        Display display = getWindowManager().getDefaultDisplay();
        Log.d(TAG, "display.isWideColorGamut(): " + display.isWideColorGamut());

        Log.d(TAG, "display.getPreferredWideGamutColorSpace(): " + display.getPreferredWideGamutColorSpace());
        Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());

        TextView textView = (TextView) findViewById(R.id.textViewMainWcgSupport);
        textView.setText("设备支持广色域颜色呈现: " + isWcgSupport);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewMain);
        imageView.setImageResource(R.drawable.android_p3);

        mColorSpace = Utils.getImageColorSpace(getResources(), R.drawable.android_p3);
        TextView textViewMainImageInfo = (TextView) findViewById(R.id.textViewMainImageInfo);
        textViewMainImageInfo.setText("图片信息: " + mColorSpace.toString());

        getWindow().setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
        mTextViewMainWcgShow = (TextView)findViewById(R.id.textViewMainWcgShow);
        mTextViewMainWcgShow.setText(mColorSpace.getName() + "色域图片使用P3色域显示");
        mTextViewMainWcgShow.setTextColor(Color.RED);

        findViewById(R.id.buttonMainSRGB).setOnClickListener(this);
        findViewById(R.id.buttonMainP3).setOnClickListener(this);
        findViewById(R.id.buttonMainGotoNext).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonMainSRGB) {
            // COLOR_MODE_DEFAULT for colorMode indicating that the activity
            // should use the default color mode (sRGB, low dynamic range).
            getWindow().setColorMode(ActivityInfo.COLOR_MODE_DEFAULT);
            mTextViewMainWcgShow.setText(mColorSpace.getName() + "色域图片使用sRGB色域显示");
            Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());
        } else if (v.getId() == R.id.buttonMainP3) {
            // COLOR_MODE_WIDE_COLOR_GAMUT of colorMode indicating that the activity
            // should use a wide color gamut if the presentation display supports it.
            getWindow().setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
            mTextViewMainWcgShow.setText(mColorSpace.getName() + "色域图片使用P3色域显示");
            Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());
        } else if (v.getId() == R.id.buttonMainGotoNext) {
            startActivity(new Intent(MainActivity.this, NestActivity.class));
        } else {
            Log.e(TAG, "Unknown button id!");
        }
    }
}