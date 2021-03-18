package com.example.pdf_project.admin_panel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdf_project.Books;
import com.example.pdf_project.R;

import java.util.List;

public class Book_Adapter extends RecyclerView.Adapter<Book_Adapter.myview> {
    public List<Book_model> data;

    public Book_Adapter(List<Book_model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_design,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        holder.textViewTitle.setText(data.get(position).getName());
        final String url=data.get(position).getPdf();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(v.getContext(), Reader.class);
                intent1.putExtra("url",url);
                intent1.putExtra("name",data.get(position).getName());
                v.getContext().startActivity(intent1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView textViewTitle;

        public myview(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);

        }
    }
}
