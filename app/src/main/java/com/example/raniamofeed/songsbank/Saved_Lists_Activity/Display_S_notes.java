package com.example.raniamofeed.songsbank.Saved_Lists_Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.Saved_Lists_Activity.Data.DatabaseHandler;
import com.example.raniamofeed.songsbank.Saved_Lists_Activity.Model.MyWish;
import com.example.raniamofeed.songsbank.login_Register.Login;

import java.util.ArrayList;

public class Display_S_notes extends Activity implements RclAdapter.OnItemClicked{

    private DatabaseHandler dba;
    private ArrayList<MyWish> dbWishes = new ArrayList<>();
    private RecyclerView recyclerView;
    private RclAdapter adapter;
    Button plusdialog;
    AlertDialog.Builder builder;
    String [] ListItemChooes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__s_notes);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        plusdialog=(Button)findViewById(R.id.plus);
        builder = new AlertDialog.Builder(Display_S_notes.this);

        refreshData();
        plusdialog.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                ListItemChooes=new String[]{"Saved Songs","User Songs","Add Notes"};
                builder.setTitle("choose something..");
                builder.setSingleChoiceItems(ListItemChooes, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i)
                        {
                            case 0:
                                Toast.makeText(Display_S_notes.this,"Saved Songs", Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(Display_S_notes.this,"User Songs", Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Intent intentList=new Intent(Display_S_notes.this,Lists.class);
                                startActivity(intentList);
                                break;
                        }

                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog mbuilder=builder.create();
                mbuilder.show();



            }
        });
    }

    private void initRecycler(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new RclAdapter(dbWishes ,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                manager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void refreshData() {
        dbWishes.clear();
        dba = new DatabaseHandler(getApplicationContext());

        ArrayList<MyWish> wishesFromDB = dba.getWishes();

        for (int i = 0; i < wishesFromDB.size(); i++){

            String title = wishesFromDB.get(i).getTitle();
            String dateText = wishesFromDB.get(i).getRecordDate();
            String content = wishesFromDB.get(i).getContent();
            int mid = wishesFromDB.get(i).getItemId();


            //Log.v("IDs: " , String.valueOf(mid));

            MyWish myWish = new MyWish();
            myWish.setTitle(title);
            myWish.setContent(content);
            myWish.setRecordDate(dateText);
            myWish.setItemId(mid);



            dbWishes.add(myWish);


        }
        dba.close();
        initRecycler();
    }

    @Override
    public void onClicked(String title, String date, String content, int id) {
        Intent i = new Intent(Display_S_notes.this, Songs.class);
        i.putExtra("content", content);
        i.putExtra("date", date);
        i.putExtra("title", title);
        i.putExtra("id", id);
        startActivity(i);
    }
//    public void displayaAert(String message) {
//        builder.setMessage(message);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}
