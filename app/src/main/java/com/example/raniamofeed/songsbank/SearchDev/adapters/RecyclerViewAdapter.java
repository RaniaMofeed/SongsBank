package com.example.raniamofeed.songsbank.SearchDev.adapters;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.SearchDev.activities.ResultSearch;
import com.example.raniamofeed.songsbank.SearchDev.model.Songs;

import java.util.List;

/**
 * Created by Rania Mofeed on 2/9/2019.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyviewHolder> {
    public OnItemClick listener ;
    private List<Songs> mdata;
    //context-->

    public RecyclerViewAdapter(OnItemClick listener, List<Songs> mdata) {
        this.listener = listener;
        this.mdata = mdata;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        view=inflater.inflate(R.layout.wish_rowsearch,parent,false);
         return new  MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
          Songs listItem=mdata.get(position);
          holder.tv_songname.setText(listItem.getSong_name());
          holder.tvmusic_keys.setText(listItem.getMusic_keys());
          holder.tvsupject.setText(listItem.getSubjects());
          holder.tv_verse.setText(listItem.getVerses());
          holder.Words.setText(listItem.getSong_words());
          holder.linearSearch.setOnClickListener(view -> listener.onClick(mdata.get(position)));

    }
 //known the size of list that return from server;
    @Override
    public int getItemCount() {
        return mdata.size();
    }



    public static class MyviewHolder extends RecyclerView.ViewHolder{
        //this part i determine the single view by declare the all elements
        TextView tv_songname;
        TextView tvmusic_keys;
        TextView tvsupject;
        TextView tv_verse;
        TextView Words;
        LinearLayout linearSearch;
        public MyviewHolder(View itemView) {
            super(itemView);
            linearSearch = (LinearLayout) itemView.findViewById(R.id.liner_search);
            tv_songname= (TextView) itemView.findViewById(R.id.rowname);
            tvmusic_keys= (TextView) itemView.findViewById(R.id.row_musickey);
            tvsupject= (TextView) itemView.findViewById(R.id.row_supject);
            tv_verse= (TextView) itemView.findViewById(R.id.row_verses);
            Words=(TextView)itemView.findViewById(R.id.row_words);
        }

    }
    public interface OnItemClick{
        void onClick(Songs position);
    }
}
