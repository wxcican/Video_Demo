package com.fuicuiedu.demo.video_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fuicuiedu.demo.video_demo.DemoA.DemoAActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mlv;
    private String[] datas;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlv = (ListView) findViewById(R.id.main_lv);

        datas = new String[]{
                "Android MediaPlayer + SurfaceView/TextureView",
                "Vitamio MediaPlayer + SurfaceView/TextureView",
                "Android VideoView + MediaController",
                "Vitamio VideoView + MediaController",
                "VideoView Buffer 缓冲处理"
        };

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        mlv.setAdapter(adapter);

        mlv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position){
            case 0:
                intent = new Intent(MainActivity.this, DemoAActivity.class);
                startActivity(intent);
                break;
            case 1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
