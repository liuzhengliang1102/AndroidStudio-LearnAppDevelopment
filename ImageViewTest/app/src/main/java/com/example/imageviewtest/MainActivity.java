package com.example.imageviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "lzl-test-ImageViewTest";

    private ImageView mImageViewScaleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setImageResource(R.drawable.android);

        mImageViewScaleType = (ImageView) findViewById(R.id.imageViewScaleType);
        mImageViewScaleType.setImageResource(R.drawable.android); // 设置图像视图的图片资源
        ((Button) findViewById(R.id.button_fitXY)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_fitStart)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_fitCenter)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_fitEnd)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_center)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_centerCrop)).setOnClickListener(this);
        ((Button) findViewById(R.id.button_centerInside)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_fitXY) {
            // 将缩放类型设置为“缩放图片使其正好填满视图（图片可能被缩放变形）”
            mImageViewScaleType.setScaleType(ImageView.ScaleType.FIT_XY);
        } else if (v.getId() == R.id.button_fitStart) {
            // 将缩放类型设置为“保持宽高比例，缩放图片使其位于视图上方或左侧”
            mImageViewScaleType.setScaleType(ImageView.ScaleType.FIT_START);
        } else if (v.getId() == R.id.button_fitCenter) {
            // 将缩放类型设置为“保持宽高比例，缩放图片使其位于视图中间”
            mImageViewScaleType.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else if (v.getId() == R.id.button_fitEnd) {
            // 将缩放类型设置为“保持宽高比例，缩放图片使其位于视图下方或右侧”
            mImageViewScaleType.setScaleType(ImageView.ScaleType.FIT_END);
        } else if (v.getId() == R.id.button_center) {
            // 将缩放类型设置为“按照原尺寸居中显示”
            mImageViewScaleType.setScaleType(ImageView.ScaleType.CENTER);
        } else if (v.getId() == R.id.button_centerCrop) {
            // 将缩放类型设置为“缩放图片使其充满视图，并位于视图中间”
            mImageViewScaleType.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if (v.getId() == R.id.button_centerInside) {
            // 将缩放类型设置为“保持宽高比例，缩小图片使之位于视图中间（只缩小不放大）”
            mImageViewScaleType.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }
}