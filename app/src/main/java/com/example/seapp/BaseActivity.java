package com.example.seapp;
/*
public class BaseActivity {
    public static int breakfast = 0;
    public static int morning = 0;

}*/

public class BaseActivity {

    private static BaseActivity singleton = new BaseActivity( );

    private BaseActivity() { }

    public static BaseActivity getInstance( ) {
        return singleton;
    }

    protected static int breakfast = 0;
    protected static int calmorning = 0;
    protected static int lunch = 0;
    protected static int evening = 0;
    protected static int dinner = 0;
    protected static int total = 0;
    protected static int BMI = 0;
    protected static int weight = 0;
    protected static int height = 0;

    protected static String uID = "";


    protected static String active = "";

    protected static void changeBreakfast( int i) {
        breakfast += i;
    }

    protected static void changeMorning(int i) {
        calmorning += i;
    }

    protected static void changeLunch(int i) {
        lunch += i;
    }

    protected static void changeEvening(int i) {
        evening += i;
    }

    protected static void changeDinner(int i) {
        dinner += i;
    }

    protected static void changeStatus(String s) {
        active = s;
    }

    protected static void changeuID(String s) {
        uID = s;
    }

    protected static void changeBMI(int i) {
        BMI = i;
    }

    protected static void changeWeight(int i) {
        weight = i;
    }

    protected static void changeHeight(int i) {
        height = i;
    }


    protected static void calculateTotal() {
        total = breakfast + calmorning + lunch + evening + dinner;
    }


}