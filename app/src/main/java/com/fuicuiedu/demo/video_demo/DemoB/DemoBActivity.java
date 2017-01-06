package com.fuicuiedu.demo.video_demo.DemoB;

import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import com.fuicuiedu.demo.video_demo.R;
import com.fuicuiedu.demo.video_demo.VideoUrlRes;

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;

public class DemoBActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    private TextureView textureView;
    private Surface mySurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_b);

        textureView = (TextureView) findViewById(R.id.demo_b_ttv);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                try {
                    mediaPlayer = new MediaPlayer(DemoBActivity.this);
                    mySurface = new Surface(surface);
                    mediaPlayer.setSurface(mySurface);
                    mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });

                    // vitamio5.0，要进行audio处理,才能对在线视频进行播放!!!!
                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            if (what == MediaPlayer.MEDIA_INFO_FILE_OPEN_OK) {
                                mediaPlayer.audioInitedOk(mediaPlayer.audioTrackInit());
                                return true;
                            }
                            return false;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                mySurface.release();
                mySurface = null;
                mediaPlayer.stop();
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });


//        surfaceView = (SurfaceView) findViewById(R.id.demo_b_sfv);
//        surfaceHolder = surfaceView.getHolder();
//
//        //使用SurfaceView时，要对PixelFormat（像素格式做处理），否则会花屏
//        surfaceHolder.setFormat(PixelFormat.RGBA_8888);
//
//        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                try {
//                    mediaPlayer = new MediaPlayer(DemoBActivity.this);
//                    mediaPlayer.setDisplay(surfaceHolder);//surfaceholder绑定
//                    mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());//设置数据源
//                    mediaPlayer.prepareAsync();//准备播放(异步准备)
//                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//监听准备状态
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
//
//                            mediaPlayer.start();//开始播放
//                        }
//                    });
//
//                    // vitamio5.0，要进行audio处理,才能对在线视频进行播放!!!!
//                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
//                        @Override public boolean onInfo(MediaPlayer mp, int what, int extra) {
//                            if (what == MediaPlayer.MEDIA_INFO_FILE_OPEN_OK) {
//                                mediaPlayer.audioInitedOk(mediaPlayer.audioTrackInit());
//                                return true;
//                            }
//                            return false;
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//            }
//        });
    }
}
