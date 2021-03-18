package com.example.pdf_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Pdf_Offline extends AppCompatActivity {
    private final String PDF_LINK="https://firebasestorage.googleapis.com/v0/b/pdfproject-dd2a3.appspot.com/o/uploads%2F1615265360900.pdf?alt=media&token=7e272225-4a6f-46ff-b4d6-4aec5c95cd7d";
private final String MY_PDF="my_pdf.pdf";
private AppCompatSeekBar seekBar;
private PDFView pdfView;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf__offline);
        pdfView=findViewById(R.id.pdfView);
        textView=findViewById(R.id.textview); 
        initiSeekbar(); 
        downloadPdf(MY_PDF); 
    }

    private void downloadPdf(String my_pdf) {
    }


    private void initiSeekbar() {
        seekBar=findViewById(R.id.seekbar);
        seekBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value=(progress*(seekBar.getWidth()-3*seekBar.getThumbOffset()))/seekBar.getMax();
                textView.setText(""+progress);
                textView.setX(seekBar.getX()+value+seekBar.getThumbOffset()/2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}