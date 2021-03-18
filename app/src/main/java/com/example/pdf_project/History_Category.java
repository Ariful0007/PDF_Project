package com.example.pdf_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pdf_project.admin_panel.Add_PDf.Admin_Books;
import com.example.pdf_project.admin_panel.Add_PDf.Admin_help;

public class History_Category extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__category);
        Toolbar toolbar = findViewById(R.id.toolbar97);

        toolbar.setTitle("History Category");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CardView book_h1=findViewById(R.id.book_h1);
        book_h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Books.class));
            }
        });
        CardView helpp11=findViewById(R.id.helpp11);
        helpp11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Help_Books.class));
            }
        });
    }
}