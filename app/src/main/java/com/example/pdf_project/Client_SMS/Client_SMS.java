package com.example.pdf_project.Client_SMS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pdf_project.Give_License;
import com.example.pdf_project.R;

public class Client_SMS extends AppCompatActivity {
    EditText Password_Log,Password_Log1,Password_Log3,Password_Log4;
    Button tmio_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__s_m_s);
        Password_Log=findViewById(R.id.Password_Log);
        Password_Log1=findViewById(R.id.Password_Log1);
        Password_Log3=findViewById(R.id.Password_Log3);
        Password_Log4=findViewById(R.id.Password_Log4);
        tmio_1=findViewById(R.id.tmio_1);
        tmio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,address,phobe,email;
                name=Password_Log.getText().toString().toLowerCase().trim();
                address=Password_Log1.getText().toString().toLowerCase().trim();
                phobe=Password_Log3.getText().toString().toLowerCase().trim();
                email=Password_Log4.getText().toString().toLowerCase().trim();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(address)
                        ||TextUtils.isEmpty(phobe)||TextUtils.isEmpty(email)) {
                    Toast.makeText(Client_SMS.this, "Fill up all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    textSend_user();

                }

            }
        });
    }
    private void textSend_user() {
        int permission= ContextCompat.checkSelfPermission(Client_SMS.this, Manifest.permission.SEND_SMS);
        if (permission== PackageManager.PERMISSION_GRANTED) {
            sending();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }
    private void sending() {
        String phone_number1233="01754201532";
        String sm333s="New Order Arrive!!!"+"\nName : "+Password_Log.getText().toString().trim()+
                "\nAddress : "+Password_Log1.getText().toString()+"\nPhonenumber : "+Password_Log3.getText().toString()+"\nEmail Address : "+Password_Log4.getText().toString();
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phone_number1233,null,sm333s,null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Give_License.class));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 0:
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    sending();;
                }
                else {
                    Toast.makeText(this, "Don't  Have permission", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}