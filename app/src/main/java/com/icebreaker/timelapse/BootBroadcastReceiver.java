package com.icebreaker.timelapse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent bootIntent = new Intent(context, AppService.class);
        bootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(bootIntent);

        Intent bootIntent2 = new Intent(context, LocationService.class);
        bootIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(bootIntent2);
    }
}
