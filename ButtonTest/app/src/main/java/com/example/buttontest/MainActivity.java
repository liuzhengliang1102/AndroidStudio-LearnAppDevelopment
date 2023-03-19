package com.example.buttontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private final static String TAG = "lzl-test-Button";

    private TextView mTextView;
    private Button mButton1, mButton2, mButton3;
    private int mButton3onClickCount = 0;

    private Button mLongClickButton1, mLongClickButton2, mLongClickButton3;
    private TextView mTextViewForLongClick;

    private Button mButtonEnable, mButtonDisable, mButtonTest;
    private TextView mTextViewForTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: " + Utils.getNowDateTime());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = String.format("%s: 你点击了按键：%s",
                        Utils.getNowTimeMs(), ((Button) v).getText());
                mTextView.setText(string);
            }
        });

        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(new MyOnClickListener());

        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mButton3.setAllCaps(false);

        // 长按
        mLongClickButton1 = (Button) findViewById(R.id.longClickButton1);
        mLongClickButton2 = (Button) findViewById(R.id.longClickButton2);
        mLongClickButton3 = (Button) findViewById(R.id.longClickButton3);
        mLongClickButton1.setOnLongClickListener(this);
        mLongClickButton2.setOnLongClickListener(this);
        mLongClickButton3.setOnLongClickListener(this);
        mTextViewForLongClick = (TextView) findViewById(R.id.textViewForLongClick);

        // 启用、禁用测试
        mButtonEnable = (Button) findViewById(R.id.button_enable);
        mButtonDisable = (Button) findViewById(R.id.button_disable);
        mButtonTest = (Button) findViewById(R.id.button_test);
        mButtonEnable.setOnClickListener(this);
        mButtonDisable.setOnClickListener(this);
        mButtonTest.setOnClickListener(this);
        mTextViewForTest = (TextView) findViewById(R.id.textViewForTest);
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == R.id.longClickButton1 || v.getId() == R.id.longClickButton2 || v.getId() == R.id.longClickButton3) {
            String string = String.format("%s: 你长按了按键：%s",
                    Utils.getNowTimeMs(), ((Button) v).getText());
            mTextViewForLongClick.setText(string);
            return true;
        }
        return false;
    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String string = String.format("%s: 你点击了按键：%s",
                    Utils.getNowTimeMs(), ((Button) v).getText());
            mTextView.setText(string);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button3) {
            boolean isAllCaps = (boolean) ((++mButton3onClickCount) % 2 == 1);
            String string = String.format("%s: 你点击了按键：%s",
                    Utils.getNowTimeMs(), ((Button) v).getText());
            mTextView.setText(string);
            mButton3.setAllCaps(isAllCaps);
        } else if (v.getId() == R.id.button_enable) {
            String string = String.format("%s: 你点击了按键：%s, 请观察【测试按键】的状态",
                    Utils.getNowTimeMs(), ((Button) v).getText());
            mTextViewForTest.setText(string);
            mButtonTest.setEnabled(true);
        } else if (v.getId() == R.id.button_disable) {
            String string = String.format("%s: 你点击了按键：%s, 请观察【测试按键】的状态",
                    Utils.getNowTimeMs(), ((Button) v).getText());
            mTextViewForTest.setText(string);
            mButtonTest.setEnabled(false);
        } else if (v.getId() == R.id.button_test) {
            String string = String.format("%s: 你点击了按键：%s",
                    Utils.getNowTimeMs(), ((Button) v).getText());
            mTextViewForTest.setText(string);
        }
    }
}