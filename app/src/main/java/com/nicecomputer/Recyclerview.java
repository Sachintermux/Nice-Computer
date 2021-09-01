package com.nicecomputer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Recyclerview extends androidx.recyclerview.widget.RecyclerView.Adapter<Recyclerview.viewHolder>{
String TAG = "hello";
    ArrayList<AdapterModel> list;
    Context context;
    TextView tv_name,tv_course,tv_number,tv_address,tv_month,tv_fees;
    Button btn_cancel,btn_send;
View.OnClickListener je;
    public Recyclerview( ArrayList<AdapterModel> list, Context context ) {
        this.list = list;
        this.context = context;
    }
public void setje(int position){

}


    @NonNull
    @Override
    public viewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout ,parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder( @NonNull viewHolder holder, @SuppressLint("RecyclerView") int position ) {
AdapterModel model = list.get(position);
holder.Sserial_No.setText(String.valueOf(position+1));
holder.Sname.setText(model.getmName().toString());
holder.Scourse.setText(model.getmCourse().toString());
holder.Samount.setText(String.valueOf(model.getmFees().toString()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Log.d(TAG, "onClick: "+ position);
                showDialog(position,model);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{
    TextView Sserial_No,Sname,Scourse,Samount;
    public viewHolder( @NonNull View itemView ) {
        super(itemView);

        Sserial_No = itemView.findViewById(R.id.tv_no);
        Sname = itemView.findViewById(R.id.tv_Name);
        Scourse = itemView.findViewById(R.id.tv_course);
        Samount = itemView.findViewById(R.id.tv_Amount);

    }
}
public void showDialog(int position , AdapterModel model){
    Dialog dialog = new Dialog(context);
    dialog.setContentView(R.layout.dialog_layout);
  tv_name = dialog.findViewById(R.id.dio_name);
  tv_course = dialog.findViewById(R.id.dio_course);
  tv_address = dialog.findViewById(R.id.dio_address);
  tv_month = dialog.findViewById(R.id.dio_month);
  tv_number = dialog.findViewById(R.id.dio_number);
  tv_fees = dialog.findViewById(R.id.dio_fees);
  btn_cancel = dialog.findViewById(R.id.dio_cancel);
  btn_send = dialog.findViewById(R.id.dio_send);

  tv_name.setText(model.getmName());
  tv_course.setText(model.getmCourse());
  tv_address.setText(model.getmAddress());
  tv_number.setText(model.getmNumber());
  tv_fees.setText(model.getmFees());
    dialog.show();
    btn_cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick( View v ) {
            dialog.dismiss();
        }
    });

    btn_send.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick( View v ) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("message" , Context.MODE_PRIVATE);
           String message = sharedPreferences.getString("message1" , "");
            Log.d(TAG, "onClick: " + message);

//            StringBuilder s = new StringBuilder();
          message =       message.replace("NAME" , String.valueOf(tv_name.getText()));
            message =     message.replaceAll("ADDRESS" , tv_address.getText().toString());
            message =     message.replaceAll("FEES" , tv_fees.getText().toString());
            message =     message.replaceAll("COURSE" , tv_course.getText().toString());
            message =     message.replaceAll("NUMBER" , tv_number.getText().toString());
            message =     message.replaceAll("MONTH" , tv_month.getText().toString());

//            SendSms(tv_number.getText().toString() , "From : " + "Nice Computer" + "\n" +
//                    "Name : " +tv_name.getText().toString() + "\n" + "Address : " + tv_address.getText().toString()
//            + "\n" + "Course : " + tv_course.getText().toString() + "\n" + "Fees : " + tv_fees.getText().toString() + "\n" +
//                 "Month : " + tv_month.getText().toString()   + "\n" + "This is Sent by Nice Computer Regarding to you last Month Coaching Fees Please clear your dues" +
//                    "\n" + "By Durgesh Sir");

            SendSms(tv_number.getText().toString() , message);
            Log.d(TAG, "onClick: " + message);
            dialog.dismiss();
        }
    });


}

private void SendSms(String number, String message){
Log.d("hello" , message);

  SmsManager smsManager = SmsManager.getDefault();

    ArrayList<String> parts = smsManager.divideMessage(message);
  smsManager.sendMultipartTextMessage(number , null, parts , null,null);
    Toast.makeText(context, "Sms sent successfully", Toast.LENGTH_SHORT).show();


}


}
