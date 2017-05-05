package com.example.teo.firebaseloaddata;

import android.database.DatabaseUtils;
import android.graphics.LinearGradient;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by teo on 04/05/2017.
 */

public class FireLoad {
    public void SaveSQlite(final DBHelper dbHelper, final int start, final int end){
        DatabaseReference ref;

        ref = FirebaseDatabase.getInstance().getReference();



        ref.child("id").orderByKey().startAt(start + "").endAt(end + "").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                int val = Integer.parseInt(dataSnapshot.getKey());
                Log.d("Key : ", val + "" );
                if (dbHelper.checkIdExistsRow(val)){
                    dbHelper.insertId(val);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = dbHelper.numberOfRows();
                Log.d("Hoan Thanh", count + " rows");

                new DataSQLiteAsyncTask().execute(start + "", end + "");



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
