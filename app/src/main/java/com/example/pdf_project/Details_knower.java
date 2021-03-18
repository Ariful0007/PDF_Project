package com.example.pdf_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pdf_project.admin_panel.Admin_panel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

public class Details_knower extends AppCompatActivity {
    TextView username_added,phone_added,email_add;
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
        setContentView(R.layout.activity_details_knower);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        username_added=findViewById(R.id.username_added);
        phone_added=findViewById(R.id.phone_added);
        email_add=findViewById(R.id.email_add);
        date_picker=findViewById(R.id.date_picker);
        addEd=findViewById(R.id.addEd);
        //intent
        url=getIntent().getStringExtra("key");
        firebaseFirestore.collection("users").document(url)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            username_added.setText(task.getResult().getString("name"));
                            phone_added.setText(task.getResult().getString("address"));
                            email_add.setText(task.getResult().getString("phobe"));
                            date_picker.setText(task.getResult().getString("email"));
                        }
                    }
                });
        addEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Admin_panel.class));
            }
        });
    }
}