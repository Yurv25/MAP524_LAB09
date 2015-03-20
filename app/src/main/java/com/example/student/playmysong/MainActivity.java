package com.example.student.playmysong;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    final String fold = "music";
    final String songNm = "song.mp3";
    final int songID = R.raw.song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private void copy2sdcard(String songName, String folder) throws IOException {

        String path = Environment.getExternalStorageDirectory() + "/" + folder;
        File dir = new File(path);
        if (new File(path + "/" + songName).exists())
            return;

        if (dir.mkdirs() || dir.isDirectory()) {
            path = path + "/" + songName;
        }
        InputStream in = getResources().openRawResource(songID);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }


    public void playSong(View view){

        try {
            copy2sdcard(songNm, fold);
        } catch (IOException e) {

        }
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("songNm", songNm);
        intent.putExtra("fold", fold);
        startService(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
