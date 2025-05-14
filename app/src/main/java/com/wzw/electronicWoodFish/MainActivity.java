package com.wzw.electronicWoodFish;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {// 取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
    private static final String TAG = "TestSensorActivity";
    private static final int SENSOR_SHAKE = 10;
    private SensorManager sensorManager;
    Vibrator vibrator;
    TextView autoStyle;
    ImageView woodFish;
    TextView num;
    /** @noinspection deprecation*/
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == SENSOR_SHAKE) {
                woodFish.performClick();
                Log.i(TAG, "检测到摇晃，执行操作！");
            }
        }
    };
    private Runnable clickRunnable;
    private boolean isClicking = false;
    int delaytime;
    int count = 0;
    @Override
    public void onResume(){
        Log.d("test", String.valueOf(getIntent()));
        if (delaytime==0){
            delaytime = getIntent().getIntExtra("time",0);
        }
        Log.d("test", String.valueOf(delaytime));
        if (sensorManager != null) {// 注册监听器
            sensorManager.registerListener(this.sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);}
            super.onResume();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator)this.getSystemService(VIBRATOR_SERVICE);
        MediaPlayer mMediaPlayer = MediaPlayer.create(this, R.raw.sound);
        super.onCreate(savedInstanceState);
        clickRunnable = new Runnable() {
            @Override
            public void run() {
                if (isClicking) {
                    woodFish.performClick();
                    handler.postDelayed(this, delaytime);
                }
            }
        };
        setContentView(R.layout.activity_main);
        autoStyle = findViewById(R.id.autoText);
        woodFish = findViewById(R.id.woodFish);
        num = findViewById(R.id.num);
        woodFish.setOnClickListener(v -> {Toast.makeText(this,"功德+1",Toast.LENGTH_SHORT).show();count++;num.setText("当前功德："+count);mMediaPlayer.start();vibrator.vibrate(100);});
    }
    private final SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            Log.i(TAG, "x轴方向的重力加速度" + x +  "；y轴方向的重力加速度" + y +  "；z轴方向的重力加速度" + z);
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            int medumValue = 20;// 如果不敏感请自行调低该数值,低于10的话就不行了,因为z轴上的加速度本身就已经达到10了
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.autoStart){
            isClicking = true;
            autoStyle.setText("自动点击：开启");
            handler.post(clickRunnable);
        } else if (item.getItemId()==R.id.autoStop) {
            isClicking = false;
            autoStyle.setText("自动点击：关闭");
            handler.removeCallbacks(clickRunnable);
        } else if (item.getItemId()==R.id.setting) {
            startActivity(new Intent(MainActivity.this, settingActivity.class));
        } else if (item.getItemId()==R.id.about) {
            startActivity(new Intent(MainActivity.this,aboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}