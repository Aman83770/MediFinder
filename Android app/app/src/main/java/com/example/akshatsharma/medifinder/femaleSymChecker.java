package com.example.akshatsharma.medifinder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class femaleSymChecker extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_sym_checker);
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#73D6EF")));
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);       //giving back arrow key on action bar
        actionBar.setTitle("Female Body");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_female_sym_checker, menu);
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
    public void headClicked(View view){
        Toast.makeText(this, "head", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SymDisplay.class);
        Bundle bundle=new Bundle();
        bundle.putString("part", "head");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void neckClicked(View view){
        Toast.makeText(this,"neck",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SymDisplay.class);
        Bundle bundle=new Bundle();
        bundle.putString("part", "neck");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void brestClicked(View view){
        Toast.makeText(this,"brest",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SymDisplay.class);
        Bundle bundle=new Bundle();
        bundle.putString("part", "chest");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void abdomenClicked(View view){
        Toast.makeText(this,"abdomen",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SymDisplay.class);
        Bundle bundle=new Bundle();
        bundle.putString("part", "abdomen");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void pelvisClicked(View view){
        Toast.makeText(this,"pelvis",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SymDisplay.class);
        Bundle bundle=new Bundle();
        bundle.putString("part", "pelvis");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void legClicked(View view){
        Toast.makeText(this, "leg", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SymDisplay.class);
        Bundle bundle=new Bundle();
        bundle.putString("part", "leg");
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void armClicked(View view){
        Toast.makeText(this,"arm",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,SymDisplay.class);
        Bundle bundle=new Bundle();
        bundle.putString("part", "arm");
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
