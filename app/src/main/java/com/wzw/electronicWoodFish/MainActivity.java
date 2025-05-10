package com.wzw.electronicWoodFish;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
    Vibrator vibrator;
    ImageView woodFish;
    TextView num;
    private final Handler handler = new Handler();
    private Runnable clickRunnable;
    private boolean isClicking = false;
    int delaytime;
    int count = 0;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        vibrator = (Vibrator)this.getSystemService(VIBRATOR_SERVICE);
        MediaPlayer mMediaPlayer = MediaPlayer.create(this, R.raw.sound);
        super.onCreate(savedInstanceState);
        clickRunnable = new Runnable() {
            @Override
            public void run() {
                if (isClicking) {
                    woodFish.performClick();
                    Log.d("test", String.valueOf(getIntent()));
                    if (delaytime==0){
                        delaytime = getIntent().getIntExtra("time",0);
                    }
                    Log.d("test", String.valueOf(delaytime));
//                    Toast.makeText(MainActivity.this,"功德+1",Toast.LENGTH_SHORT).show();count++;num.setText("当前功德："+count);mMediaPlayer.start();vibrator.vibrate(100);
                    handler.postDelayed(this, delaytime);
                }
            }
        };
        setContentView(R.layout.activity_main);
        woodFish = findViewById(R.id.woodFish);
        num = findViewById(R.id.num);
        woodFish.setOnClickListener(v -> {Toast.makeText(this,"功德+1",Toast.LENGTH_SHORT).show();count++;num.setText("当前功德："+count);mMediaPlayer.start();vibrator.vibrate(100);});
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.autoStart){
            isClicking = true;
            handler.post(clickRunnable);
        } else if (item.getItemId()==R.id.autoStop) {
            isClicking = false;
            handler.removeCallbacks(clickRunnable);
        } else if (item.getItemId()==R.id.setting) {
            startActivity(new Intent(MainActivity.this, settingActivity.class));
        } else if (item.getItemId()==R.id.about) {

        }
        return super.onOptionsItemSelected(item);
    }
}