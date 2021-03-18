package com.example.pdf_project.admin_panel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.pdf_project.Books;
import com.example.pdf_project.Help_text;
import com.example.pdf_project.History_Category;
import com.example.pdf_project.R;
import com.example.pdf_project.User_history;
import com.example.pdf_project.admin_panel.Add_PDf.Pdf_Category;

public class Admin_panel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Admin Panel");
        setSupportActionBar(toolbar);
        CardView card_view1=findViewById(R.id.card_view1);
        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Pdf_Category.class));
            }
        });
        CardView card_view=findViewById(R.id.card_view);
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Add_User.class));
            }
        });
        CardView card_view2=findViewById(R.id.card_view2);
        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), User_history.class));
            }
        });
        CardView card_view4=findViewById(R.id.card_view4);
        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), History_Category.class));
            }
        });
        CardView card_view5=findViewById(R.id.card_view5);
        card_view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Help_text.class));
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
}