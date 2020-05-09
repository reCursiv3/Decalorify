package com.example.seapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter2 extends RecyclerView.Adapter<ExampleAdapter2.ExampleViewHolder> {
    private ArrayList<ExampleItem2> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView4;
        public TextView mTextView5;
        public TextView mTextView6;
        public Button add_food;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView4 = itemView.findViewById(R.id.food_name);
            mTextView5 = itemView.findViewById(R.id.cal_count);
            mTextView6 = itemView.findViewById(R.id.quantity);
            add_food=itemView.findViewById(R.id.add_food);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null)
                    {
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });

            add_food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }

                }
            });
        }
    }

    public ExampleAdapter2(ArrayList<ExampleItem2> exampleList){
        mExampleList =exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item2,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem2 currentItem2=mExampleList.get(position);

        holder.mTextView4.setText(currentItem2.getText1());
        holder.mTextView5.setText(currentItem2.getText2());
        holder.mTextView6.setText(currentItem2.getText3());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}
