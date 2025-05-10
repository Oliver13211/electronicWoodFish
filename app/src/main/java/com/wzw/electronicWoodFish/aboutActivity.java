package com.wzw.electronicWoodFish;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;
public class aboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.sendEmailToMe).setOnClickListener(v -> {Toast.makeText(this, "我的邮箱：2995306790@qq.com", Toast.LENGTH_LONG).show();try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Log.d("error",e.toString());
        }startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=wfP4_PTy8ff2_PGBsLDvoq6s")).setClassName("com.android.browser","com.android.browser.BrowserActivity"));});
        findViewById(R.id.openMyWebSite).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("1.94.48.181")).setClassName("com.android.browser","com.android.browser.BrowserActivity")));
    }
}