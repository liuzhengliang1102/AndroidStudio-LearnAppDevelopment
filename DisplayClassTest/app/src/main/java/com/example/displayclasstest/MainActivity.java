package com.example.displayclasstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "lzl-test";
    private TextView mTextView;
    private StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        Log.i(TAG, "getWidth():" + display.getWidth());
        Log.i(TAG, "getHeight():" + display.getHeight());
        mStringBuilder.append("getWidth():" + display.getWidth() + "\n");
        mStringBuilder.append("getHeight():" + display.getHeight() + "\n");

        Point outSmallestSize = new Point();
        Point outLargestSize = new Point();
        display.getCurrentSizeRange(outSmallestSize, outLargestSize);
        Log.i(TAG, "getCurrentSizeRange():outSmallestSize:" + outSmallestSize.toString());
        Log.i(TAG, "getCurrentSizeRange():outLargestSize:" + outLargestSize.toString());
        mStringBuilder.append("getCurrentSizeRange():outSmallestSize:" + outSmallestSize.toString() + "\n");
        mStringBuilder.append("getCurrentSizeRange():outLargestSize:" + outLargestSize.toString() + "\n");

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        display.getRealMetrics(realDisplayMetrics);
        Log.i(TAG, "getRealMetrics():" + realDisplayMetrics.toString());
        double screenSize = Math.sqrt(Math.pow(realDisplayMetrics.widthPixels/realDisplayMetrics.xdpi,2) + Math.pow(realDisplayMetrics.heightPixels/realDisplayMetrics.ydpi,2));
        Log.i(TAG, "screenSize:" + screenSize);
        mStringBuilder.append("getRealMetrics():" + realDisplayMetrics.toString() + "\n");
        mStringBuilder.append("screenSize:" + screenSize + "\n");


        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        Log.i(TAG, "getMetrics():" + displayMetrics.toString());
        mStringBuilder.append("getMetrics():" + displayMetrics.toString() + "\n");

        Point outSize = new Point();
        display.getRealSize(outSize);
        Log.i(TAG, "getRealSize():" + outSize.toString());
        mStringBuilder.append("getRealSize():" + outSize.toString() + "\n");

        display.getSize(outSize);
        Log.i(TAG, "getSize():" + outSize.toString());
        mStringBuilder.append("getSize():" + outSize.toString() + "\n");

        Rect outRect = new Rect();
        display.getRectSize(outRect);
        Log.i(TAG, "getRectSize():" + outRect.toString());
        mStringBuilder.append("getRectSize():" + outRect.toString() + "\n");

        Display.Mode activeMode = display.getMode();
        Log.i(TAG, "getMode():" + activeMode.toString());
        mStringBuilder.append("getMode():" + activeMode.toString() + "\n");

        Display.Mode[] modes = display.getSupportedModes();
        for (int i = 0; i < modes.length; i++) {
            Log.i(TAG, "getSupportedModes():Mode[" + i + "]:"+ modes[i].toString());
            mStringBuilder.append("getSupportedModes():Mode[" + i + "]:"+ modes[i].toString() + "\n");
        }

        Log.i(TAG, "getDisplayId:" + display.getDisplayId());
        Log.i(TAG, "getFlags:" + display.getFlags());

        Display.HdrCapabilities hdrCapabilities = display.getHdrCapabilities();
        Log.i(TAG, "getHdrCapabilities:" + hdrCapabilities.toString());

        Log.i(TAG, "isValid:" + display.isValid());
        Log.i(TAG, "isHdr:" + display.isHdr());
        Log.i(TAG, "isMinimalPostProcessingSupported:" + display.isMinimalPostProcessingSupported());
        Log.i(TAG, "isWideColorGamut:" + display.isWideColorGamut());

        Log.i(TAG, "getName:" + display.getName());

        Log.i(TAG, "getOrientation:" + display.getOrientation());
        Log.i(TAG, "getRotation:" + display.getRotation());
        Log.i(TAG, "getPixelFormat:" + display.getPixelFormat());
        Log.i(TAG, "getPreferredWideGamutColorSpace:" + display.getPreferredWideGamutColorSpace().toString());
        Log.i(TAG, "getPresentationDeadlineNanos:" + display.getPresentationDeadlineNanos());

        Log.i(TAG, "getRefreshRate:" + display.getRefreshRate());

        Log.i(TAG, "getState:" + display.getState());


        float [] refreshRates = display.getSupportedRefreshRates();
        for (int i = 0; i < refreshRates.length; i++) {
            Log.i(TAG, "refreshRates[" + i + "]:"+ refreshRates[i]);
        }

        mStringBuilder.append("\n\n\n");

        Log.i(TAG, "getResources().getDisplayMetrics():" + getResources().getDisplayMetrics().toString());
        Log.i(TAG, " getResources().getDisplayMetrics():widthPixels:"
                + displayMetrics.widthPixels + ", heightPixels:" + displayMetrics.heightPixels);
        mStringBuilder.append("getResources().getDisplayMetrics():" + getResources().getDisplayMetrics().toString() + "\n");

        mStringBuilder.append("\n\n\n");

        WindowMetrics windowMetrics = windowManager.getCurrentWindowMetrics();
        Rect rect = windowMetrics.getBounds();
        Log.i(TAG, "getCurrentWindowMetrics():"+ rect.toString());
        Log.i(TAG, "getCurrentWindowMetrics(): width:"+ rect.width() + ", height:" + rect.height());
        mStringBuilder.append("getCurrentWindowMetrics():"+ rect.toString() + "\n");
        mStringBuilder.append("getCurrentWindowMetrics(): width:"+ rect.width() + ", height:" + rect.height() + "\n");

        mTextView.setText(mStringBuilder.toString());
    }
}