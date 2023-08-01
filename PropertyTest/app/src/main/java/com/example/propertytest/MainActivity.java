package com.example.propertytest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
//import android.os.SystemProperties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "lzl-test";
    private static final String MODEL = "ro.product.model";
    private static final String DEVICE = "ro.product.device";
    private static final String VERSION_RELEASE = "ro.build.version.release";
    private String mPropertyName;
    private String mPropertyValue;
    private Button mButtonGetProperty;
    private Button mButtonSetProperty;
    private EditText mEditTextPropertyName;
    private EditText mEditTextPropertyValue;
    private TextView mTextViewGetPropertyValue;
    private String mInputPropertyName;
    private String mInputPropertyValue;

    private int mColor = Color.RED;
    private int onClickCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ro.product.model
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        // ro.product.device
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        // ro.build.version.release
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView1_val = (TextView) findViewById(R.id.textView1_val);
        TextView textView2_val = (TextView) findViewById(R.id.textView2_val);
        TextView textView3_val = (TextView) findViewById(R.id.textView3_val);

        mPropertyName = textView1.getText().toString();
        if (mPropertyName.equals(MODEL)) {
            textView1_val.setText(Build.MODEL);
            Log.d(TAG, "getprop " + DEVICE + " by Build.MODEL = " + Build.MODEL);
        }
        mPropertyName = textView2.getText().toString();
        if (mPropertyName.equals(DEVICE)) {
            textView2_val.setText(Build.DEVICE);
            Log.d(TAG, "getprop " + DEVICE + " by Build.DEVICE = " + Build.DEVICE);
        }
        mPropertyName = textView3.getText().toString();
        if (mPropertyName.equals(VERSION_RELEASE)) {
            textView3_val.setText(Build.VERSION.RELEASE);
            Log.d(TAG, "getprop " + VERSION_RELEASE + " by Build.VERSION.RELEASE = " + Build.VERSION.RELEASE);
        }

        // ro.product.model
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        // ro.product.device
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        // ro.build.version.release
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        TextView textView4_val = (TextView) findViewById(R.id.textView4_val);
        TextView textView5_val = (TextView) findViewById(R.id.textView5_val);
        TextView textView6_val = (TextView) findViewById(R.id.textView6_val);
        mPropertyName = textView4.getText().toString();
        if (!mPropertyName.equals(MODEL)) {
            mPropertyName = MODEL;
        }
        mPropertyValue = getProperty(mPropertyName,"unknown");
        Log.d(TAG, mPropertyName + ": " + mPropertyValue);
        textView4.setText(mPropertyName);
        textView4_val.setText(mPropertyValue);
        textView4_val.setTextColor(Color.RED);

        mPropertyName = textView5.getText().toString();
        if (!mPropertyName.equals(DEVICE)) {
            mPropertyName = DEVICE;
        }
        mPropertyValue = getProperty(mPropertyName,"unknown");
        Log.d(TAG, mPropertyName + ": " + mPropertyValue);
        textView5.setText(mPropertyName);
        textView5_val.setText(mPropertyValue);
        textView5_val.setTextColor(Color.RED);

        mPropertyName = textView6.getText().toString();
        if (!mPropertyName.equals(VERSION_RELEASE)) {
            mPropertyName = VERSION_RELEASE;
        }
        mPropertyValue = getProperty(mPropertyName,"unknown");
        Log.d(TAG, mPropertyName + ": " + mPropertyValue);
        textView6.setText(mPropertyName);
        textView6_val.setText(mPropertyValue);
        textView6_val.setTextColor(Color.RED);

        mEditTextPropertyName = (EditText) findViewById(R.id.input_prop_name);
        mEditTextPropertyValue = (EditText) findViewById(R.id.input_prop_value);

        mTextViewGetPropertyValue = (TextView) findViewById(R.id.tv_get_prop_value);

        mButtonGetProperty = (Button) findViewById(R.id.button_get);
        mButtonGetProperty.setOnClickListener(this);

        mButtonSetProperty = (Button) findViewById(R.id.button_set);
        mButtonSetProperty.setOnClickListener(this);
    }

    public static String getProperty(String key, String defaultValue) {
        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, "unknown"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return value;
        }
    }

    public static int setProperty(String key, String value) {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method set = c.getMethod("set", String.class, String.class);
            set.invoke(c, key, value);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void onClick(View v) {
        onClickCount++;
        if (v.getId() == R.id.button_get) {
            if (!mEditTextPropertyName.getText().toString().isEmpty()) {
                mInputPropertyName = mEditTextPropertyName.getText().toString();
                mPropertyValue = getProperty(mInputPropertyName,"unknown");
                Log.d(TAG, mInputPropertyName + ": " + mPropertyValue);
                mTextViewGetPropertyValue.setText(mPropertyValue);
                mColor = (onClickCount % 2 == 0) ? Color.RED : Color.BLUE;
                mTextViewGetPropertyValue.setTextColor(mColor);
            }
        } else if (v.getId() == R.id.button_set) {
            if (!mEditTextPropertyName.getText().toString().isEmpty()) {
                mInputPropertyName = mEditTextPropertyName.getText().toString();
                if (!mEditTextPropertyValue.getText().toString().isEmpty()) {
                    mInputPropertyValue = mEditTextPropertyValue.getText().toString();
                    if (setProperty(mInputPropertyName, mInputPropertyValue) != 0) {
                        Toast.makeText(getApplicationContext(), "set property \"" + mInputPropertyName + "\" to \"" + mInputPropertyValue + "\" failed", 1000).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "请输入有效的Property Value", 1000).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "请输入有效的Property Name", 1000).show();
            }
        }
    }
}