package com.example.prem.myrecycler.premrecyclerviewgson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.prem.R;
import com.example.prem.interfaces.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by prem on 9/2/18.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context mContext;
    ArrayList<MyCountryModel> myCountryModels;
    private OnItemClickListener onItemClickListener;
    public MyAdapter(Context mContext, ArrayList<MyCountryModel> data) {
        this.mContext=mContext;
        this.myCountryModels=data;

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.my_data_adapter_layout, parent, false);

        return new MyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        MyCountryModel myCountryModel = myCountryModels.get(position);
        holder.mCountryName.setText(myCountryModel.getCountry_name());
        holder.mCountryId.setText(myCountryModel.getCountry_id());


    }

    @Override
    public int getItemCount() {
        return myCountryModels == null ? 0 : myCountryModels.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private final Button button;
        private TextView mCountryName, mCountryId;

        public ViewHolder(View itemView) {
            super(itemView);
            mCountryId=(TextView)itemView.findViewById(R.id.country_id_textview);
            mCountryName=itemView.findViewById(R.id.country_name_textview);
            button=itemView.findViewById(R.id.button);
            button.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }

        }
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }
}
