package com.example.zuitestdemo.compat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import com.example.zuitestdemo.R;

/**
 * Created by gonglt1 on 17-9-29.
 */

public class NotificationCompatHelper {
    private static final String TAG = "NotificationCompatHelper";
    private final static String DEFAULT_ID = "com_zui_deskclock_channel_id";

    public static NotificationCompatHelper mInstance;
    private NotificationChannel mChannel;
    private Context mContext;
    private NotificationManager mNotificationManager;
    public NotificationCompatHelper(Context context) {
        mContext = context.getApplicationContext();
        String channelName = mContext.getResources().getString(R.string.app_name);
        if (SystemUtils.isOOrLater()) {
            mChannel = new NotificationChannel(DEFAULT_ID,
                    channelName, NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setSound(null, null);
            if (getManager() != null) {
                getManager().createNotificationChannel(mChannel);
            }
        }
    }

    public static NotificationCompatHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NotificationCompatHelper(context);
        }
        return mInstance;
    }



    private Notification.Builder getBuilder(boolean isCustom) {
        Notification.Builder builder;
        if (SystemUtils.isOOrLater()) {
            builder = new Notification.Builder(mContext, DEFAULT_ID);
            if (isCustom) {
                builder.setStyle(new Notification.DecoratedCustomViewStyle());
            }
            builder.setColor(mContext.getResources().getColor(R.color.teal_700));
        } else {
            builder = new Notification.Builder(mContext);
        }
        return builder;
    }

    public Notification.Builder getBuilder() {
        return getBuilder(false);
    }

    public Notification.Builder getCustomBuilder() {
        return getBuilder(true);
    }

    private NotificationManager getManager() {
        if (mNotificationManager == null && mContext != null) {
            mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }
}
