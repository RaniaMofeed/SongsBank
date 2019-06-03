package com.example.raniamofeed.songsbank.Addnewlistactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.raniamofeed.songsbank.R;
import com.example.raniamofeed.songsbank.data_storage.SongsSharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AddNewList extends AppCompatActivity implements dialog.dialogListener {
    private ItemRecycleviewlist listexample;
    Button AddList;
    private RecyclerView recyclerView;
    private NewListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    static String namelist ,modelStr;
    List<ItemRecycleviewlist> list;
    static String date;

  //  private Gson gson;
    private SongsSharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_list);
//        gson = new Gson();
//        preferences = new SongsSharedPreferences(this);
        list = new ArrayList<>();
        initRecycler();
        AddList = (Button) findViewById(R.id.plusddd);
        AddList.setOnClickListener(view -> openDialog()); //lambda expression


    }

    public void openDialog() {
        dialog dialog = new dialog();
        dialog.show(getSupportFragmentManager(), "example Dialog");
    }

    @Override
    public void applyText(String NameList, String Date) {
        namelist=NameList;
        date=Date;
        createExamplelist();
    }

    public void createExamplelist() {
        /*if (getListItem() != null){
            adapter.setList(getListItem());
            adapter.notifyDataSetChanged();
        }else {*/
            list.add(new ItemRecycleviewlist(R.drawable.ic_menu_moreoverflow,namelist , date));
            adapter.setList(list);
            //modelStr = gson.toJson(listexample);
            //preferences.setDataFromSharedPreferences(listexample);
       // }
    }

    private void initRecycler(){
        recyclerView = (RecyclerView) findViewById(R.id.listnewlist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new NewListAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /*private List<ItemRecycleviewlist.ListItems> getListItem(){
        List<ItemRecycleviewlist.ListItems> list = preferences.getDataFromSharedPreferences();
           return list;
    }*/
}
