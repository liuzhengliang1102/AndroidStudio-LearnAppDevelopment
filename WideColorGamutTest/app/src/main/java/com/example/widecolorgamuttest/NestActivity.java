package com.example.widecolorgamuttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NestActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "lzl-test-NestActivity";
    private TextView mTextViewNextWcgShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); //设置屏幕不随手机旋转
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //设置屏幕竖屏显示

        boolean isWcgSupport = getResources().getConfiguration().isScreenWideColorGamut();
        Log.d(TAG, "getResources().getConfiguration().isScreenWideColorGamut(): " + isWcgSupport);

        Display display = getWindowManager().getDefaultDisplay();
        Log.d(TAG, "display.isWideColorGamut(): " + display.isWideColorGamut());

        Log.d(TAG, "display.getPreferredWideGamutColorSpace(): " + display.getPreferredWideGamutColorSpace());
        Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());

        TextView textView = (TextView) findViewById(R.id.textViewNextWcgSupport);
        textView.setText("设备支持广色域颜色呈现: " + isWcgSupport);

        ImageView imageView_sRGB = (ImageView)findViewById(R.id.imageViewNextSrgb);
        imageView_sRGB.setImageResource(R.drawable.srgb);
        ImageView imageView_p3 = (ImageView)findViewById(R.id.imageViewNextP3);
        imageView_p3.setImageResource(R.drawable.display_p3);
        ImageView imageView_BT2020 = (ImageView)findViewById(R.id.imageViewNextBT2020);
        imageView_BT2020.setImageResource(R.drawable.bt2020_1);

        ColorSpace colorSpace = Utils.getImageColorSpace(getResources(), R.drawable.srgb);
        TextView textViewNextSrgbInfo = (TextView) findViewById(R.id.textViewNextSrgbInfo);
        textViewNextSrgbInfo.setText("图片信息: " + colorSpace.toString());
        colorSpace = Utils.getImageColorSpace(getResources(), R.drawable.display_p3);
        TextView textViewNextP3Info = (TextView) findViewById(R.id.textViewNextP3Info);
        textViewNextP3Info.setText("图片信息: " + colorSpace.toString());
        colorSpace = Utils.getImageColorSpace(getResources(), R.drawable.bt2020_1);
        TextView textViewNextBT2020Info = (TextView) findViewById(R.id.textViewNextBT2020Info);
        textViewNextBT2020Info.setText("图片信息: " + colorSpace.toString());


        getWindow().setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
        mTextViewNextWcgShow = (TextView)findViewById(R.id.textViewNextWcgShow);
        mTextViewNextWcgShow.setText("使用P3色域显示");
        mTextViewNextWcgShow.setTextColor(Color.RED);

        findViewById(R.id.buttonNextSRGB).setOnClickListener(this);
        findViewById(R.id.buttonNextP3).setOnClickListener(this);
        findViewById(R.id.buttonNextReturn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonNextSRGB) {
            // COLOR_MODE_DEFAULT for colorMode indicating that the activity
            // should use the default color mode (sRGB, low dynamic range).
            getWindow().setColorMode(ActivityInfo.COLOR_MODE_DEFAULT);
            mTextViewNextWcgShow.setText("sRGB色域显示");
            Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());
        } else if (v.getId() == R.id.buttonNextP3) {
            // COLOR_MODE_WIDE_COLOR_GAMUT of colorMode indicating that the activity
            // should use a wide color gamut if the presentation display supports it.
            getWindow().setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
            mTextViewNextWcgShow.setText("P3色域显示");
            Log.d(TAG, "getWindow().getColorMode(): " + getWindow().getColorMode());
        } else if (v.getId() == R.id.buttonNextReturn){
            finish();
        } else {
            Log.e(TAG, "Unknown button id!");
        }
    }
}