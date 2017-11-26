package com.example.akshatsharma.medifinder;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SymDisplay extends AppCompatActivity {

    private ActionBar actionBar;
    private String body_part,JSON_STRING,json_url,json_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sym_display);

        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#73D6EF")));
        actionBar.setTitle("Your Possible Disease");

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .5));


        Bundle bundle=getIntent().getExtras();
        body_part=bundle.getString("part");

        new BackgroundTaskSymCheck().execute();

    }
    public class BackgroundTaskSymCheck extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute() {
            json_url="http://medifinder.net16.net/App/sym_checker.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string= URLEncoder.encode("part", "UTF-8")+"="+URLEncoder.encode(body_part,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();




                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while( (JSON_STRING=bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_string=result;
            displayPopUp();
        }
    }

    public void displayPopUp(){

        JSONObject jsonObject;
        JSONArray jsonArray;
        ListView listView=(ListView)findViewById(R.id.symListView);
        SymAdapter symAdapter=new SymAdapter(this,R.layout.sym_check_layout);
        listView.setAdapter(symAdapter);
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            String dis;
            while(count<jsonArray.length()){
                JSONObject JO=jsonArray.getJSONObject(count);
                dis=JO.getString("diseases");
                SymCheck symCheck=new SymCheck(dis);
                symAdapter.add(symCheck);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
