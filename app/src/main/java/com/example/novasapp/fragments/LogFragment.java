package com.example.novasapp.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.novasapp.R;
import com.example.novasapp.database;
import com.example.novasapp.eventsAdapter;
import com.example.novasapp.eventObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LogFragment extends Fragment {

    public LogFragment() {
        // Required empty public constructor
    }

    database db, dbP;
    RecyclerView r_view;
    TextView nv_menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_log, container, false);

        nv_menu = v.findViewById(R.id.log_filter_menu_tv);
        nv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(LogFragment.this.getContext(),nv_menu);

                popupMenu.inflate(R.menu.log_filter_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem mI) {
                        dbP = new database(LogFragment.this.getContext());
                        int id_n = mI.getItemId();

                        if (id_n == R.id.filter_show_all) {
                            Toast.makeText(LogFragment.this.getContext(), "Show Events hit", Toast.LENGTH_SHORT).show();
                            showEvents(dbP);
                            //Toast.makeText(LogFragment.this.getContext(), dbP.toString(), Toast.LENGTH_SHORT).show();

                        } else if (id_n == R.id.filter_date) {
                            Toast.makeText(LogFragment.this.getContext(), "Date Range hit", Toast.LENGTH_SHORT).show();
                            showFilteredDates(dbP);

                        } else if (id_n == R.id.filter_time) {
                            Toast.makeText(LogFragment.this.getContext(), "Time Range hit", Toast.LENGTH_SHORT).show();
                            showFilteredTimes(dbP);

                        } else if (id_n == R.id.filter_act) {
                                Toast.makeText(LogFragment.this.getContext(), "Filter Activities hit", Toast.LENGTH_SHORT).show();
                                showFilteredEvents(dbP,"poop");
                        }

                        dbP.close();
                        return false;
                    }
                });

                popupMenu.show();
            }
        });


        r_view = v.findViewById(R.id.log_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LogFragment.this.getContext());
        r_view.setLayoutManager(linearLayoutManager);
        r_view.setHasFixedSize(true);

        db = new database(LogFragment.this.getContext());
        showEvents(db);
        db.close();

        return v;
    }



    private void showEvents(database db) {

        ArrayList<eventObject> allEvents = db.getAllEvents();

        if (allEvents.size() > 0) {
            r_view.setVisibility(View.VISIBLE);
            eventsAdapter mAdapter = new eventsAdapter(LogFragment.this.getContext(), allEvents);
            r_view.setAdapter(mAdapter);
        }
        else {
            r_view.setVisibility(View.GONE);
            Toast.makeText(LogFragment.this.getContext(), "There are no events in the database. Start adding now", Toast.LENGTH_LONG).show();
        }

    }

    private void showFilteredEvents(database db, String filter) {

        ArrayList<eventObject> allEvents = db.getAllEvents();

        if (allEvents.size() > 0) {

            eventsAdapter mAdapter = new eventsAdapter(LogFragment.this.getContext(), allEvents);

            mAdapter.getActTypeFiltered(filter);
            r_view.setVisibility(View.VISIBLE);

            r_view.setAdapter(mAdapter);


        }
        else {
            r_view.setVisibility(View.GONE);
            Toast.makeText(LogFragment.this.getContext(), "There are no events in the database. Start adding now", Toast.LENGTH_LONG).show();
        }

    }

    private void showFilteredDates(database db) {

        ArrayList<eventObject> allEvents = db.getAllEvents();

        if (allEvents.size() > 0) {

            Calendar cal = Calendar.getInstance();
            long date = new Date().getTime();
            final int[] intArr = {0,0,0,0,0,0,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),-1};
            int[] intArr2 = {0,0,0,0,0,0};

            DatePickerDialog datePickerDialog = new DatePickerDialog(LogFragment.this.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                   intArr[0] = i; intArr[2] = i1; intArr[4] = i2;
                    Toast.makeText(LogFragment.this.getContext(), "first date chosed", Toast.LENGTH_SHORT).show();
                    DatePickerDialog datePickerDialog2 = new DatePickerDialog(LogFragment.this.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker2, int j, int j1, int j2) {

                            intArr[1] = j; intArr[3] = j1; intArr[5] = j2;
                            intArr2[0] = intArr[1]; intArr2[1] = intArr[0]; intArr2[2] = intArr[3]; intArr2[3] = intArr[2]; intArr2[4] = intArr[5]; intArr2[5] = intArr[4];
                            Toast.makeText(LogFragment.this.getContext(), "second date chosen", Toast.LENGTH_SHORT).show();

                            if(intArr[0] < intArr[1]) {
                                intArr[9] = 0;
                            } else if(intArr[0] > intArr[1]) {
                                intArr[9] = 1;
                            } else {
                                if(intArr[2] < intArr[3]) {
                                    intArr[9] = 0;
                                } else if(intArr[2] > intArr[3]){
                                    intArr[9] = 1;
                                } else {
                                    if(intArr[4] < intArr[5]) {
                                        intArr[9] = 0;
                                    } else if(intArr[4] > intArr[5]) {
                                        intArr[9] = 1;
                                    } else {
                                        intArr[9] = -1;
                                    }
                                }
                            }

                            if (intArr[9] == 0){

                                eventsAdapter mAdapter = new eventsAdapter(LogFragment.this.getContext(), allEvents);
                                mAdapter.getDatesFiltered(intArr2);
                                r_view.setVisibility(View.VISIBLE);
                                r_view.setAdapter(mAdapter);
                                Toast.makeText(LogFragment.this.getContext(), "intArr used", Toast.LENGTH_SHORT).show();
                            } else if (intArr[9] == 1) {

                                eventsAdapter mAdapter = new eventsAdapter(LogFragment.this.getContext(), allEvents);
                                mAdapter.getDatesFiltered(intArr);
                                r_view.setVisibility(View.VISIBLE);
                                r_view.setAdapter(mAdapter);
                                Toast.makeText(LogFragment.this.getContext(), "intArr2 used", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(LogFragment.this.getContext(), "Date filter error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },intArr[6],intArr[7],intArr[8]);
                    datePickerDialog2.show();
                    datePickerDialog2.getDatePicker().setMaxDate(date);
                }
            },intArr[6],intArr[7],intArr[8]);
            datePickerDialog.show();
            datePickerDialog.getDatePicker().setMaxDate(date);


        }
        else {
            r_view.setVisibility(View.GONE);
            Toast.makeText(LogFragment.this.getContext(), "There are no events in the database. Start adding now", Toast.LENGTH_LONG).show();
        }



    }

    public void showFilteredTimes(database db) {

        ArrayList<eventObject> allEvents = db.getAllEvents();

        if (allEvents.size() > 0) {

            Calendar cal = Calendar.getInstance();

            final int[] times = {0,0,0,0,cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE)};

            TimePickerDialog timePickerDialog = new TimePickerDialog(LogFragment.this.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {

                    times[0] = i;
                    times[1] = i1;

                    TimePickerDialog timePickerDialog = new TimePickerDialog(LogFragment.this.getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int j, int j1) {

                            times[2] = j;
                            times[3] = j1;

                            if(times[0] < times[1]) {

                            } else if (times[0] > times[1]) {

                            } else {

                                if (times[2] < times[3]) {

                                } else if (times[2] > times[3]) {

                                } else {
                                    Toast.makeText(LogFragment.this.getContext(),"You can't make the dates the same. Please select again.",Toast.LENGTH_LONG).show();
                                }


                            }

                        }
                    },times[4],times[5], false);

                }
            },cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE), false);

            //******* fix here

            eventsAdapter mAdapter = new eventsAdapter(LogFragment.this.getContext(), allEvents);

            //mAdapter.getActTypeFiltered(filter);
            r_view.setVisibility(View.VISIBLE);

            r_view.setAdapter(mAdapter);


        }
        else {
            r_view.setVisibility(View.GONE);
            Toast.makeText(LogFragment.this.getContext(), "There are no events in the database. Start adding now", Toast.LENGTH_LONG).show();
        }



    }
}