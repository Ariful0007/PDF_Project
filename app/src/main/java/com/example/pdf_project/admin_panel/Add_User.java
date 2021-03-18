package com.example.pdf_project.admin_panel;

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

import com.example.pdf_project.Client_SMS.Client_SMS;
import com.example.pdf_project.Give_License;
import com.example.pdf_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Add_User extends AppCompatActivity {
    EditText username_added,phone_added,email_add;
    TextView date_picker;
    Button addEd;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    FirebaseAuth firebaseAuth;
    String url;
    KProgressHUD progressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__user);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        username_added=findViewById(R.id.username_added);
        phone_added=findViewById(R.id.phone_added);
        email_add=findViewById(R.id.email_add);
        date_picker=findViewById(R.id.date_picker);
        addEd=findViewById(R.id.addEd);
        Calendar calendar=Calendar.getInstance();
        String current= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String current1= DateFormat.getDateInstance().format(calendar.getTime());
        date_picker.setText(current);
        progressHUD=KProgressHUD.create(Add_User.this);
        addEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,address,phobe,email;
                name=username_added.getText().toString().toLowerCase().trim();
                address=phone_added.getText().toString().toLowerCase().trim();
                phobe=email_add.getText().toString().toLowerCase().trim();
                email=date_picker.getText().toString().toLowerCase().trim();
                 Random myRandom = new Random();
                 final String randomkey=String.valueOf(myRandom.nextInt(100000));
                url=randomkey;
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(address)
                        ||TextUtils.isEmpty(phobe)||TextUtils.isEmpty(email)) {
                    Toast.makeText(Add_User.this, "Fill up all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    progress_check();
                    //textSend_user();
                    firebaseAuth.createUserWithEmailAndPassword(phobe,"123456")
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        textSend_user();
                                        store_information1(name,address,phobe,email,randomkey);
                                        store_information2(name,address,phobe,email,randomkey);
                                        store_information(name,address,phobe,email,randomkey);


                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressHUD.dismiss();
                            Toast.makeText(Add_User.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });
    }

    private void store_information2(String name,
                                    String address, String phobe,
                                    String email, String randomkey) {
       /* Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("address", address);
        user.put("phobe", phobe);
        user.put("email", email);
        user.put("randomkey",randomkey);*/
        User_model user_model=new User_model(name,phobe,email,randomkey);

// Add a new document with a generated ID
        firebaseFirestore.collection("Profile")
                .document(phobe)
                .set(user_model)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //progressHUD.dismiss();
                            textSend_user();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                        progressHUD.dismiss();
                        Toast.makeText(Add_User.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void store_information1(String name,
                                    String address, String phobe,
                                    String email, String randomkey) {
       /* Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("address", address);
        user.put("phobe", phobe);
        user.put("email", email);
        user.put("randomkey",randomkey);*/
        User_model user_model=new User_model(name,phobe,email,randomkey);

// Add a new document with a generated ID
        firebaseFirestore.collection("users")
                .document(randomkey)
                .set(user_model)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //progressHUD.dismiss();
                            textSend_user();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                        progressHUD.dismiss();
                        Toast.makeText(Add_User.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void store_information(String name,
                                   String address, String phobe,
                                   String email, String randomkey) {

        /*Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("address", address);
        user.put("phobe", phobe);
        user.put("email", email);
        user.put("randomkey",randomkey);*/
        User_model user_model=new User_model(name,phobe,email,randomkey);

// Add a new document with a generated ID
        firebaseFirestore.collection("Temporary")
                .document(randomkey)
                .set(user_model)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressHUD.dismiss();

                            Toast.makeText(Add_User.this, "Account Activated", Toast.LENGTH_SHORT).show();
                            textSend_user();
                            startActivity(new Intent(getApplicationContext(),Admin_panel.class));

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                        progressHUD.dismiss();
                        Toast.makeText(Add_User.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void textSend_user() {
        int permission= ContextCompat.checkSelfPermission(Add_User.this, Manifest.permission.SEND_SMS);
        if (permission== PackageManager.PERMISSION_GRANTED) {
            sending();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }
    private void sending() {
        String phone_number1233=phone_added.getText().toString();
        String sm333s="Your Accout Actived"+"\nYour License key is : "+url+"\nThank You.";
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phone_number1233,null,sm333s,null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();

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
    private void progress_check() {
        progressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }
}