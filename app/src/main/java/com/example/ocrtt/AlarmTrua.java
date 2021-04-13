package com.example.ocrtt;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import me.pushy.sdk.Pushy;

public class AlarmTrua extends BroadcastReceiver {
    public AlarmTrua(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String a = intent.getStringExtra("TIME_SANG");
        String b = intent.getStringExtra("TIME_TRUA");
        String c = intent.getStringExtra("TIME_CHIEU");
        String d = intent.getStringExtra("TIME_TOI");
        String ma = intent.getStringExtra("DON_THUOC");
        String ngaybd = intent.getStringExtra("THOI_GIAN_BD");
        String ngaykt = intent.getStringExtra("THOI_GIAN_KT");
        Intent action = new Intent(context,AlarmActivity.class);
        action.putExtra("CB_NOIDUNG","trua");
        action.putExtra("CB_TIME_SANG",a);
        action.putExtra("CB_TIME_TRUA",b);
        action.putExtra("CB_TIME_CHIEU",c);
        action.putExtra("CB_TIME_TOI",d);
        action.putExtra("CB_THOI_GIAN_BD",ngaybd);
        action.putExtra("CB_THOI_GIAN_KT",ngaykt);
        action.putExtra("CB_MATHUOC",ma);
        String notificationTitle = "OCRTT - Nhắc nhở sử dụng thuốc";
        String notificationText = "Uống thuốc buổi trưa";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setLights(Color.RED, 1000, 1000)
                .setVibrate(new long[]{0, 400, 250, 400})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(PendingIntent.getActivity(context, 0, action, PendingIntent.FLAG_UPDATE_CURRENT));

        Pushy.setNotificationChannel(builder, context);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, builder.build());
    }
}
