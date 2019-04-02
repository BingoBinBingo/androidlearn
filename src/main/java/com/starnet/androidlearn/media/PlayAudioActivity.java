package com.starnet.androidlearn.media;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.util.Log;

import java.io.FileDescriptor;

public class PlayAudioActivity extends Activity implements View.OnClickListener{
    private static final String TAG = PlayAudioActivity.class.getSimpleName();
    //1.创建MediaPlay
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String mFileName = "hdmi_output_test_audio.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_audio_activity_layout);
        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(PlayAudioActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PlayAudioActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            initMediaPlayer(); // 初始化MediaPlayer
        }
    }

    private void initMediaPlayer() {
        try {

            AssetFileDescriptor maAssetFileDescriptor =
                    PlayAudioActivity.this.getAssets().openFd(mFileName);
            FileDescriptor fd = maAssetFileDescriptor.getFileDescriptor();
            //2. 指定音频文件的路径
            mediaPlayer.setDataSource(fd, maAssetFileDescriptor.getStartOffset(),
                    maAssetFileDescriptor.getLength());
            //3. 让MediaPlayer进入到准备状态
            mediaPlayer.prepare();
        } catch (Exception e) {
            Log.e(TAG, " initMediaPlayer error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    //4.1 开始播放
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    //4.2 暂停播放
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    //4.3 停止播放
                    mediaPlayer.stop();
                    //4.4 重置播放状态
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            //5.释放MediaPlay占用的内存资源
            mediaPlayer.release();
        }
    }

}
