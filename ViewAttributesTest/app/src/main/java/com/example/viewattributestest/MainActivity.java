package com.example.viewattributestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "lzl-test-ViewAttributes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        Log.i(TAG, "onCreate: layoutParams.width:" + layoutParams.width + ", layoutParams.height:" + layoutParams.height);
        textView.setBackgroundColor(Color.GREEN);
        textView.setText("setLayoutParams测试");
        layoutParams.width = dp2px(this, 200);
        layoutParams.height = dp2px(this, 50);
        textView.setLayoutParams(layoutParams);
        textView.setText("setLayoutParams:width:" + 200 + ", height:" + 50 + "(dp)");
    }

    private int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }
}