package com.example.student.playmysong;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by student on 3/20/15.
 */
public class MyReceiver extends BroadcastReceiver {

    final String song = "song.mp3";
    final String folder = "music";
    public MyReceiver() { }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent in = new Intent(context, MyService.class);
        in.putExtra("songNm", song);
        in.putExtra("fold", folder);
        context.startService(in);
        context.startActivity(in);
    }
}
