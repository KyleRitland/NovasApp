package com.example.novasapp;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Comparator;

public class eventObject {

    private String activity_type;
    private String notes;
    private int id;
    private int year;
    private int month;
    private int date;
    private int hour;
    private int minute;
    private float weight;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private String customData_1_name;
    private int customData_1_int;
    private float customData_1_float;
    private String customData_1_string;
    private String customData_2_name;
    private int customData_2_int;
    private float customData_2_float;
    private String customData_2_string;
    private String customData_3_name;
    private int customData_3_int;
    private float customData_3_float;
    private String customData_3_string;

    /*
    // Standard constructors
    */

    public eventObject() {

    }

    public eventObject(int id, String activity_type, int year, int month, int date, int hour, int minute) {
        this.id = id;
        this.activity_type = activity_type;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    public eventObject(int id, String activity_type, int year, int month, int date, int hour, int minute, float weight) {
        this.id = id;
        this.activity_type = activity_type;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.weight = weight;
    }

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute, float weight) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.weight = weight;
    }

    public eventObject(int id, String activity_type, int year, int month, int date, int hour, int minute, int sH, int sM, int eH, int eM) {
        this.id = id;
        this.activity_type = activity_type;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.startHour = sH;
        this.startMinute = sM;
        this.endHour = eH;
        this.endMinute = eM;

    }

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute, int sH, int sM, int eH, int eM) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.startHour = sH;
        this.startMinute = sM;
        this.endHour = eH;
        this.endMinute = eM;

    }

    /*
    // Constructors with: custom data = TRUE, duration length = FALSE
    */

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute, String s1, int i1, float f1, String s11) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;

        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;

        this.customData_1_name = s1;
        this.customData_1_int = i1;
        this.customData_1_float = f1;
        this.customData_1_string = s11;
    }

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute, String s1, int i1, float f1, String s11, String s2, int i2, float f2, String s21) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;

        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;

        this.customData_1_name = s1;
        this.customData_1_int = i1;
        this.customData_1_float = f1;
        this.customData_1_string = s11;
        this.customData_2_name = s2;
        this.customData_2_int = i2;
        this.customData_2_float = f2;
        this.customData_2_string = s21;
    }

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute, String s1, int i1, float f1, String s11, String s2, int i2, float f2, String s21, String s3, int i3, float f3, String s31) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;

        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;

        this.customData_1_name = s1;
        this.customData_1_int = i1;
        this.customData_1_float = f1;
        this.customData_1_string = s11;
        this.customData_2_name = s2;
        this.customData_2_int = i2;
        this.customData_2_float = f2;
        this.customData_2_string = s21;
        this.customData_3_name = s3;
        this.customData_3_int = i3;
        this.customData_3_float = f3;
        this.customData_3_string = s31;
    }

    /*
    // Constructors with: custom data = TRUE, duration length = TRUE
    */

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute, int sH, int sM, int eH, int eM, String s1, int i1, float f1, String s11) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;

        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;

        this.startHour = sH;
        this.startMinute = sM;
        this.endHour = eH;
        this.endMinute = eM;

        this.customData_1_name = s1;
        this.customData_1_int = i1;
        this.customData_1_float = f1;
        this.customData_1_string = s11;
    }

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute, int sH, int sM, int eH, int eM, String s1, int i1, float f1, String s11, String s2, int i2, float f2, String s21) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;

        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;

        this.startHour = sH;
        this.startMinute = sM;
        this.endHour = eH;
        this.endMinute = eM;

        this.customData_1_name = s1;
        this.customData_1_int = i1;
        this.customData_1_float = f1;
        this.customData_1_string = s11;
        this.customData_2_name = s2;
        this.customData_2_int = i2;
        this.customData_2_float = f2;
        this.customData_2_string = s21;
    }

    public eventObject(int id, String activity_type, String notes, int year, int month, int date, int hour, int minute, int sH, int sM, int eH, int eM, String s1, int i1, float f1, String s11, String s2, int i2, float f2, String s21, String s3, int i3, float f3, String s31) {
        this.id = id;
        this.activity_type = activity_type;
        this.notes = notes;

        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;

        this.startHour = sH;
        this.startMinute = sM;
        this.endHour = eH;
        this.endMinute = eM;

        this.customData_1_name = s1;
        this.customData_1_int = i1;
        this.customData_1_float = f1;
        this.customData_1_string = s11;
        this.customData_2_name = s2;
        this.customData_2_int = i2;
        this.customData_2_float = f2;
        this.customData_2_string = s21;
        this.customData_3_name = s3;
        this.customData_3_int = i3;
        this.customData_3_float = f3;
        this.customData_3_string = s31;
    }


    /*
    // toString
    */

    @Override
    public String toString() {
        return "eventsObject{" +
                "activity_type='" + activity_type + '\'' +
                ", notes='" + notes + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", date=" + date +
                ", hour=" + hour +
                ", minute=" + minute +
                ", weight=" + weight +
                '}';
    }

    /*
    / comparitors
    */

    public static Comparator<eventObject> dateComparatorASC = new Comparator<eventObject>() {
        @Override
        public int compare(eventObject e1, eventObject e2) {

            int[] intArr = {e1.getYear(),e2.getYear(),e1.getMonth(),e2.getMonth(),e1.getDate(),e2.getDate()};

            if (intArr[0] < intArr[1]) {
                return 1;
            } else if (intArr[0] > intArr[1]) {
                return -1;
            } else {
                if(intArr[2] < intArr[3]) {
                    return 1;
                } else if(intArr[2] > intArr[3]) {
                    return -1;
                } else {
                    if(intArr[4] < intArr[5]) {
                        return 1;
                    } else if(intArr[4] > intArr[5]) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    };

    public static Comparator<eventObject> dateComparatorDES = new Comparator<eventObject>() {
        @Override
        public int compare(eventObject e1, eventObject e2) {

            int[] intArr = {e1.getYear(),e2.getYear(),e1.getMonth(),e2.getMonth(),e1.getDate(),e2.getDate()};

            if (intArr[0] > intArr[1]) {
                return 1;
            } else if (intArr[0] < intArr[1]) {
                return -1;
            } else {
                if(intArr[2] > intArr[3]) {
                    return 1;
                } else if(intArr[2] < intArr[3]) {
                    return -1;
                } else {
                    if(intArr[4] > intArr[5]) {
                        return 1;
                    } else if(intArr[4] < intArr[5]) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    };

    public static Comparator<eventObject> timeComparatorASC = new Comparator<eventObject>() {
        @Override
        public int compare(eventObject e1, eventObject e2) {
            int[] intArr = {e1.getHour(),e2.getHour(),e1.getMinute(),e2.getMinute()};

            if (intArr[0] < intArr[1]) {
                return 1;
            } else if (intArr[0] > intArr[1]) {
                return -1;
            } else {
                if (intArr[2] < intArr[3]) {
                    return 1;
                } else if (intArr[2] > intArr[3]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    };

    public static Comparator<eventObject> timeComparatorDES = new Comparator<eventObject>() {
        @Override
        public int compare(eventObject e1, eventObject e2) {
            int[] intArr = {e1.getHour(),e2.getHour(),e1.getMinute(),e2.getMinute()};

            if (intArr[0] > intArr[1]) {
                return 1;
            } else if (intArr[0] < intArr[1]) {
                return -1;
            } else {
                if (intArr[2] > intArr[3]) {
                    return 1;
                } else if (intArr[2] < intArr[3]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    };

    public static Comparator<eventObject> idComparator = new Comparator<eventObject>() {
        @Override
        public int compare(eventObject e1, eventObject e2) {

            if (e1.getIdInt() < e2.getIdInt()) {
                return 1;
            } else {
                return -1;
            }
        }
    };


    // custom getters

    public String getFullDate(Context c) {

        int n = month;
        String s = "";
        String[] sArr = c.getResources().getStringArray(R.array.months);

        for (int i = 0; i < sArr.length; i++ ) {
            if(i == n) {
                s = sArr[i];
                break;
            }
        }

        return s + " " + Integer.toString(date) + ", " + Integer.toString(year);
    }

    public String getFullTime() {

        Calendar c1 = Calendar.getInstance();
        c1.set(0,0,0,hour,minute);

        CharSequence cS = DateFormat.format("h:mm a",c1);

        return cS.toString();
    }

    public String getDuration() {

        int durH = endHour - startHour;
        int durM = endMinute - startMinute;

        return durH + " hours, " + durM + " minutes";
    }

    //default getters and setters

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getIdInt() {
        return id;
    }

    public String getIdStr() {
        return Integer.toString(id);
    }

}
