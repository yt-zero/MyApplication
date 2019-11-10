package com.example.schedule;

import android.util.Log;

import java.util.ArrayList;

public class Course {
    String CourseN,Classroom,Course,Section,Teacher;
    int period;
    public static ArrayList<Course> coursesL = new ArrayList<Course>();
    public static ArrayList<Course> p1course = new ArrayList<Course>();
    public static ArrayList<Course> p2course = new ArrayList<Course>();
    public static ArrayList<Course> p3course = new ArrayList<Course>();
    public static ArrayList<Course> p4course = new ArrayList<Course>();
    public static ArrayList<Course> p5course = new ArrayList<Course>();
    public static ArrayList<Course> p6course = new ArrayList<Course>();
    public static ArrayList<Course> p7course = new ArrayList<Course>();


    public Course(String CourseN, String Classroom, String Course,int period, String Section, String Teacher){
        this.CourseN=CourseN;
        this.Classroom=Classroom;
        this.Course=Course;
        this.period=period;
        this.Section=Section;
        this.Teacher=Teacher;
    }
    //sort courseL into different Array List. If a course take place in period 1, it will be added to p1course. If a course occur in period 2, it will be added to p2course, and so on.
    public static void sort(){
        //clear each ArrayList, otherwise a new copy of course information will be create each time when program open setting activity. namely, there will be a lots of duplication of the same course in the autoComplete.
        //if it is too much work for the device. you can also create a new variable. if it is the first time, the program enter setting, sort the List. if it is the second or more times, skip this part.
        clearList();
        for(Course a:coursesL){
           switch(a.period){
               case 1:
                   p1course.add(a);
                   break;
               case 2:
                   p2course.add(a);
                   break;
               case 3:
                   p3course.add(a);
                   break;
               case 4:
                   p4course.add(a);
                   break;
               case 5:
                   p5course.add(a);
                   break;
               case 6:
                   p6course.add(a);
                   break;
               case 7:
                   p7course.add(a);
                   break;

           }
            }

    }
    public String toString(){
        return CourseN+" "+Section+" "+Classroom+" "+Teacher;
    }

    public static void clearList(){
    p1course.clear();
    p2course.clear();
    p3course.clear();
    p4course.clear();
    p5course.clear();
    p6course.clear();
    p7course.clear();
    }
}
