package com.example.propertytest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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

        TextView textView = (TextView) findViewById(R.id.textView);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView_val = (TextView) findViewById(R.id.textView_val);
        TextView textView2_val = (TextView) findViewById(R.id.textView2_val);
        TextView textView3_val = (TextView) findViewById(R.id.textView3_val);

        mEditTextPropertyName = (EditText) findViewById(R.id.input_prop_name);
        mEditTextPropertyValue = (EditText) findViewById(R.id.input_prop_value);

        mTextViewGetPropertyValue = (TextView) findViewById(R.id.tv_get_prop_value);

        mButtonGetProperty = (Button) findViewById(R.id.button_get);
        mButtonGetProperty.setOnClickListener(this);

        mButtonSetProperty = (Button) findViewById(R.id.button_set);
        mButtonSetProperty.setOnClickListener(this);

        mPropertyName = "ro.product.model";
        mPropertyValue = getProperty(mPropertyName,"unknown");
        Log.d(TAG, mPropertyName + ":" + mPropertyValue);
        textView.setText(mPropertyName);
        textView_val.setText(mPropertyValue);
        textView_val.setTextColor(Color.RED);

        mPropertyName = "ro.display.screen_type";
        mPropertyValue = getProperty(mPropertyName,"unknown");
        Log.d(TAG, mPropertyName + ":" + mPropertyValue);
        textView2.setText(mPropertyName);
        textView2_val.setText(mPropertyValue);
        textView2_val.setTextColor(Color.RED);

        mPropertyName = "ro.product.marketname";
        mPropertyValue = getProperty(mPropertyName,"unknown");
        Log.d(TAG, mPropertyName + ":" + mPropertyValue);
        textView3.setText(mPropertyName);
        textView3_val.setText(mPropertyValue);
        textView3_val.setTextColor(Color.RED);
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
                Log.d(TAG, mInputPropertyName + ":" + mPropertyValue);
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