package com.example.pdf_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        ConstraintLayout relativeLayout = findViewById(R.id.r3);
        relativeLayout.startAnimation(animation);
        firebaseAuth=FirebaseAuth.getInstance();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //startActivity(new Intent(getApplicationContext(),Give_License.class));
                if (firebaseAuth.getCurrentUser()!=null) {
                    startActivity(new Intent(getApplicationContext(),User.class));
                }
                else {
                    startActivity(new Intent(getApplicationContext(),Give_License.class));
                }
            }
        },1500);
    }
}