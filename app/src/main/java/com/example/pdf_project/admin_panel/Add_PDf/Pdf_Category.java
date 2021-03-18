package com.example.pdf_project.admin_panel.Add_PDf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pdf_project.R;
import com.example.pdf_project.admin_panel.Book_pdf;
import com.example.pdf_project.admin_panel.Help_pdf;

public class Pdf_Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf__category);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Pdf Category");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CardView book_h=findViewById(R.id.book_h);
        book_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Book_pdf.class));

            }
        });
        CardView helpp=findViewById(R.id.helpp);
        helpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Help_pdf.class));
            }
        });
    }
}