package com.example.novasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class editAddEvent extends AppCompatActivity {

    Button btn_date, btn_time, btn_accept, btn_cancel, btn_del;
    eventObject editOrNewEvent,originalEvent;
    TextView event_id,date,time,eventType,notes;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add_event);

        editOrNewEvent = new eventObject();

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);

        eventType = findViewById(R.id.event_type_text_2);
        date = findViewById(R.id.eventDateTextDisplay);
        time = findViewById(R.id.eventTImeDisplayText);
        notes = findViewById(R.id.add_notes_field);

        btn_date = findViewById(R.id.set_date_btn);
        btn_time = findViewById(R.id.set_time_btn);
        btn_accept = findViewById(R.id.accept_btn);
        btn_cancel = findViewById(R.id.cancel_btn);
        btn_del = findViewById(R.id.delete_btn);

        if (id >= 0){
            database db = new database(editAddEvent.this);
            ArrayList<eventObject> events = db.getAllEvents();
            for (eventObject event : events) {
                if(event.getIdInt() == id) {

                    originalEvent = event;

                    date.setText(event.getFullDate(editAddEvent.this));
                    time.setText(event.getFullTime()); ;
                    eventType.setText(event.getActivity_type());

                    break;

                }
            }



        }

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int[] arr = {cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)};
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(editAddEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker2, int j, int j1, int j2) {

                        editOrNewEvent.setYear(j);
                        editOrNewEvent.setMonth(j1);
                        editOrNewEvent.setDate(j2);
                        time.setText(editOrNewEvent.getFullDate(editAddEvent.this));

                    }
                },arr[0],arr[1],arr[2]);

            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();

                int[] times = {0,0,0,0,cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)};

                TimePickerDialog timePickerDialog = new TimePickerDialog(editAddEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        if(i > times[4] || (i == times[4] && i1 > times[5]) ){
                            Toast.makeText(editAddEvent.this,"You can't select a future time.",Toast.LENGTH_SHORT).show();
                        } else {
                            editOrNewEvent.setHour(i);
                            editOrNewEvent.setMinute(i1);
                            time.setText(editOrNewEvent.getFullTime());
                        }

                    }
                },times[4],times[5], false);
                timePickerDialog.show();
            }
        });

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database db = new database(editAddEvent.this);
                db.editOne(originalEvent,editOrNewEvent,originalEvent.getIdInt());
                Intent intent = new Intent(editAddEvent.this, MainActivity.class);
                editAddEvent.this.startActivity(intent);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editAddEvent.this, MainActivity.class);
                editAddEvent.this.startActivity(intent);
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database db = new database(editAddEvent.this);
                db.deleteOne(id);

                Intent intent = new Intent(editAddEvent.this, MainActivity.class);
                editAddEvent.this.startActivity(intent);

            }
        });

    }
}