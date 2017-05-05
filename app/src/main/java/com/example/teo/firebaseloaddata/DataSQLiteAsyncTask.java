package com.example.teo.firebaseloaddata;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by teo on 05/05/2017.
 */

public class DataSQLiteAsyncTask extends AsyncTask<String, ArrayList<String>, ArrayList<String>> {
    @Override
    protected ArrayList<String> doInBackground(String... params) {

        ArrayList<String> s =  MainActivity.dbHelper.getAllId(Integer.parseInt(params[0]) , Integer.parseInt(params[1]));

        return s;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        String noidung = "";
        for (int i = 0; i< strings.size(); i++){

            noidung = noidung + strings.get(i) + "\t";

        }
        MainActivity.textView.setText(noidung);
        super.onPostExecute(strings);
    }
}
