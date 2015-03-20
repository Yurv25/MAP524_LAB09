package com.example.student.playmysong;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

/**
 * Created by student on 3/20/15.
 */
public class MyService extends IntentService {

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String songName = intent.getStringExtra("songNm");
        final String folder = intent.getStringExtra("fold");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    MediaPlayer media = new MediaPlayer();
                    Uri uri = Uri.parse("file:///sdcard/" + folder + "/" + songName);
                    media.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    media.setDataSource(getApplicationContext(), uri);
                    media.prepare();
                    media.start();
                } catch (Exception e) {}
            }
        };
        new Thread(r).start();
    }
}
