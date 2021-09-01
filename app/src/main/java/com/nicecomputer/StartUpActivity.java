package com.nicecomputer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

public class StartUpActivity extends AppCompatActivity {
private boolean state = false;
String TAG = "hello";

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    state = false;
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);

    checkPermission();
    hello hello = new hello();
    hello.execute();
     }
  private boolean checkPermission(){
    if (ContextCompat.checkSelfPermission(StartUpActivity.this , Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
          ){

    }
    else{

      ActivityCompat.requestPermissions( StartUpActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
    }
    if (  ContextCompat.checkSelfPermission(StartUpActivity.this , Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
          ){

    }
    else{

      ActivityCompat.requestPermissions( StartUpActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
    }
    return false;
  }

  public class hello extends AsyncTask<Void,Void,Void>{

    @Override
    protected Void doInBackground( Void... voids ) {
      Typewriter writer = (Typewriter) findViewById(R.id.Starting_TvMessage);
      writer.setCharacterDelay(100);
      writer.animateText("Loading Student Data...");
      try {
        Thread.sleep(3000);
        state = true;
        startActivity(new Intent(StartUpActivity.this, HomeActivity.class));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return null;
    }
  }


  @Override
  protected void onResume() {
    super.onResume();
    if (state) onBackPressed();;
  }


}
