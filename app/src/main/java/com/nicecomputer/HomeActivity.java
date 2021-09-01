package com.nicecomputer;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nicecomputer.Data_Manage.GetDataFromStorage;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<AdapterModel> models;
    SharedPreferences sharedPreferences;
    Recyclerview recyclerviewAdapter;
String TAG = "hello";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String gsonString = intent.getStringExtra("list");
        recyclerView = findViewById(R.id.home_recycler);
        Log.d("hello", "log Working");
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        models = new GetDataFromStorage().readData(models, sharedPreferences);
        recyclerviewAdapter = new Recyclerview(models, HomeActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerviewAdapter);

        Log.d(TAG, "onCreate: " + "Sms sent");

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_icon) {
            startActivity(new Intent(HomeActivity.this, AddStudent_Activity.class));

            // Do something
            return true;
        }
        if (id == R.id.dot_icon) {
            startActivity(new Intent(HomeActivity.this, Setting_Activity.class));
            // Do something
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        models = new GetDataFromStorage().readData(models, sharedPreferences);
        recyclerviewAdapter = new Recyclerview(models, HomeActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerviewAdapter);

    }
}