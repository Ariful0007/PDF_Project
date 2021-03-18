package com.example.pdf_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pdf_project.admin_panel.Add_User;
import com.example.pdf_project.admin_panel.Admin_panel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;

public class Help_text extends AppCompatActivity {
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
        setContentView(R.layout.activity_help_text);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        username_added=findViewById(R.id.username_added);
        addEd=findViewById(R.id.addEd);
        progressHUD=KProgressHUD.create(Help_text.this);
        addEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String help=username_added.getText().toString().trim();
                if (TextUtils.isEmpty(help)) {
                    username_added.setError("Enter Text");
                    return;
                }
                else {
                    progress_check();
                    Map<String, Object> user = new HashMap<>();
                    user.put("first", help);
                    firebaseFirestore.collection("Text").document("text_help")
                            .set(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        progressHUD.dismiss();
                                        Toast.makeText(Help_text.this, "Text Added", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), Admin_panel.class));
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Help_text.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });



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
}