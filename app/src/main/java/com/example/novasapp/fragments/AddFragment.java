package com.example.novasapp.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.novasapp.R;
import com.example.novasapp.database;
import com.example.novasapp.eventObject;

import java.util.Calendar;
import java.util.Date;

public class AddFragment extends Fragment {

    public AddFragment() {
        // Required empty public constructor
    }


    private LinearLayout log_lin_Layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add, container, false);

        // log_lin_Layout = findViewById(R.id.log_linearLayout);

        ImageButton img1 = (ImageButton) v.findViewById(R.id.treat);
        ImageButton img2 = (ImageButton) v.findViewById(R.id.poop);
        ImageButton img3 = (ImageButton) v.findViewById(R.id.meal);
        ImageButton img4 = (ImageButton) v.findViewById(R.id.pee);

        if( img1 != null) {
            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createNewLog(img1.getId());
                }

            });
        }

        if( img2 != null) {
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createNewLog(img2.getId());
                }
            });
        }

        if( img3 != null) {
            img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createNewLog(img3.getId());
                }
            });
        }

        if( img4 != null) {
            img4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createNewLog(img4.getId());
                }
            });
        }

        return v;

    }

    public View.OnClickListener createNewLog(int id) {

        Calendar cal = Calendar.getInstance();
        Calendar calFinal = Calendar.getInstance();

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);



        TimePickerDialog timePickerDialog = new TimePickerDialog(AddFragment.this.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                Calendar temp1 = Calendar.getInstance();
                temp1.set(Calendar.HOUR_OF_DAY,i);
                temp1.set(Calendar.MINUTE,i1);

                Calendar temp2 = Calendar.getInstance();
                temp2.set(Calendar.HOUR_OF_DAY,hour);
                temp2.set(Calendar.MINUTE,min);


                if(i > hour || (i==hour && i1 > min)) {
                    Toast.makeText(AddFragment.this.getContext(),"You can't select a future time",Toast.LENGTH_SHORT).show();

                } else {
                    calFinal.set(Calendar.HOUR_OF_DAY,i);
                    calFinal.set(Calendar.MINUTE,i1);
                    runSwitch(id, calFinal);
                }
            }
        },hour,min, false);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddFragment.this.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calFinal.set(Calendar.YEAR,i);
                calFinal.set(Calendar.MONTH,i1);
                calFinal.set(Calendar.DATE,i2);
                timePickerDialog.show();
            }
        },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));

        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());

        datePickerDialog.show();





        return null;
    }

    public void runSwitch(int id, Calendar calF) {
        Toast.makeText(AddFragment.this.getContext(), "Switch Ran", Toast.LENGTH_SHORT).show();
        String act_type = "";

        if(id == R.id.treat) {
            act_type = "Treat";
        } else if (id == R.id.poop) {
            act_type = "Poop";
        } else if (id == R.id.meal) {
            act_type = "Meal";
        } else if (id == R.id.pee) {
            act_type = "Pee";
        }

        if(act_type != "") {

            eventObject pup;
            try {
                pup = new eventObject(id,
                        act_type,
                        calF.get(Calendar.YEAR),
                        calF.get(Calendar.MONTH),
                        calF.get(Calendar.DATE),
                        calF.get(Calendar.HOUR_OF_DAY),
                        calF.get(Calendar.MINUTE)
                );
                Toast.makeText(AddFragment.this.getContext(), pup.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                pup = new eventObject(-1,
                        "FAIL",
                        0,
                        0,
                        0,
                        0,
                        0
                );
                Toast.makeText(AddFragment.this.getContext(), "object creation failed", Toast.LENGTH_SHORT).show();
            }

            database db = new database(AddFragment.this.getContext());

            boolean success = db.addOne(pup);
            Toast.makeText(AddFragment.this.getContext(), "Activity logged = " + success, Toast.LENGTH_LONG).show();

            db.close();

        } else {
            Toast.makeText(AddFragment.this.getContext(), "All cases failed", Toast.LENGTH_SHORT).show();
        }

    }

}