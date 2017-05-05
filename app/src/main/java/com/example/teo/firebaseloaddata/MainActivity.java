package com.example.teo.firebaseloaddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public static TextView textView;
    private Button button;
    public static DBHelper dbHelper;
    private int dem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        textView = (TextView) findViewById(R.id.text1);
        button = (Button) findViewById(R.id.button1);
        dbHelper = new DBHelper(getApplicationContext());
        //dem = 0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireLoad fireLoad = new FireLoad();
                fireLoad.SaveSQlite(dbHelper, 10-5, 10);
            }
        });

    }


}
