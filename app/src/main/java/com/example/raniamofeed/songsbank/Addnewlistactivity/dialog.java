package com.example.raniamofeed.songsbank.Addnewlistactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.raniamofeed.songsbank.R;

import java.security.PublicKey;

/**
 * Created by Rania Mofeed on 5/22/2019.
 */

public class dialog extends AppCompatDialogFragment{
        private EditText namelist;
        private EditText Date;
        private dialogListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog,null);
                builder.setView(view)
                        .setTitle("Name List")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String name=namelist.getText().toString();
                                String Dte=Date.getText().toString();
                                listener.applyText(name,Dte);


                            }
                        });
                namelist= (EditText) view.findViewById(R.id.Name_list);
                Date= (EditText) view.findViewById(R.id.date);
                return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener= (dialogListener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString()+"must implement dialogListener");
        }
    }

    public interface dialogListener{
        void applyText(String NameList,String Date);

    }
}

