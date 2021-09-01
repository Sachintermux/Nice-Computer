package com.nicecomputer;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.nicecomputer.Data_Manage.GetDataFromStorage;

import java.util.ArrayList;

public class AddStudent_Activity extends AppCompatActivity {
    EditText edt_name, edt_address, edt_course, edt_number, edt_fees;
    Button btn_submit, btn_back;
    ArrayList<AdapterModel> adapterModels;

    private static final String TAG = "hello";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        edt_name = findViewById(R.id.edt_Name);
        edt_address = findViewById(R.id.edt_address);
        edt_course = findViewById(R.id.edt_course);
        edt_number = findViewById(R.id.edt_number);
        edt_fees = findViewById(R.id.edt_fees);
        btn_submit = findViewById(R.id.btn_submit);
        btn_back = findViewById(R.id.btn_back);
        adapterModels = new GetDataFromStorage().readData(adapterModels, sharedPreferences);
        Log.d(TAG, "onCreate: " + adapterModels.size());
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick( View v ) {
                if (isEmpty(edt_name) && isEmpty(edt_fees) && isEmpty(edt_number) && isEmpty(edt_course)) {
                    adapterModels.add(new AdapterModel(edt_name.getText().toString(), edt_course.getText().toString(),
                            edt_address.getText().toString(), edt_number.getText().toString(), edt_fees.getText().toString()));
                    new GetDataFromStorage().saveData(adapterModels, sharedPreferences);
                    edt_address.setText("");
                    edt_name.setText("");
                    edt_course.setText("");
                    edt_fees.setText("");
                    edt_number.setText("");
                    Toast.makeText(AddStudent_Activity.this, "student Added SuccessesFully", Toast.LENGTH_SHORT).show();
                    edt_name.setHintTextColor(edt_address.getCurrentHintTextColor());
                    edt_course.setHintTextColor(edt_address.getCurrentHintTextColor());
                    edt_number.setHintTextColor(edt_address.getCurrentHintTextColor());
                    edt_fees.setHintTextColor(edt_address.getCurrentHintTextColor());

                } else {
                    if (!(isEmpty(edt_name))) edt_name.setHintTextColor(getColor(R.color.red));
                    if (!(isEmpty(edt_course))) edt_course.setHintTextColor(getColor(R.color.red));
                    if (!(isEmpty(edt_number))) edt_number.setHintTextColor(getColor(R.color.red));
                    if (!(isEmpty(edt_fees))) edt_fees.setHintTextColor(getColor(R.color.red));
                    Toast.makeText(AddStudent_Activity.this, "Please Fill All Boxes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                onBackPressed();
            }
        });

    }

    private boolean isEmpty( EditText myeditText ) {
        boolean ans = myeditText.getText().toString().matches("");
        if (ans) ans = false;
        else ans = true;
        Log.d("hello", "Ans  + =" + ans + "    " + myeditText.getText());
        return ans;
    }

}
