package com.fuicuiedu.demo.video_demo.DemoA;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;

import com.fuicuiedu.demo.video_demo.R;
import com.fuicuiedu.demo.video_demo.VideoUrlRes;

import java.io.IOException;

public class DemoAActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;//用于控制视频播放
    private SurfaceView surfaceView;//用于视频展示
    private SurfaceHolder surfaceHolder;//Surface持有者

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_a);

        //找到SurfaceView控件
        surfaceView = (SurfaceView) findViewById(R.id.demo_a_sfv);
        //拿到用于访问Surface的接口SurfaceHolder（Surface持有者）
        surfaceHolder = surfaceView.getHolder();
        //通过回调，拿到Surface的状态，并作出相应的处理。
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //Surface创建成功是触发
                try {
                    mediaPlayer = new MediaPlayer();//获取MediaPlayer实例
                    mediaPlayer.setDisplay(surfaceHolder);//绑定surfaceHolder
                    mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());//设置数据源
                    mediaPlayer.prepareAsync();//准备播放(异步准备)
                    //监听是否准备好
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            //准备好后，播放视频
                            mediaPlayer.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                //Surface大小发生改变时触发
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                //Surface销毁时触发
                mediaPlayer.stop();//停止播放
                mediaPlayer.release();//释放相关资源
            }
        });

    }
}
