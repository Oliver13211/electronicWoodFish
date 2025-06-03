package com.wzw.electronicWoodFish;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
public class aboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.sendEmailToMe).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://1.94.48.181/wp-content/uploads/2025/05/qrcode_for_gh_fdf4f0431103_258.jpg")).setClassName("com.android.browser","com.android.browser.BrowserActivity")));
        findViewById(R.id.openMyWebSite).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("1.94.48.181")).setClassName("com.android.browser","com.android.browser.BrowserActivity")));
    }
}