package com.example.screenbrightnesstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "lzl-test";
    private Context mContext;

    private int mRoScreenBrightnessSettingMinimum;
    private int mRoScreenBrightnessSettingMaximum;

    private TextView mTextViewScreenBrightnessRange;

    private EditText mEditTextInputIntBrightnessMin;
    private int mInputIntIntBrightnessMin;
    private EditText mEditTextInputIntBrightnessMax;
    private int mInputIntIntBrightnessMax;
    private EditText mEditTextInputIntBrightness;
    private int mInputIntBrightness;
    private EditText mEditTextInputIntBrightnessAddStep;
    private int mInputIntBrightnessAddStep = 1;

    private TextView mTextViewOutputInfo;

    private int mColor = Color.RED;
    private int onClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        //获取系统最小屏幕亮度:config_screenBrightnessSettingMinimum
        mRoScreenBrightnessSettingMinimum = getScreenBrightnessSettingMinimum(mContext);
        Log.d(TAG, "config_screenBrightnessSettingMinimum:" + mRoScreenBrightnessSettingMinimum);
        //获取系统最大屏幕亮度:config_screenBrightnessSettingMaximum
        mRoScreenBrightnessSettingMaximum = getscreenBrightnessSettingMaximum(mContext);
        Log.d(TAG, "config_screenBrightnessSettingMaximum:" + mRoScreenBrightnessSettingMaximum);


        TextView textView =  (TextView)findViewById(R.id.tv_screenBrightnessSettingMinimum_val);
        textView.setText(String.format("%d",mRoScreenBrightnessSettingMinimum));
        textView =  (TextView)findViewById(R.id.tv_screenBrightnessSettingMaximum_val);
        textView.setText(String.format("%d",mRoScreenBrightnessSettingMaximum));

        mInputIntIntBrightnessMin = mRoScreenBrightnessSettingMinimum;
        mInputIntIntBrightnessMax = mRoScreenBrightnessSettingMaximum;

        mEditTextInputIntBrightnessMin = (EditText)findViewById(R.id.editTextNumber_screenBrightnessMinimum);
        mEditTextInputIntBrightnessMin.setText(String.format("%d",mInputIntIntBrightnessMin));

        mEditTextInputIntBrightnessMax = (EditText)findViewById(R.id.editTextNumber_screenBrightnessMaximum);
        mEditTextInputIntBrightnessMax.setText(String.format("%d",mInputIntIntBrightnessMax));

        mTextViewScreenBrightnessRange = (TextView)findViewById(R.id.tv_ScreenBrightness_range);
        mTextViewScreenBrightnessRange.setText(String.format("ScreenBrightness 测试，输入范围[%d~%d]",mInputIntIntBrightnessMin, mInputIntIntBrightnessMax));

        mEditTextInputIntBrightness = (EditText)findViewById(R.id.editTextNumber_screenBrightness);
        mInputIntBrightness = mInputIntIntBrightnessMax/2;
        mEditTextInputIntBrightness.setText(String.format("%d",mInputIntBrightness));

        mEditTextInputIntBrightnessAddStep = (EditText)findViewById(R.id.editTextNumber_screenBrightnessAddStep);
        mInputIntBrightnessAddStep = 1;
        mEditTextInputIntBrightnessAddStep.setText(String.format("%d",mInputIntBrightnessAddStep));

        findViewById(R.id.button_set_screenBrightnessMinimum).setOnClickListener(this);
        findViewById(R.id.button_set_screenBrightnessMaximum).setOnClickListener(this);
        findViewById(R.id.button_set_screenBrightness).setOnClickListener(this);
        findViewById(R.id.button_set_screenBrightnessAddStep).setOnClickListener(this);
        findViewById(R.id.button_add).setOnClickListener(this);

        findViewById(R.id.button_add).setOnClickListener(this);
        mTextViewOutputInfo = (TextView) findViewById(R.id.tv_output_info);
    }

    //获取系统最小屏幕亮度:config_screenBrightnessSettingMinimum
    private int getScreenBrightnessSettingMinimum(Context context) {
        int screenBrightnessSettingMinimumId = getResources().getIdentifier("config_screenBrightnessSettingMinimum", "integer", "android");
        int screenBrightnessSettingMinimum = getResources().getInteger(screenBrightnessSettingMinimumId);
        return screenBrightnessSettingMinimum;
    }
    //获取系统最大屏幕亮度:config_screenBrightnessSettingMaximum
    private int getscreenBrightnessSettingMaximum(Context context) {
        int screenBrightnessSettingMaximumId = context.getResources().getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android");
        int screenBrightnessSettingMaximum = context.getResources().getInteger(screenBrightnessSettingMaximumId);
        return screenBrightnessSettingMaximum;
    }

    //修改当前Activity界面的窗口亮度
    private void setWindowScreenBrightness(int brightness) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        Log.d(TAG, "Brightness:" + brightness + ", Max Brightness:" + mInputIntIntBrightnessMax);
        lp.screenBrightness = (float) brightness / (float)mInputIntIntBrightnessMax;
        window.setAttributes(lp);
    }

    private void showTextViewOutputInfo() {
        mColor = (onClickCount % 2 == 0) ? Color.RED : Color.BLUE;
        String str = String.format("范围：%d~%d\nint brightness[%d], float brightness[%f]\n自增步长: %d\n",
                mInputIntIntBrightnessMin, mInputIntIntBrightnessMax, mInputIntBrightness,
                (float) mInputIntBrightness / (float)mInputIntIntBrightnessMax, mInputIntBrightnessAddStep);
        mTextViewOutputInfo.setText(str);
        mTextViewOutputInfo.setTextColor(mColor);

    }
    @Override
    public void onClick(View v) {
        onClickCount++;
        if (v.getId() == R.id.button_set_screenBrightnessMinimum) {
            if (!mEditTextInputIntBrightnessMin.getText().toString().isEmpty()) {
                mInputIntIntBrightnessMin = Integer.parseInt(mEditTextInputIntBrightnessMin.getText().toString());
            } else {
                mInputIntIntBrightnessMin = mRoScreenBrightnessSettingMaximum;
            }
            mTextViewScreenBrightnessRange.setText(String.format("ScreenBrightness 测试，输入范围[%d~%d]",mInputIntIntBrightnessMin, mInputIntIntBrightnessMax));
            showTextViewOutputInfo();
        } else if (v.getId() == R.id.button_set_screenBrightnessMaximum) {
            if (!mEditTextInputIntBrightnessMax.getText().toString().isEmpty()) {
                mInputIntIntBrightnessMax = Integer.parseInt(mEditTextInputIntBrightnessMax.getText().toString());
            } else {
                mInputIntIntBrightnessMax = mRoScreenBrightnessSettingMaximum;
            }
            mTextViewScreenBrightnessRange.setText(String.format("ScreenBrightness 测试，输入范围[%d~%d]",mInputIntIntBrightnessMin, mInputIntIntBrightnessMax));
            showTextViewOutputInfo();
        } else if (v.getId() == R.id.button_set_screenBrightness) {
            if (!mEditTextInputIntBrightness.getText().toString().isEmpty()) {
                mInputIntBrightness = Integer.parseInt(mEditTextInputIntBrightness.getText().toString());
                Log.d(TAG, "mIntBrightness:" + mInputIntBrightness);
                if (mInputIntBrightness < mInputIntIntBrightnessMin || mInputIntBrightness > mInputIntIntBrightnessMax) {
                    Toast.makeText(getApplicationContext(), "请输入有效int型Brightness,range[" + mInputIntIntBrightnessMin +"~" + mInputIntIntBrightnessMax +"]", 1000).show();
                } else {
                    setWindowScreenBrightness(mInputIntBrightness);
                    showTextViewOutputInfo();
                }
            } else {
                Toast.makeText(getApplicationContext(), "请输入有效的参数", 1000).show();
            }
        } else if (v.getId() == R.id.button_set_screenBrightnessAddStep) {
            if (!mEditTextInputIntBrightnessAddStep.getText().toString().isEmpty()) {
                mInputIntBrightnessAddStep = Integer.parseInt(mEditTextInputIntBrightnessAddStep.getText().toString());
                Log.d(TAG, "mIntBrightnessAddStep:" + mInputIntBrightnessAddStep);
                if ((mInputIntBrightnessAddStep + mInputIntBrightness) > mInputIntIntBrightnessMax) {
                    mInputIntBrightnessAddStep = 1;
                    Toast.makeText(getApplicationContext(), "请输入有效的参数", 1000).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "请输入有效的参数", 1000).show();
            }
            showTextViewOutputInfo();
        } else if (v.getId() == R.id.button_add) {
            mInputIntBrightness = mInputIntBrightness + mInputIntBrightnessAddStep;
            Log.d(TAG, "mIntBrightness:" + mInputIntBrightness);
            if (mInputIntBrightness > mInputIntIntBrightnessMax) {
                mInputIntBrightness = mInputIntIntBrightnessMax;
                Toast.makeText(getApplicationContext(), "Brightness超过了[" + mInputIntIntBrightnessMin +"~" + mInputIntIntBrightnessMax +"]范围", 1000).show();
            }
            setWindowScreenBrightness(mInputIntBrightness);
            showTextViewOutputInfo();
        }  else {
            Log.e(TAG, "未知按键");
        }
    }
}