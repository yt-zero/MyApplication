package com.example.schedule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class Week extends AppCompatActivity {
    public class MyView extends View {

        public MyView(Context context)
        {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas) {
            Paint paint=new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setColor(Color.BLACK);
//            if(Period.isBWeek)
//                Period.loadPeriodsB();
//            else
//                Period.loadPeriodsA();
            Period.drawPeriods(canvas);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

}
