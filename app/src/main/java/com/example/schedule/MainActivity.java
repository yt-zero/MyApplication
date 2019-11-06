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
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public void weekPress(View view){
        Intent myIntent = new Intent(MainActivity.this, Week.class); //Create a new intent
        MainActivity.this.startActivity(myIntent);
    }

    public void settingPress(View view){
        Intent myIntent = new Intent(MainActivity.this, Settings.class); //Create a new intent
        MainActivity.this.startActivity(myIntent);
    }

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
        databaseReference.child("Course").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("info12","data changed");
                 Iterable <DataSnapshot> children =dataSnapshot.getChildren();
                 for(DataSnapshot child:children){
                     for(DataSnapshot a:child.getChildren()){
                         String CourseName=a.getValue().toString();
                         String Classroom=a.getValue().toString();
                         String Courselocal=a.getValue().toString();
                         int Period=Integer.parseInt(a.getValue().toString());
                         String Section=a.getValue().toString();
                         String Teacher=a.getValue().toString();
                         Course.coursesL.add(new Course(CourseName,Classroom,Courselocal,Period,Section,Teacher));
//                         String temp=a.getKey();
//                         Log.i("infor",a.getKey());
//                         Log.i("infor",""+a.getValue());
                     }
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
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
        //efficiency issue
        if(Period.isBWeek)
            Period.loadPeriodsB();
        else
            Period.loadPeriodsA();
    }

public class MyDrawable extends Drawable {

    public MyDrawable() {
        // Set up color and text size

    }
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
            int LengthC = current.end.diffTicks(current.start) / 100;
            int LengthN = next.end.diffTicks(next.start) / 100;
            Rect currentP = new Rect(450, 100, 580, 100 + LengthC);
            Rect nextP = new Rect(450, 100 + LengthC + 20, 580, LengthC + 120 + LengthN);
            canvas.drawRect(currentP, paint);
            canvas.drawRect(nextP, paint);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(current.start.getHourAMPM() + ":" + current.start.getMinuteS(), 450, 118, paint);
            canvas.drawText(next.start.getHourAMPM() + ":" + next.start.getMinuteS(), 450, 138 + LengthC, paint);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(current.end.getHourAMPM() + ":" + current.end.getMinuteS(), 580, 95 + LengthC, paint);
            canvas.drawText(next.end.getHourAMPM() + ":" + next.end.getMinuteS(), 580, LengthC + 115 + LengthN, paint);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(current.subject, 515, (200 + LengthC) / 2, paint);
            canvas.drawText(current.getPeriod(), 525, (200 + LengthC) / 2, paint);
            canvas.drawText(next.subject, 515, (240 + LengthC + LengthC + LengthN) / 2, paint);
            canvas.drawText(next.getPeriod(), 525, (240 + LengthC*2 + LengthN) / 2, paint);
        }
        else
        {
            paint.setTextSize(50);
            paint.setStrokeWidth(4);
            canvas.drawText("No class now!",380,300,paint);
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
