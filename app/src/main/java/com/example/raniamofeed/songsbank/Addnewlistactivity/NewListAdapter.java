package com.example.raniamofeed.songsbank.Addnewlistactivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raniamofeed.songsbank.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rania Mofeed on 5/23/2019.
 */

public class NewListAdapter extends RecyclerView.Adapter<NewListAdapter.viewholder> {
    private List<ItemRecycleviewlist> recycleviewlists;

    public static class viewholder extends RecyclerView.ViewHolder {
        public ImageView mimageView;
        public TextView mText1;
        public TextView mtext2;

        public viewholder(View itemView) {
            super(itemView);
            mimageView = (ImageView) itemView.findViewById(R.id.image_item);
            mText1 = (TextView) itemView.findViewById(R.id.list_nameitem);
            mtext2 = (TextView) itemView.findViewById(R.id.datecreate);
        }
    }

    public void setList(List<ItemRecycleviewlist> examplelist){
        recycleviewlists = examplelist;
        notifyDataSetChanged();
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_addnewlist, parent, false);
        viewholder evh=new viewholder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        ItemRecycleviewlist curent_recycleviewlist=recycleviewlists.get(position);
        holder.mimageView.setImageResource(curent_recycleviewlist.getMresourceimage());
        holder.mText1.setText(curent_recycleviewlist.getMname());
        holder.mtext2.setText(curent_recycleviewlist.getMdate());



    }

    @Override
    public int getItemCount() {
        if (recycleviewlists != null)
            return recycleviewlists.size();
        return 0;
    }
}
