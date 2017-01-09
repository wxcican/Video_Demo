package com.fuicuiedu.demo.video_demo.DemoD;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fuicuiedu.demo.video_demo.R;
import com.fuicuiedu.demo.video_demo.VideoUrlRes;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_d);

        //找到VideoView（Vitamio包）
        VideoView videoView = (VideoView) findViewById(R.id.demo_d_vv);
        //设置数据源
        videoView.setVideoPath(VideoUrlRes.getTestVideo1());
        //开始播放
        videoView.start();

        //实例化控制器（Vitamio包）
        MediaController mediaController = new MediaController(this);
        //添加控制器
        videoView.setMediaController(mediaController);
    }
}
