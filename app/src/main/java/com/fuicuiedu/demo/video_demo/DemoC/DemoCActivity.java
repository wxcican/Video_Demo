package com.fuicuiedu.demo.video_demo.DemoC;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.fuicuiedu.demo.video_demo.R;
import com.fuicuiedu.demo.video_demo.VideoUrlRes;

public class DemoCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_c);

        //找到VideoView
        VideoView videoView = (VideoView) findViewById(R.id.demo_c_vv);
        //设置数据源
        videoView.setVideoPath(VideoUrlRes.getTestVideo1());
        //开始播放
        videoView.start();

        //实例化控制器
        MediaController mediaController = new MediaController(this);
        //添加控制器
        videoView.setMediaController(mediaController);
    }
}
