package com.example.pdf_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pdf_project.Client_SMS.Client_SMS;
import com.example.pdf_project.admin_panel.Add_User;
import com.example.pdf_project.admin_panel.Admin_panel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;

public class Give_License extends AppCompatActivity {
    TextInputEditText key;
    Button login;
    String text;
    KProgressHUD progressHUD;
    TextView text4;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    DocumentReference documentReference;
    String email,password;
    String license_key;

    FirebaseUser firebaseUser;
    String user;
    String previous_key;
    String name,phobe,address,randomkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give__license);
        //animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.activity_main);
        relativeLayout.startAnimation(animation);
        firebaseAuth=FirebaseAuth.getInstance();

        firebaseFirestore=FirebaseFirestore.getInstance();
        progressHUD=KProgressHUD.create(Give_License.this);
        login=findViewById(R.id.login);
        key=findViewById(R.id.username_edit_text);
        text4=findViewById(R.id.text4);
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getApplicationContext(),User_reg.class));
                AlertDialog.Builder  alertdialouge=new AlertDialog.Builder(Give_License.this);
                alertdialouge.setTitle("Buy a License key.");
                alertdialouge.setMessage("01754201532");
                alertdialouge.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String s="tel:"+"01754201532";
                        Intent intent=new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(s));
                        startActivity(intent);


                    }
                }).setNegativeButton("SMS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), Client_SMS.class));
                    }
                });
                alertdialouge.show();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                String key_l = key.getText().toString().trim();
                key_l = key_l.toLowerCase();
                license_key=key_l;
                if (TextUtils.isEmpty(key_l)) {
                    Toast.makeText(Give_License.this, "Enter License Key", Toast.LENGTH_LONG).show();

                    AlertDialog.Builder  alertdialouge=new AlertDialog.Builder(Give_License.this);
                    alertdialouge.setTitle("Enter License Key.");

                    final EditText editText=new EditText(Give_License.this);
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);

                    alertdialouge.setView(editText);
                    alertdialouge.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            text=editText.getText().toString().toLowerCase();
                            key.setText(text);
                            Toast.makeText(Give_License.this, ""+text, Toast.LENGTH_SHORT).show();

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertdialouge.show();

                }
                else if (key_l.equals("admin")) {
                    startActivity(new Intent(getApplicationContext(), Admin_panel.class));
                }

                else {
                    progress_check();
                    // startActivity(new Intent(getApplicationContext(),Next_Main.class));
                    firebaseFirestore.collection("Temporary").document(key_l)
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().exists()) {
                                    email=task.getResult().getString("phobe");
                                    password="123456";
                                    firebaseAuth.signInWithEmailAndPassword(email,password)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        progressHUD.dismiss();


                                                        Toast.makeText(Give_License.this, "You are login", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(getApplicationContext(),User.class));
                                                        delete_parent(license_key);
                                                    }
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressHUD.dismiss();
                                            Toast.makeText(Give_License.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();


                                        }
                                    });

                                }
                                else {
                                    progressHUD.dismiss();
                                    Toast.makeText(Give_License.this, "You are not a valid user", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder  alertdialouge=new AlertDialog.Builder(Give_License.this);
                                    alertdialouge.setTitle("Recover License Key.");
                                    alertdialouge.setMessage("Enter Previous Key.");
                                    final EditText editText=new EditText(Give_License.this);
                                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
                                    alertdialouge.setView(editText);
                                    alertdialouge.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            text=editText.getText().toString().toLowerCase();
                                            key.setText(text);
                                            previous_key=text;
                                            //check_user_reques();
                                            textSend_user();



                                        }
                                    }).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    alertdialouge.show();
                                }
                            }
                            else {
                                progressHUD.dismiss();
                                Toast.makeText(Give_License.this, "Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressHUD.dismiss();
                            Toast.makeText(Give_License.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }catch (Exception e) {
            }
        }
        });
    }

    private void progress_check() {
        progressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }
    private void delete_parent(String url1) {
        firebaseFirestore.collection("Temporary").whereEqualTo("randomkey",url1.toString())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        WriteBatch writeBatch=FirebaseFirestore.getInstance().batch();
                        List<DocumentSnapshot> snapshotList=queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot documentSnapshot:snapshotList) {
                            writeBatch.delete(documentSnapshot.getReference());
                        }
                        writeBatch.commit()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressHUD.dismiss();
                                        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                                        //startActivity(new Intent(getApplicationContext(),Active_list.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressHUD.dismiss();
                                Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressHUD.dismiss();
                Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void textSend_user() {
        int permission= ContextCompat.checkSelfPermission(Give_License.this, Manifest.permission.SEND_SMS);
        if (permission== PackageManager.PERMISSION_GRANTED) {
            sending();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }
    private void sending() {
        String phone_number1233="01754201532";
        String sm333s="Accout Recovery Request"+"\nUser Previous License Key is :"+previous_key+"\nThank You";
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
    private void check_user_reques()
    {
        firebaseFirestore.collection("users")
                .document(previous_key)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists())
                            {
                                name=task.getResult().getString("name");
                                phobe=task.getResult().getString("phobe");
                                address=task.getResult().getString("address");
                                email=task.getResult().getString("email");
                                randomkey=task.getResult().getString("randomkey");

                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Give_License.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}