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
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
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

public class PopUp extends AppCompatActivity {


    String JSON_STRING,color,shape,imprint;
    String json_url,json_string;
    TextView tx_name,tx_salts,tx_strength;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);


        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#73D6EF")));
        actionBar.setTitle("Your Medicine");

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));
        Bundle bundle=getIntent().getExtras();
        color=bundle.getString("color");
        shape=bundle.getString("shape");
        imprint=bundle.getString("imprint");
        imprint=" "+imprint+" ";
        tx_name=(TextView)findViewById(R.id.tx_name);
        tx_salts=(TextView)findViewById(R.id.tx_salts);
        tx_strength=(TextView)findViewById(R.id.tx_strength);

        new BackgroundTaskPopUp().execute();


    }

    public class BackgroundTaskPopUp extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPreExecute() {
            json_url="http://medifinder.net16.net/App/pill_identifire.php";
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
                String data_string= URLEncoder.encode("color", "UTF-8")+"="+URLEncoder.encode(color,"UTF-8")+"&"+
                        URLEncoder.encode("shape","UTF-8")+"="+URLEncoder.encode(shape,"UTF-8")+"&"+
                        URLEncoder.encode("imprint","UTF-8")+"="+URLEncoder.encode(imprint,"UTF-8");
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
        String name,salts,strength;
        JSONObject jsonObject;
        JSONArray jsonArray;
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            if(jsonArray.length()==0)
            {
                tx_name.setText("No Results");
                tx_salts.setText("No Results");
                tx_strength.setText("No Results");
            }
            while(count<jsonArray.length()){
                JSONObject JO=jsonArray.getJSONObject(count);

                name=JO.getString("name");
                salts=JO.getString("salts");
                strength=JO.getString("strength");
                count++;
                tx_name.setText(name);
                tx_salts.setText(salts);
                tx_strength.setText(strength);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
