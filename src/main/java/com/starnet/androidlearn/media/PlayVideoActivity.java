package com.starnet.androidlearn.media;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.util.Log;

import java.io.FileDescriptor;

public class PlayVideoActivity extends Activity implements View.OnClickListener{
    private static final String TAG = PlayVideoActivity.class.getSimpleName();
    //1.创建MediaPlay
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private String mFileName = "fortest.mp4";
    private SurfaceView mSurfaceView;
    private boolean mIsFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video_activity_layout);
        mSurfaceView = (SurfaceView)findViewById(R.id.videoSurface);
        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(PlayVideoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PlayVideoActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            initMediaPlayer(); // 初始化MediaPlayer
        }
    }

    private void initMediaPlayer() {
        try {
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Toast.makeText(PlayVideoActivity.this, "MediaPlay初始化完毕",
                            Toast.LENGTH_SHORT).show();
                }
            });
            AssetFileDescriptor maAssetFileDescriptor =
                    PlayVideoActivity.this.getAssets().openFd(mFileName);
            FileDescriptor fd = maAssetFileDescriptor.getFileDescriptor();
            //2. 指定视频文件的路径
            mMediaPlayer.setDataSource(fd, maAssetFileDescriptor.getStartOffset(),
                    maAssetFileDescriptor.getLength());

            if(!mIsFirst) {
                mMediaPlayer.setDisplay(mSurfaceView.getHolder());
                mMediaPlayer.prepareAsync();
            } else {
                //3. 让MediaPlayer进入到准备状态
                mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        mIsFirst = false;
                        mMediaPlayer.setDisplay(holder);
                        mMediaPlayer.prepareAsync();
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {

                    }
                });
            }



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
                if (!mMediaPlayer.isPlaying()) {
                    //4.1 开始播放
                    mMediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mMediaPlayer.isPlaying()) {
                    //4.2 暂停播放
                    mMediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if (mMediaPlayer.isPlaying()) {
                    //4.3 停止播放
                    mMediaPlayer.stop();
                    //4.4 重置播放状态
                    mMediaPlayer.reset();
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
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            //5.释放MediaPlay占用的内存资源
            mMediaPlayer.release();
        }
    }

}
