package com.nicecomputer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Setting_Activity extends AppCompatActivity {
EditText edt_message;
ImageView message_hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        edt_message = findViewById(R.id.edt_message);
        message_hint = findViewById(R.id.message_hint);
        SharedPreferences sharedPreferences = getSharedPreferences("message" , MODE_PRIVATE);
        String message = sharedPreferences.getString("message1" , "");
        edt_message.setText(message);
    }

    public void saveMessage( View view ) {
        SharedPreferences sharedPreferences = getSharedPreferences("message" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("message1" , edt_message.getText().toString());
        editor.apply();
        Toast.makeText(Setting_Activity.this, "Message Save", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("message" , MODE_PRIVATE);
        String message = sharedPreferences.getString("message1" , "");
        edt_message.setText(message);
    }

    public void message_hint( View view ) {

    }
}