package com.fristtest.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;


public class MainActivity extends ActionBarActivity {
    TextView textView;
    ImageButton apple,banana;
    private SoundPool soundPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        textView = (TextView) findViewById(R.id.show);
        apple = (ImageButton) findViewById(R.id.apple);
//        banana = (ImageButton) findViewById(R.id.banana);

        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //对话框   Builder是AlertDialog的静态内部类
                Dialog dialog = new AlertDialog.Builder(MainActivity.this)
                        //设置对话框的标题
//                        .setTitle("小航提示")
                                //设置对话框要显示的消息
                        .setMessage("apple \n 苹果")
                                //给对话框来个按钮 叫“确定定” ，并且设置监听器 这种写法也真是有些BT
                        /*.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                //点击 "确定定" 按钮之后要执行的操作就写在这里

                            }
                        })*/.create();//创建按钮
                dialog.show();//显示一把
                soundPool= new SoundPool(2, AudioManager.STREAM_SYSTEM,5);
                soundPool.load(MainActivity.this, R.raw.collide, 1);
                soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){
                    @Override
                    public void onLoadComplete(SoundPool arg0, int arg1, int arg2) {
//                        soundPool.play(soundPoolMap.get(soundId), // 声音资源id
//                                volume, // 左声道音量
//                                volume, // 右声道音量
//                                1, // 优先级
//                                loop, // 循环次数 -1带表永远循环
//                                0.5f // 回放速度0.5f～2.0f之间
//                        );
                        soundPool.play(1, 0.1f, 0.1f, 0, 0, 1);
                    }});
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause(){
        super.onPause();
        soundPool.stop(1);
    }
}
