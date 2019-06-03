package com.example.raniamofeed.songsbank.Saved_Lists_Activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.Saved_Lists_Activity.Model.MyWish;

import java.util.ArrayList;

/**
 * Created by Rania Mofeed on 10/29/2018.
 */

public class RclAdapter extends RecyclerView.Adapter<RclAdapter.ViewHolder>{

    ArrayList<MyWish> mData;
    OnItemClicked listener;

    public RclAdapter(ArrayList<MyWish> mData, OnItemClicked listener) {
        this.mData = mData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wish_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(mData.get(i).getTitle());
        viewHolder.date.setText(mData.get(i).getRecordDate());
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClicked(mData.get(i).getTitle() ,mData.get(i).getRecordDate() ,
                        mData.get(i).getContent() ,mData.get(i).getItemId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView date;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.dateText);
            container = (RelativeLayout) itemView.findViewById(R.id.container);
        }
    }

    public interface OnItemClicked{
        void onClicked(String title ,String date ,String content ,int id);
    }
}
