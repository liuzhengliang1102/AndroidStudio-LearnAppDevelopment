package com.example.widecolorgamuttest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;

import java.io.IOException;

public class Utils {
    public static ColorSpace getImageColorSpace(Resources res, int resId) {
        Bitmap bitmap = null;
        ImageDecoder.Source source = ImageDecoder.createSource(res, resId);
        try {
            bitmap = ImageDecoder.decodeBitmap(source);
            return bitmap.getColorSpace();
        } catch (IOException e) {
            // handle exception.
        }
        if (bitmap != null)
            return bitmap.getColorSpace();
        else
            return null;
    }
}
