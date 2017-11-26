package com.example.akshatsharma.medifinder;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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

public class Hospitals extends AppCompatActivity {
    String JSON_STRING,json_string;
    private String toSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#73D6EF")));
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Hospitals");

        getJSON();
    }
    public void searchHos(View view){
        SearchView hosSearchView = (SearchView) findViewById(R.id.hosSearchView);
        toSearch=hosSearchView.getQuery().toString();
        new BackgroundSearchTaskHos().execute();
        Toast.makeText(this,"Searching Starts",Toast.LENGTH_LONG).show();
    }

    class BackgroundSearchTaskHos extends AsyncTask<Void,Void,String>{
        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="http://medifinder.net16.net/App/json_get_search_data_hos.php";
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
                String data_string= URLEncoder.encode("query", "UTF-8")+"="+URLEncoder.encode(toSearch,"UTF-8");
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
            displayListView();
        }
    }
    public void getJSON(){
        Toast.makeText(this, "JSON Parsing Starts", Toast.LENGTH_SHORT).show();
        new BackgroundTaskHos().execute();
        Toast.makeText(this,"JSON Parsing Ends",Toast.LENGTH_LONG).show();
    }
    public class BackgroundTaskHos extends AsyncTask<Void,Void,String> {
        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="http://medifinder.net16.net/App/json_get_data_hos.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
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
            displayListView();
        }
    }
    public void displayListView(){
        JSONObject jsonObject;
        JSONArray jsonArray;
        ListView hosListView = (ListView) findViewById(R.id.hosListView);
        HosAdapter hosAdapter=new HosAdapter(this,R.layout.hos_row_layout);
        hosListView.setAdapter(hosAdapter);
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            while(count<jsonArray.length()){
                JSONObject JO=jsonArray.getJSONObject(count);
                String name,city,address,contact;
                name=JO.getString("name");
                city=JO.getString("city");
                address=JO.getString("address");
                contact=JO.getString("contact");
                Hos hos=new Hos(name,city,address,contact);
                hosAdapter.add(hos);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hospitals, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
