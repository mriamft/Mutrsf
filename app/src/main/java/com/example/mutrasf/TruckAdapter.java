
package com.example.mutrasf;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.Holder>{

    public interface OnBtnClicked{
        void onBtnClickedListener(int pos);
    }



    private Context context;
    private ArrayList name,  price, phone, id;

    private OnBtnClicked listener;


    public TruckAdapter(Context context, ArrayList name, ArrayList price, ArrayList phone, ArrayList id) {
        this.context = context;
        this.name = name;
        this.price = price;
        this.phone = phone;
        this.id = id;
    }

    public void SetBtn(OnBtnClicked onBtnClicked){
        listener = onBtnClicked;
    }




    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displaytrucks, parent, false);
        return new Holder(v , listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name.setText(String.valueOf(name.get(position)));
        holder.price.setText(String.valueOf(price.get(position)));
        holder.phone.setText(String.valueOf(phone.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name, price,phone;

        public Holder(@NonNull View itemView,OnBtnClicked onBtnClicked) {
            super(itemView);
            name = itemView.findViewById(R.id.TextName);
            price = itemView.findViewById(R.id.TextPrice);
            phone= itemView.findViewById(R.id.TextContact);

        }
    }
}


