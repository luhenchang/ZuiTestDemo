package com.example.zuitestdemo.compat;
import android.os.Build;


/**
 * Created by gonglt1 on 17-9-29.
 */

public class SystemUtils {
    private static final String TAG = "SystemUtils";

    public static boolean isQOrLater() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static boolean isOOrLater() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }
}
