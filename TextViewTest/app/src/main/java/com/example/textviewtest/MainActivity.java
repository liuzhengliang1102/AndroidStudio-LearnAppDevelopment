package com.example.textviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "lzl-test-Textview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(R.string.textView_hello);

        TextView textView_setTextSize = (TextView) findViewById(R.id.textView_setTextSize);
        textView_setTextSize.setTextSize(20);

        TextView textView_setLayoutParams = (TextView) findViewById(R.id.textView_setLayoutParams);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        Log.i(TAG, "getLayoutParams:width:" + layoutParams.width + ", height:" + layoutParams.height);
        layoutParams.width = dp2px(this, 400);
        layoutParams.height = dp2px(this, 20);
        textView_setLayoutParams.setBackgroundColor(Color.GREEN);
        textView_setLayoutParams.setText("setLayoutParams:width:" + 400 + ", height:" + 20);
        textView_setLayoutParams.setLayoutParams(layoutParams);

        TextView textView_java_color = (TextView) findViewById(R.id.textView_java_color);
        textView_java_color.setTextColor(Color.RED);

        TextView textView_java_color1 = (TextView) findViewById(R.id.textView_java_color1);
        textView_java_color1.setTextColor(0xFFFF0000); //ARGB

        TextView textView_java_background = (TextView) findViewById(R.id.textView_java_background);
        textView_java_background.setTextColor(Color.RED);
        textView_java_background.setBackgroundColor(Color.GRAY);
        //textView_java_background.setBackgroundColor(0xFF888888);

        TextView textView_java_background1 = (TextView) findViewById(R.id.textView_java_background1);
        textView_java_background1.setTextColor(Color.RED);
        textView_java_background1.setBackgroundResource(R.color.gray);
    }

    int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    int px2dip(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}