package com.example.schedule;

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
    public static void sort(){
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
    public String toString(Course course){
        return course.CourseN+" "+course.Section+" "+course.Classroom+" "+course.Teacher;
    }
}
