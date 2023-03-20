package com.example.animationdrawabletest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  implements
        View.OnClickListener{
    private static final String TAG = "lzl-test-AnimationDrawable-Test";
    private ImageView mImageViewByCode, mImageViewByXml;
    private Button mButtonByCode, mButtonByXml;
    private AnimationDrawable mAnimationDrawableByCode, mAnimationDrawableByXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageViewByCode = (ImageView) findViewById(R.id.imageView_code);
        mImageViewByCode.setOnClickListener(this);
        mButtonByCode = (Button) findViewById(R.id.button_code);
        mButtonByCode.setOnClickListener(this);
        mButtonByCode.setText("开始");

        mImageViewByXml = (ImageView) findViewById(R.id.imageView_xml);
        mImageViewByXml.setOnClickListener(this);
        mButtonByXml = (Button) findViewById(R.id.button_xml);
        mButtonByXml.setOnClickListener(this);
        mButtonByXml.setText("开始");

        setAnimationDrawableByCode();
        setAnimationDrawableByXml();
    }

    private void setAnimationDrawableByCode() {
        mAnimationDrawableByCode = new AnimationDrawable();

        // 下面图片加入到AnimationDrawable的列表中
        mAnimationDrawableByCode.addFrame(getDrawable(R.drawable.fingerprint_unlock_1), 50);
        mAnimationDrawableByCode.addFrame(getDrawable(R.drawable.fingerprint_unlock_2), 50);
        mAnimationDrawableByCode.addFrame(getDrawable(R.drawable.fingerprint_unlock_3), 50);
        mAnimationDrawableByCode.addFrame(getDrawable(R.drawable.fingerprint_unlock_4), 50);
        mAnimationDrawableByCode.addFrame(getDrawable(R.drawable.fingerprint_unlock_5), 50);

        // false表示循环播放
        mAnimationDrawableByCode.setOneShot(false);
        mImageViewByCode.setImageDrawable(mAnimationDrawableByCode);
        mAnimationDrawableByCode.start(); // 开始播放帧动画
    }

    private void setAnimationDrawableByXml() {
        // 设置从animation_list.xml获取
        mImageViewByXml.setImageResource(R.drawable.animation_list);
        mAnimationDrawableByXml = (AnimationDrawable) mImageViewByXml.getDrawable();
        /*mImageViewByXml.setBackgroundResource(R.drawable.animation_list);
        mAnimationDrawableByXml = (AnimationDrawable) mImageViewByXml.getBackground();*/
        mAnimationDrawableByXml.start(); // 开始播放帧动画
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_code || v.getId() == R.id.imageView_code) {
            // 判断帧动画是否正在播放
            if (mAnimationDrawableByCode.isRunning()) {
                mAnimationDrawableByCode.stop(); // 停止播放帧动画
                mButtonByCode.setText("开始");
            } else {
                mAnimationDrawableByCode.start(); // 开始播放帧动画
                mButtonByCode.setText("暂停");
            }
        }

        if (v.getId() == R.id.button_xml || v.getId() == R.id.imageView_xml) {
            // 判断帧动画是否正在播放
            if (mAnimationDrawableByXml.isRunning()) {
                mAnimationDrawableByXml.stop(); // 停止播放帧动画
                mButtonByXml.setText("开始");
            } else {
                mAnimationDrawableByXml.start(); // 开始播放帧动画
                mButtonByXml.setText("暂停");
            }
        }
    }
}