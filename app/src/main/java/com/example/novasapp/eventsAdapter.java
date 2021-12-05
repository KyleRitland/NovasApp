package com.example.novasapp;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class eventsAdapter extends RecyclerView.Adapter<eventsViewHolder>

    implements Filterable {

    private Context context;
    private ArrayList<eventObject> listEvents;
    private ArrayList<eventObject> mArrayList;
    private database mDatabase;

    public eventsAdapter(Context context, ArrayList<eventObject> listEvents) {
        this.context = context;
        this.listEvents = listEvents;
        this.mArrayList = listEvents;
        mDatabase = new database(context);
    }

    @Override
    public eventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_view_layout, parent, false);
        return new eventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(eventsViewHolder holder, int position) {
        final eventObject events = listEvents.get(position);

        holder.event_type.setText(events.getActivity_type());
        holder.event_date.setText(events.getFullDate(context));
        holder.event_time.setText(events.getFullTime());
        holder.event_id.setText(events.getIdStr());
        //holder.event_notes.setText(events.getNotes());
        //holder.eventPic.setImageDrawable(events.getImg());
        holder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editTaskDialog(listEvents);
                Intent intent = new Intent(context,editAddEvent.class);
                intent.putExtra("id",events.getIdInt());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listEvents = mArrayList;
                } else {
                    ArrayList<eventObject> filteredList = new ArrayList<>();
                    for (eventObject event: mArrayList) {
                        if (event.getActivity_type().toLowerCase().contains(charString)) {
                            filteredList.add(event);
                        }
                    }
                    listEvents = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listEvents;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listEvents = (ArrayList<eventObject>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public ArrayList<eventObject> getActTypeFiltered(String act_type) {

        if (act_type.isEmpty()) {
            listEvents = mArrayList;
        } else {
            ArrayList<eventObject> filteredList = new ArrayList<>();
            for (eventObject event: mArrayList) {
                if (event.getActivity_type().toLowerCase().equals(act_type)) {
                    filteredList.add(event);
                }
            }
            listEvents = filteredList;
        }

        return listEvents;
    }

    public ArrayList<eventObject> getDatesFiltered(int[] times) {

        if (times.length == 0) {
            listEvents = mArrayList;
        } else {
            ArrayList<eventObject> filteredList = new ArrayList<>();
            for (eventObject event: mArrayList) {
                int[] arr1 = {event.getYear(),event.getMonth(),event.getDate()};
                boolean[] tF = {arr1[0] <= times[0] && arr1[0] >= times[1],
                                arr1[1] <= times[2] && arr1[1] >= times[3],
                                arr1[2] <= times[4] && arr1[2] >= times[5]};

                if (tF[0] && tF[1] && tF[2]) {
                    filteredList.add(event);
                } else {
                    //error
                }
            }
            listEvents = filteredList;
        }

        return listEvents;
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    /*
    private void editTaskDialog(final puppyModel event) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_contacts, null);
        final EditText nameField = subView.findViewById(R.id.enterName);
        final EditText contactField = subView.findViewById(R.id.enterPhoneNum);
        if (contacts != null) {
            nameField.setText(contacts.getName());
            contactField.setText(String.valueOf(contacts.getPhno()));
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit contact");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("EDIT CONTACT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final String ph_no = contactField.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(context, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                } else {
                    mDatabase.updateContacts(new
                            Contacts(Objects.requireNonNull(contacts).getId(), name, ph_no));
                    ((Activity) context).finish();
                    context.startActivity(((Activity)
                            context).getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
    */
}
