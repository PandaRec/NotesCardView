package com.example.notescardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    ArrayList <Note> notes;

    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_activity,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewTitle.setText(note.getTitle());
        holder.textViewDescription.setText(note.getDescription());
        holder.textViewDayOfWeek.setText(String.format(" %s",note.getDayOfWeek()));
        //holder.textViewPriority.setText(String.format("%s",note.getPriority()));
        switch (note.getPriority()){
            case 1:
                holder.textViewTitle.setBackgroundColor(holder.itemView.getResources().getColor(R.color.red));
                holder.textViewPriority.setText(String.format("Приоритет - %s",holder.itemView.getResources().getString(R.string.priority_high)));
                break;
            case 2:
                holder.textViewTitle.setBackgroundColor(holder.itemView.getResources().getColor(R.color.orange));
                holder.textViewPriority.setText(String.format("Приоритет - %s",holder.itemView.getResources().getString(R.string.priority_middle)));
                break;
            case 3:
                holder.textViewTitle.setBackgroundColor(holder.itemView.getResources().getColor(R.color.green));
                holder.textViewPriority.setText(String.format("Приоритет - %s",holder.itemView.getResources().getString(R.string.priority_low)));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewDayOfWeek;
        private TextView textViewPriority;
        //private TextView getTextViewDayOfWeekIs;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek);
            textViewPriority = itemView.findViewById(R.id.textViewPriority);
            //getTextViewDayOfWeekIs = itemView.findViewById(R.id.textViewDayIs);

        }
    }
}
