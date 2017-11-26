package com.example.akshatsharma.medifinder;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,ActionBar.TabListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;    //a kind of a listner for noticing
                                                            //the change in drawerLayout
    private ActionBar actionBar,actionBar1;     //initializing action bar
    private DrawerLayout drawerLayout;
    private ListView navList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private List<navList1> navArray=new ArrayList<navList1>();
    private ViewPager viewPager;
    private Fragment frag;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);

        navList=(ListView)findViewById(R.id.navList);

        //ArrayList<String> navArray=new ArrayList<String>();

        /*navArray.add("Home");
        navArray.add("Disclaimer");
        navArray.add("Rate Us");
        navArray.add("FeedBack");
        navArray.add("About Us");*/
        navArray.add(new navList1("Home",R.drawable.home));
        navArray.add(new navList1("Disclaimer",R.drawable.disclaimer));
        navArray.add(new navList1("Rate Us",R.drawable.rate));
        navArray.add(new navList1("Feedback",R.drawable.feedback));
        navArray.add(new navList1("About Us", R.drawable.aboutus));

        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //for highlighting the selected option

        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navArray);
        ArrayAdapter<navList1> adapter=new navListAdapter();
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);


        //syncing the drawer layout
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.openDrawer,R.string.closeDrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);



        //to tell the user that there is a nevigation menu hidden
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#73D6EF")));
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);       //giving back arrow key on action bar
        actionBar.setTitle("medifinder");
        actionBar.setLogo(R.drawable.logo);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);  //gives the back arrow in action bar
        //have the property to add and remove the fragments when needed
        fragmentManager=getSupportFragmentManager();
        //fragmentTransaction=fragmentManager.beginTransaction();


        //loadSelection(0);           //by default the home page is selected

        viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new myAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                actionBar1.setSelectedNavigationItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        actionBar1=getSupportActionBar();
        actionBar1.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1=actionBar1.newTab();
        tab1.setText("Home");
        tab1.setTabListener(this);

        ActionBar.Tab tab2=actionBar1.newTab();
        tab2.setText("Diseases Checker");
        tab2.setTabListener(this);
        ActionBar.Tab tab3=actionBar1.newTab();
        tab3.setText("Pill Identifier");
        tab3.setTabListener(this);

        actionBar1.addTab(tab1);
        actionBar1.addTab(tab2);
        actionBar1.addTab(tab3);

    }


    private class myAdapter extends FragmentPagerAdapter{

        public myAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            frag=null;
            if(position==0){
                frag=new HomeFragment();
                /*HomeFragment homeFragment=new HomeFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.pager, homeFragment);
                fragmentTransaction.commit();*/

            }
            else if(position==1){

                frag=new symptomChecker();
                /*symptomChecker sc=new symptomChecker();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.pager,sc);
                fragmentTransaction.commit();*/

            }
            else if(position==2){

                frag=new pillIdentifier();
                /*pillIdentifier pi=new pillIdentifier();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.pager,pi);
                fragmentTransaction.commit();*/
            }

            /*fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.pager,frag);
            fragmentTransaction.commit();*/

            return frag;
        }

        @Override
        public int getCount() {
            return 3;
        }


    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    private class navListAdapter extends ArrayAdapter<navList1>{


        public navListAdapter(){
            super(MainActivity.this, R.layout.nav_list_view, navArray);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View listView=convertView;
            LayoutInflater navInflater=LayoutInflater.from(getContext());
            listView=navInflater.inflate(R.layout.nav_list_view,parent,false);
            //find the option to work with
            navList1 currentListOption=navArray.get(position);

            //fill the view

            ImageView iv=(ImageView)listView.findViewById(R.id.navListImage);
            iv.setImageResource(currentListOption.getIconId());

            //give the name to the nav list option

            TextView listOption=(TextView)listView.findViewById(R.id.navListText);
            listOption.setText(currentListOption.getName());

            return listView;
            //return super.getView(position, convertView, parent);
        }
    }

    private void loadSelection(int i){      //for default location
        navList.setItemChecked(i,true);

        switch (i){
            case 0:
                HomeFragment homeFragment=new HomeFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                this.fragmentTransaction.replace(R.id.fragmentHolder, homeFragment);
                /*
                fragment transaction has the property to add and delete all the fragments
replacing the current fragment with new one and commiting the transaction
                */

                fragmentTransaction.commit();
                finish();
                Intent again=new Intent(this,MainActivity.class);
                startActivity(again);
                break;
            case 1:
                Disclaimer disclaimer=new Disclaimer();
                fragmentTransaction = fragmentManager.beginTransaction();
                this.fragmentTransaction.replace(R.id.fragmentHolder,disclaimer);
                fragmentTransaction.commit();
                break;
            case 2:
                RateUs rateUs=new RateUs();
                fragmentTransaction = fragmentManager.beginTransaction();
                this.fragmentTransaction.replace(R.id.fragmentHolder, rateUs);
                this.fragmentTransaction.commit();
                break;
            case 3:
                /*Feedback feedback=new Feedback();
                fragmentTransaction = fragmentManager.beginTransaction();
                this.fragmentTransaction.replace(R.id.fragmentHolder, feedback);
                this.fragmentTransaction.commit();*/
                Intent emailIntent=new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"medifindergroup@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_CC,new String[]{"akshatsharma3800@gmail.com.com"});


                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT,"Body");

                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Choose Email Client"));
                break;
            case 4:
                AboutUs aboutUs=new AboutUs();
                fragmentTransaction = fragmentManager.beginTransaction();
                this.fragmentTransaction.replace(R.id.fragmentHolder, aboutUs);
                this.fragmentTransaction.commit();
                break;

        }

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if (id==android.R.id.home){
            if(drawerLayout.isDrawerOpen(navList)){
                drawerLayout.closeDrawer(navList);
            }
            else {
                drawerLayout.openDrawer(navList);
            }
        }
        /*switch (id){
            case R.id.sym_chk:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent i=new Intent(this,symptomChecker.class);
                startActivity(i);
                return true;
            case R.id.pill_idn:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent j=new Intent(this,pillIdentifier.class);
                startActivity(j);
                return true;

        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        loadSelection(position);
        drawerLayout.closeDrawer(navList);

    }
    public void medButtonClicked(View view){
       /* ImageButton imageButton=(ImageButton)view.findViewById(R.id.medImageButton);
        imageButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HomeFragment.this,"Medicine button clicked baby",Toast.LENGTH_LONG).show();
                    }
                }
        );*/
        //Toast.makeText(this,"Medicine button clicked baby",Toast.LENGTH_LONG).show();
        Intent i=new Intent(this,Medicines.class);
        startActivity(i);
    }
    public void docButtonClicked(View view){
        Intent i=new Intent(this,Doctors.class);
        startActivity(i);
    }
    public void hosButtonClicked(View view){
        Intent i=new Intent(this,Hospitals.class);
        startActivity(i);
    }
    public void digCenButtonClicked(View view){
        Intent i=new Intent(this,DigCentre.class);
        startActivity(i);
    }

    public void bodyPart(View view){
        RadioGroup radio_g=(RadioGroup)findViewById(R.id.radioGroup);
        Button bodyButton=(Button)findViewById(R.id.bodyButton);
        int selected_id=radio_g.getCheckedRadioButtonId();
        RadioButton radio_b=(RadioButton)findViewById(selected_id);
        if(R.id.radio_male==selected_id){
            Intent m=new Intent(this,maleSymChecker.class);
            startActivity(m);
        }
        else if(R.id.radio_female==selected_id){
            Intent f=new Intent(this,femaleSymChecker.class);
            startActivity(f);
        }

    }
    public void EnterPart(View view){
        TextView EnteredBodyPart=(TextView)findViewById(R.id.EnteredBodyPart);
        RadioGroup radio_g=(RadioGroup)findViewById(R.id.radioGroup);
        Button bodyButton=(Button)findViewById(R.id.bodyButton);
        int selected_id=radio_g.getCheckedRadioButtonId();
        RadioButton radio_b=(RadioButton)findViewById(selected_id);
        if(R.id.radio_male==selected_id){
            Intent intent=new Intent(this,SymDisplay.class);
            Bundle bundle=new Bundle();
            bundle.putString("part", EnteredBodyPart.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(R.id.radio_female==selected_id){
            Intent intent=new Intent(this,SymDisplay.class);
            Bundle bundle=new Bundle();
            bundle.putString("part", EnteredBodyPart.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public void displayNow(View view){
        Spinner colorSpinner=(Spinner)findViewById(R.id.colorSpinner);
        Spinner shapeSpinner=(Spinner)findViewById(R.id.shapeSpinner);
        EditText imprint=(EditText)findViewById(R.id.imprint);
        Intent i=new Intent(this,PopUp.class);
        Bundle bundle=new Bundle();
        bundle.putString("imprint",imprint.getText().toString());
        bundle.putString("color",colorSpinner.getSelectedItem().toString());
        bundle.putString("shape",shapeSpinner.getSelectedItem().toString());
        i.putExtras(bundle);
        startActivity(i);
    }


}
