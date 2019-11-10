package com.example.schedule;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.EventListener;
/*
Things that are in the code:
Draw current periods, day and week
Read courses from database
Settings screen
A/B Week
Save settings from session to session
Read special days from database

Thing that are not yet in the code
Read periods from database
 */
public class MainActivity extends AppCompatActivity {
    //start Week Activity
    public void weekPress(View view){
        Intent myIntent = new Intent(MainActivity.this, Week.class); //Create a new intent
        MainActivity.this.startActivity(myIntent);
    }
    //start Setting Activity
    public void settingPress(View view){
        Intent myIntent = new Intent(MainActivity.this, Settings.class); //Create a new intent
        MainActivity.this.startActivity(myIntent);
    }
    //start Day Activity
    public void dayPress(View view){
        Intent myIntent = new Intent(MainActivity.this, Day.class); //Create a new intent
        MainActivity.this.startActivity(myIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=database.getReference();
        //read Course information form the data base
        databaseReference.child("Course").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // Log.i("infor","run");
                 Iterable <DataSnapshot> children =dataSnapshot.getChildren();
                 for(DataSnapshot child:children){
                     //Log.i("haha","123");
                     String CourseName="",Classroom="",Courselocal="",Section="",Teacher="";
                     int period=-1;
                    //Log.i("infor",""+child.getValue().toString());
                     for(DataSnapshot a:child.getChildren()){
                         String temp=a.getKey();
                         //Log.i("infor",""+a.getValue().toString());
                         switch(temp) {
                             case "COURSE::name":
                                 CourseName = a.getValue().toString();
                                 break;
                             case "Classroom":
                                 Classroom = a.getValue().toString();
                                 break;
                             case "Course":
                                 Courselocal = a.getValue().toString();
                                 break;
                             case "Period":
                                 period = Integer.parseInt((a.getValue().toString()));
                                 break;
                             case "Section":
                                 Section = a.getValue().toString();
                                 break;
                             case "Teacher":
                                 Teacher = a.getValue().toString();
                                 break;
                         }
                     }
                     Course.coursesL.add(new Course(CourseName,Classroom,Courselocal,period,Section,Teacher));
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { ;
            }
        });
        //If it is a speicalD, click on the button.
        final ToggleButton specialD=findViewById(R.id.toggleButton);
        specialD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(specialD.isChecked())
                    Day.specialDay=true;
                else
                    Day.specialDay=false;
            }
        });
        //read SpecialDay information from the database
        databaseReference.child("specialDay").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable <DataSnapshot> children =dataSnapshot.getChildren();
                for(DataSnapshot child:children){
                    int StartH=-1,StartM=-1,PeriodLength=-1;
                    String subjuct="";
                    //without the "if" statement, the program is going to stop because one of the key,"lunch", only have 5 characters.
                    if(child.getKey().length()>7) {
                        subjuct = child.getKey().substring(7);
                    }
                    for(DataSnapshot a:child.getChildren()) {
                        String temp=a.getKey();
                        switch(temp){
                            case "startH":
                                StartH=Integer.parseInt(a.getValue().toString());
                                break;
                            case"startM":
                                StartM=Integer.parseInt(a.getValue().toString());
                                break;
                            case"PeriodLength":
                                PeriodLength=Integer.parseInt(a.getValue().toString());
                                break;
                    }

                    }
                    //load information into an Arraylist initialized in the Period class
                    WTime Today=new WTime();
                    Period.specialD.add(new Period(new WTime(Today.getDay(),StartH,StartM),PeriodLength,subjuct));
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //The same method(the one for specialday) works for accessing periods from the database. The only thing needed to do is put periods info into database.
//        databaseReference.child("Periods").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Iterable <DataSnapshot> children =dataSnapshot.getChildren();
//                for(DataSnapshot child:children){
//                    for(DataSnapshot a:child.getChildren()) {
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyDrawable d=new MyDrawable();
        ImageView i = findViewById(R.id.imageView);
        i.setImageDrawable(d);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Period.isBWeek)
            Period.loadPeriodsB();
        else
            Period.loadPeriodsA();
    }

public class MyDrawable extends Drawable {

    public MyDrawable() {

    }
    //draw method for current and next period.
    @Override
    public void draw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);
        paint.setStrokeWidth(2);
        WTime today=new WTime();
        //Period.loadPeriodsA();
        Period current=Period.findContainingPeriod(today);
        Period next=Period.findNextPeriod(today);
        if(current!=null) {
            int LengthC = current.end.diffTicks(current.start) / 200;
            int LengthN = next.end.diffTicks(next.start) / 200;
            Rect currentP = new Rect(100, 20, 230, 20 + LengthC);
            Rect nextP = new Rect(100, 20 + LengthC + 20, 230, LengthC + 20 + LengthN);
            canvas.drawRect(currentP, paint);
            canvas.drawRect(nextP, paint);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(current.start.getHourAMPM() + ":" + current.start.getMinuteS(), 100, 38, paint);
            canvas.drawText(next.start.getHourAMPM() + ":" + next.start.getMinuteS(), 100, 58 + LengthC, paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(current.end.getHourAMPM() + ":" + current.end.getMinuteS(), 230, 15 + LengthC, paint);
            canvas.drawText(next.end.getHourAMPM() + ":" + next.end.getMinuteS(), 230, LengthC + 20 + LengthN, paint);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(current.subject, 165, (50+ LengthC) / 2, paint);
            canvas.drawText(current.getPeriod(), 165, (90+LengthC) / 2, paint);
            canvas.drawText(next.subject, 165, (50+ LengthC + LengthC + LengthN) / 2, paint);
            canvas.drawText(next.getPeriod(), 165, (90+ LengthC*2 + LengthN) / 2, paint);
        }
        else
        {
            //if there is no class now, the main Activity will indicate that.
            paint.setTextSize(50);
            paint.setStrokeWidth(4);
            canvas.drawText("No class now!",175,100,paint);
        }
//            int day=today.getDay();
//            WTime eight = new WTime(day,8,0);
//            ArrayList<Period> dayPeriods = Period.getTodaysPeriods(today.getDay());
//            int hour=today.getHour();
//            int min=today.getMinute();
//            int number = hour*60 + min;
//
    }
    @Override
    public void setAlpha(int alpha) {
        // This method is required
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        // This method is required
    }

    @Override
    public int getOpacity() {
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        return PixelFormat.OPAQUE;
    }
}
}
