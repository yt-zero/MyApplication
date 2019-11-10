package com.example.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    //Store store the information in the TextView
    String[] classes=new String[7];
    //save all AutoCompleteTextView into a ArrayList
    ArrayList<AutoCompleteTextView> info=new ArrayList<>();
    static SharedPreferences pref;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String COURSE1="COURSE1";
    public static final String COURSE2="COURSE2";
    public static final String COURSE3="COURSE3";
    public static final String COURSE4="COURSE4";
    public static final String COURSE5="COURSE5";
    public static final String COURSE6="COURSE6";
    public static final String COURSE7="COURSE7";

    public void AWeek(View view){
        TextView WeekS=findViewById(R.id.WeekS);
        WeekS.setText("A Week");
        Period.clearPeriods();
        Period.isBWeek=false;
    }
    public void BWeek(View view){
        TextView WeekS=findViewById(R.id.WeekS);
        WeekS.setText("B Week");
        Period.clearPeriods();
        Period.isBWeek=true;
    }
    public void setClasses(View view) {
        //Store information into each Text View
        for(int i=0;i<7;i++)
        {
            classes[i]=info.get(i).getText().toString();
        }
        SharedPreferences pref=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(COURSE1,classes[0]);
        editor.putString(COURSE2,classes[1]);
        editor.putString(COURSE3,classes[2]);
        editor.putString(COURSE4,classes[3]);
        editor.putString(COURSE5,classes[4]);
        editor.putString(COURSE6,classes[5]);
        editor.putString(COURSE7,classes[6]);
        editor.commit();
        for(int i=0;i<7;i++) {
            if(classes[i].length()>12) {
                Period.setAllPeriodInfo(i + 1 + "", classes[i].substring(0, 12));
            }
        }
        //find each TextView
//        AutoCompleteTextView subject1 = findViewById(R.id.s1);
//        AutoCompleteTextView subject2 = findViewById(R.id.s2);
//        AutoCompleteTextView subject3 = findViewById(R.id.s3); 
//        AutoCompleteTextView subject4 = findViewById(R.id.s4);
//        AutoCompleteTextView subject5 = findViewById(R.id.s5);
//        AutoCompleteTextView subject6 = findViewById(R.id.s6);
//        AutoCompleteTextView subject7 = findViewById(R.id.s7);
//        AutoCompleteTextView teacher1 = findViewById(R.id.T1);
//        AutoCompleteTextView teacher2 = findViewById(R.id.T2);
//        AutoCompleteTextView teacher3 = findViewById(R.id.T3);
//        AutoCompleteTextView teacher4 = findViewById(R.id.T4);
//        AutoCompleteTextView teacher5 = findViewById(R.id.T5);
//        AutoCompleteTextView teacher6 = findViewById(R.id.T6);
//        AutoCompleteTextView teacher7 = findViewById(R.id.T7);
//        AutoCompleteTextView room1 = findViewById(R.id.R1);
//        AutoCompleteTextView room2 = findViewById(R.id.R2);
//        AutoCompleteTextView room3 = findViewById(R.id.R3);
//        AutoCompleteTextView room4 = findViewById(R.id.R4);
//        AutoCompleteTextView room5 = findViewById(R.id.R5);
//        AutoCompleteTextView room6 = findViewById(R.id.R6);
//        AutoCompleteTextView room7 = findViewById(R.id.R7);
//        teachers[0] = teacher1.getText().toString();
//        teachers[1] = teacher2.getText().toString();
//        teachers[2] = teacher3.getText().toString();
//        teachers[3] = teacher4.getText().toString();
//        teachers[4] = teacher5.getText().toString();
//        teachers[5] = teacher6.getText().toString();
//        teachers[6] = teacher7.getText().toString();
//        roomN[0] = room1.getText().toString();
//        roomN[1] = room2.getText().toString();
//        roomN[2] = room3.getText().toString();
//        roomN[3] = room4.getText().toString();
//        roomN[4] = room5.getText().toString();
//        roomN[5] = room6.getText().toString();
//        roomN[6] = room7.getText().toString();


    }

    @Override
    protected void onStart() {
        super.onStart();

//        AutoCompleteTextView teacher1=findViewById(R.id.T1);
//        AutoCompleteTextView teacher2=findViewById(R.id.T2);
//        AutoCompleteTextView teacher3=findViewById(R.id.T3);
//        AutoCompleteTextView teacher4=findViewById(R.id.T4);
//        AutoCompleteTextView teacher5=findViewById(R.id.T5);
//        AutoCompleteTextView teacher6=findViewById(R.id.T6);
//        AutoCompleteTextView teacher7=findViewById(R.id.T7);
//        AutoCompleteTextView room1=findViewById(R.id.R1);
//        AutoCompleteTextView room2=findViewById(R.id.R2);
//        AutoCompleteTextView room3=findViewById(R.id.R3);
//        AutoCompleteTextView room4=findViewById(R.id.R4);
//        AutoCompleteTextView room5=findViewById(R.id.R5);
//        AutoCompleteTextView room6=findViewById(R.id.R6);
//        AutoCompleteTextView room7=findViewById(R.id.R7);
//        room1.setText(roomN[0]);
//        room2.setText(roomN[1]);
//        room3.setText(roomN[2]);
//        room4.setText(roomN[3]);
//        room5.setText(roomN[4]);
//        room6.setText(roomN[5]);
//        room7.setText(roomN[6]);
//        teacher1.setText(teachers[0]);
//        teacher2.setText(teachers[1]);
//        teacher3.setText(teachers[2]);
//        teacher4.setText(teachers[3]);
//        teacher5.setText(teachers[4]);
//        teacher6.setText(teachers[5]);
//        teacher7.setText(teachers[6]);
//        for(int i=0;i<7;i++){
//            info.get(i).setText(pref.getString(i+"",null));
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Different Array list to store different classes for different Period, so that period one classes will on appear on column one. You promise Extra credit for this feature :)
        ArrayList<String> informationList1= new ArrayList<String>();
        ArrayList<String> informationList2= new ArrayList<String>();
        ArrayList<String> informationList3= new ArrayList<String>();
        ArrayList<String> informationList4= new ArrayList<String>();
        ArrayList<String> informationList5= new ArrayList<String>();
        ArrayList<String> informationList6= new ArrayList<String>();
        ArrayList<String> informationList7= new ArrayList<String>();
        //Load TextView into an ArrayList
        info.add((AutoCompleteTextView) findViewById(R.id.s1));
        info.add((AutoCompleteTextView) findViewById(R.id.s2));
        info.add((AutoCompleteTextView) findViewById(R.id.s3));
        info.add((AutoCompleteTextView) findViewById(R.id.s4));
        info.add((AutoCompleteTextView) findViewById(R.id.s5));
        info.add((AutoCompleteTextView) findViewById(R.id.s6));
        info.add((AutoCompleteTextView) findViewById(R.id.s7));
        //load data in the shared Preferences file into each TextView
        pref=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        info.get(0).setText(pref.getString(COURSE1,""));
        info.get(1).setText(pref.getString(COURSE2,""));
        info.get(2).setText(pref.getString(COURSE3,""));
        info.get(3).setText(pref.getString(COURSE4,""));
        info.get(4).setText(pref.getString(COURSE5,""));
        info.get(5).setText(pref.getString(COURSE6,""));
        info.get(6).setText(pref.getString(COURSE7,""));
        Course.sort();
//        for(Course temp:Course.coursesL){
//            informationList1.add(temp.toString());
//        }
//        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,informationList1.toArray());
//        for(int i=0;i<7;i++)
//        {
//            info.get(i).setAdapter(adapter);
//        }
//        ArrayList<String> informationList= new ArrayList<String>();
//
//
        //convert arrayList consisting of Course into ArraryList consisiting of String.
        for(Course temp:Course.p1course){
            informationList1.add(temp.toString());
        }
        for(Course temp:Course.p2course){
            informationList2.add(temp.toString());
        }
        for(Course temp:Course.p3course){
            informationList3.add(temp.toString());
        }
        for(Course temp:Course.p4course){
            informationList4.add(temp.toString());
        }
        for(Course temp:Course.p5course){
            informationList5.add(temp.toString());
        }
        for(Course temp:Course.p6course){
            informationList6.add(temp.toString());
        }
        for(Course temp:Course.p7course){
            informationList7.add(temp.toString());
        }
        //set adapter to each Auto-complete Textview
        ArrayAdapter adapter1 =new ArrayAdapter(this,android.R.layout.simple_list_item_1,informationList1.toArray());
        ArrayAdapter adapter2 =new ArrayAdapter(this,android.R.layout.simple_list_item_1,informationList2.toArray());
        ArrayAdapter adapter3 =new ArrayAdapter(this,android.R.layout.simple_list_item_1,informationList3.toArray());
        ArrayAdapter adapter4 =new ArrayAdapter(this,android.R.layout.simple_list_item_1,informationList4.toArray());
        ArrayAdapter adapter5 =new ArrayAdapter(this,android.R.layout.simple_list_item_1,informationList5.toArray());
        ArrayAdapter adapter6 =new ArrayAdapter(this,android.R.layout.simple_list_item_1,informationList6.toArray());
        ArrayAdapter adapter7 =new ArrayAdapter(this,android.R.layout.simple_list_item_1,informationList7.toArray());
        ((AutoCompleteTextView)findViewById(R.id.s1)).setAdapter(adapter1);
        ((AutoCompleteTextView)findViewById(R.id.s2)).setAdapter(adapter2);
        ((AutoCompleteTextView)findViewById(R.id.s3)).setAdapter(adapter3);
        ((AutoCompleteTextView)findViewById(R.id.s4)).setAdapter(adapter4);
        ((AutoCompleteTextView)findViewById(R.id.s5)).setAdapter(adapter5);
        ((AutoCompleteTextView)findViewById(R.id.s6)).setAdapter(adapter6);
        ((AutoCompleteTextView)findViewById(R.id.s7)).setAdapter(adapter7);



        }
    }

