package com.example.activitylifetest;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    // 根据手机的分辨率从 dp 的单位 转成为 px(像素)
    public static int dp2px(Context context, float dp) {
        // 获取当前手机的像素密度（1个dp对应几个px）
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f); // 四舍五入取整
    }

    // 根据手机的分辨率从 px(像素) 的单位 转成为 dp
    public static int px2dp(Context context, float px) {
        // 获取当前手机的像素密度（1个dp对应几个px）
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f); // 四舍五入取整
    }

    // 获取当前的日期时间
    public static String getNowDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    // 获取当前的时间
    public static String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    // 获取当前的时间(毫秒ms)
    public static String getNowTimeMs() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        return simpleDateFormat.format(new Date());
    }
}
