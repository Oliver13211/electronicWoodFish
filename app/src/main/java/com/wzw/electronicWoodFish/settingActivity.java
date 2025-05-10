package com.wzw.electronicWoodFish;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class settingActivity extends AppCompatActivity {
    int time;
    EditText waitTime;
    Button submit;
    @Override
    protected void onResume (){
        super.onResume();
        if (time!=0){
            startActivity(new Intent(this,MainActivity.class).putExtra("time",time));
            Log.d("test2", String.valueOf(time));
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_setting_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        waitTime = findViewById(R.id.waitTime);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(v -> {
            if (waitTime.getText().length()==0 | waitTime.getText().toString().equals("0")){
                Toast.makeText(settingActivity.this,"间隔时长不能为空或为0",Toast.LENGTH_SHORT).show();
            }
            else{
                time = Integer.parseInt(waitTime.getText().toString());
                Toast.makeText(settingActivity.this,"间隔时长设置成功，为"+time,Toast.LENGTH_SHORT).show();
            }
        });
    }
}