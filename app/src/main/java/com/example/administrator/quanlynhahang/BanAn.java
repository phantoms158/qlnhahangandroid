package com.example.administrator.quanlynhahang;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.quanlynhahang.Adapter.Adapterbanan;
import com.example.administrator.quanlynhahang.DTO.banan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class BanAn extends Fragment implements Runnable{
    GridView gridBanAn;
    String url = "http://localhost:8080/QLNhaHang/public/admin/quanlyban/getList";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.banan,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Màn Hình Bàn Ăn");

        //getData(url);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LoadSanPham().execute("http://10.50.100.179:8080/QLNhaHang/public/admin/quanlyban/getList");
            }
        });

    }
    private void getData(String url){
        //Showing a progress dialog while our app fetches the data from url


        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        Log.d("ketqua",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void showGrid(JSONArray jsonArray){
        //Looping through all the elements of json array
        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }




    @Override
    public void run() {

    }

    private class LoadSanPham extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {

            return GET_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<banan> mangbanan = new ArrayList<>();
            Log.d("ketqua",s);
            try {
                JSONArray array = new JSONArray(s);
                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject object = array.getJSONObject(i);
                    mangbanan.add(new banan(object.getString("TenBan"),object.getString("MaBan"),object.getString("TinhTrang")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Adapterbanan adapter = new Adapterbanan(getActivity().getApplicationContext(), R.layout.item_ban,mangbanan);
            gridBanAn.setAdapter(adapter);
        }
    }

    private static String GET_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();
        try
        {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return content.toString();
    }
}
