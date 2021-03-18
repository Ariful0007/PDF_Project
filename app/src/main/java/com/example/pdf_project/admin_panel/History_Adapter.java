package com.example.pdf_project.admin_panel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdf_project.Details_knower;
import com.example.pdf_project.R;

import java.util.List;

public class History_Adapter extends RecyclerView.Adapter<History_Adapter.myview> {
    public List<User_model> data;

    public History_Adapter(List<User_model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_id,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        holder.text2.setText(data.get(position).getRandomkey());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username=data.get(position).getRandomkey();
                Intent intent1=new Intent(v.getContext(), Details_knower.class);
                intent1.putExtra("key",username);
                v.getContext().startActivity(intent1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView text2;

        public myview(@NonNull View itemView) {
            super(itemView);
            text2=itemView.findViewById(R.id.text2);
        }
    }
}
