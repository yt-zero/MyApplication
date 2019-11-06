package com.example.schedule;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    static String[] classes=new String[7];
    static String[] teachers=new String[7];
    static String[] roomN=new String[7];
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
        //find each TextView
        AutoCompleteTextView subject1 = findViewById(R.id.s1);
        AutoCompleteTextView subject2 = findViewById(R.id.s2);
        AutoCompleteTextView subject3 = findViewById(R.id.s3);
        AutoCompleteTextView subject4 = findViewById(R.id.s4);
        AutoCompleteTextView subject5 = findViewById(R.id.s5);
        AutoCompleteTextView subject6 = findViewById(R.id.s6);
        AutoCompleteTextView subject7 = findViewById(R.id.s7);
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
        //Store information for each Text View
        classes[0] = subject1.getText().toString();
        classes[1] = subject2.getText().toString();
        classes[2] = subject3.getText().toString();
        classes[3] = subject4.getText().toString();
        classes[4] = subject5.getText().toString();
        classes[5] = subject6.getText().toString();
        classes[6] = subject7.getText().toString();
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

        AutoCompleteTextView subject1=findViewById(R.id.s1);
        AutoCompleteTextView subject2=findViewById(R.id.s2);
        AutoCompleteTextView subject3=findViewById(R.id.s3);
        AutoCompleteTextView subject4=findViewById(R.id.s4);
        AutoCompleteTextView subject5=findViewById(R.id.s5);
        AutoCompleteTextView subject6=findViewById(R.id.s6);
        AutoCompleteTextView subject7=findViewById(R.id.s7);
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
        subject1.setText(classes[0]);
        subject2.setText(classes[1]);
        subject3.setText(classes[2]);
        subject4.setText(classes[3]);
        subject5.setText(classes[4]);
        subject6.setText(classes[5]);
        subject7.setText(classes[6]);
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Course.sort();
        setContentView(R.layout.activity_settings);
        ArrayList<String> informationList= new ArrayList<String>();
        //ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,informationList.toArray());
    }

}
