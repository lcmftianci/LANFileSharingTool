package com.example.shinelon.flowerviewpager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shinelon.flowerviewpager.MsgSendGet.MsgActivity;
import com.example.shinelon.flowerviewpager.PackFileManager.FileManager;

public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        startActivity(new Intent(SplashPage.this, MsgActivity.class));
        finish();
    }
}
