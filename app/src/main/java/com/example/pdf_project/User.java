package com.example.pdf_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class User extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView nav_view;
    FirebaseAuth firebaseAuth;
    TextView nai;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        firebaseFirestore=FirebaseFirestore.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("C12");
        setSupportActionBar(toolbar);
        //animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        RelativeLayout relativeLayout = findViewById(R.id.description);
        relativeLayout.startAnimation(animation);
        //fragment
        firebaseAuth=FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        nai=findViewById(R.id.nai);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        CardView card_view8=findViewById(R.id.card_view8);
        card_view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Books.class));
            }
        });
        CardView card_view15=findViewById(R.id.card_view15);
        card_view15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Help_Books.class));
            }
        });
        firebaseFirestore.collection("Text").document("text_help")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            nai.setText(task.getResult().getString("first"));
                        }
                    }
                });

    }
    @Override
    public void onBackPressed()   {
        AlertDialog.Builder warning = new AlertDialog.Builder(this)
                .setTitle("Are you Exit ?")
                .setMessage("You are logged in with Temporary Account. Exiting  will Delete All the data.")
                .setPositiveButton("Sync Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {

                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Data Sync Successfully", Toast.LENGTH_SHORT).show();

                            }
                        },2000);


                    }
                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ToDO: delete all the notes created by the Anon user

                        // TODO: delete the anon user
                        dialog.dismiss();
                        finishAffinity();

                    }
                });

        warning.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addNote:
                Toast.makeText(this, "You are now home now.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notes:
                startActivity(new Intent(getApplicationContext(),Profile.class));
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shareapp1:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notes2:
                startActivity(new Intent(getApplicationContext(),Download_list.class));
                Toast.makeText(this, "Download Pdf", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                AlertDialog.Builder warning = new AlertDialog.Builder(this)
                        .setTitle("Are you Logout ?")
                        .setMessage("You are logged in with Temporary Account. Exiting  will Delete All the data.")
                        .setPositiveButton("Stay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {

                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        dialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Data Sync Successfully", Toast.LENGTH_SHORT).show();

                                    }
                                },2000);


                            }
                        }).setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ToDO: delete all the notes created by the Anon user

                                // TODO: delete the anon user
                                firebaseAuth.signOut();
                                dialog.dismiss();
                                finishAffinity();

                            }
                        });

                warning.show();
                break;



        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}