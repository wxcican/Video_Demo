package com.fuicuiedu.demo.video_demo.DemoE;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fuicuiedu.demo.video_demo.R;
import com.fuicuiedu.demo.video_demo.VideoUrlRes;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoEActivity extends AppCompatActivity {

    private VideoView videoView;
    private ProgressBar progressBar;//进度条
    private TextView downLoadRate;//加载速度
    private TextView loadRate;//加载百分比

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_e);

        videoView = (VideoView) findViewById(R.id.demo_e_vv);
        progressBar = (ProgressBar) findViewById(R.id.demo_e_progressBar);
        downLoadRate = (TextView) findViewById(R.id.demo_e_tvDownloadRate);
        loadRate = (TextView) findViewById(R.id.demo_e_tvLoadRate);

        //设置数据源
        videoView.setVideoPath(VideoUrlRes.getTestVideo2());
        //添加控制器
        videoView.setMediaController(new MediaController(this));

        //第一步 在OnPreparedListener监听器中调整缓冲大小
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //单位是B,1024B = 1Kb，1kb * 512 = 0.5M
                videoView.setBufferSize(1024 * 512);
            }
        });

        //第二步 在OnInfoListener监听器中，监控缓冲的开始、结束，以及视频下载速率的变化
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                //根据what值来判断
                switch (what) {
                    //缓冲开始
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        startBuffer();//开始缓冲
                        break;
                    //缓冲结束
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        endBuffer();//结束缓冲
                        break;
                    //下载速率改变
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        //改变下载速率显示，extra即为当前的下载速率
                        downLoadRate.setText(extra + "KB/S");
                        break;
                }
                return false;
            }
        });

        //第三步 通过OnBufferingUpdateListener监听器，获取当前缓冲进度的百分比
        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                //percent即为当前缓冲进度的百分比
                loadRate.setText(percent + "%");
            }
        });
    }

    //开始缓冲
    private void startBuffer() {
        //如果处于播放状态，暂停播放
        if (videoView.isPlaying()) videoView.pause();
        //显示进度条等Ui
        progressBar.setVisibility(View.VISIBLE);
        downLoadRate.setVisibility(View.VISIBLE);
        loadRate.setVisibility(View.VISIBLE);
        //初始化缓冲值
        downLoadRate.setText("");
        loadRate.setText("");
    }

    //结束缓冲
    private void endBuffer() {
        //开始播放
        videoView.start();
        //隐藏进度条等UI
        progressBar.setVisibility(View.INVISIBLE);
        downLoadRate.setVisibility(View.INVISIBLE);
        loadRate.setVisibility(View.INVISIBLE);
    }
}
