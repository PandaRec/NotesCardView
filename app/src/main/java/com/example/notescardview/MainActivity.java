package com.example.notescardview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final ArrayList<Note> notes = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private NotesDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        dbHelper = new NotesDBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Log.i("f","f");

        if(notes.isEmpty()) {
            notes.add(new Note("первое название", "первое описание", "Понедельник", 2));
            notes.add(new Note("второе название", "второе описание", "вторник", 1));
            notes.add(new Note("третье название", "третье описание", "среда", 3));
        }
/*
        for(Note note:notes){
            ContentValues contentValues = new ContentValues();
            contentValues.put(NotesContract.NoteEntry.COLUMN_TITLE,note.getTitle());
            contentValues.put(NotesContract.NoteEntry.COLUMN_DESCRIPTION,note.getDescription());
            contentValues.put(NotesContract.NoteEntry.COLUMN_DAY_OF_WEEK,note.getDayOfWeek());
            contentValues.put(NotesContract.NoteEntry.COLUMN_PRIORITY,note.getPriority());
            database.insert(NotesContract.NoteEntry.TABLE_NAME,null,contentValues);
        }

        Cursor cursor = database.query(NotesContract.NoteEntry.TABLE_NAME,null,null,null,null,null,null);
        ArrayList<Note> notesDB = new ArrayList<>();
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_TITLE));
            String descr = cursor.getString(cursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_DESCRIPTION));
            String dayOfWeek = cursor.getString(cursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_DAY_OF_WEEK));
            int priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_PRIORITY));
            notesDB.add(new Note(title,descr,dayOfWeek,priority));
        }
        cursor.close();
        */


        adapter = new NotesAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongNoteClick(int position) {
                Toast.makeText(MainActivity.this, "long "+position, Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                removeItem(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }
    private void removeItem(int position){
        notes.remove(position);
        adapter.notifyDataSetChanged();
    }

    public void onClickButtonPressed(View view) {
        Intent intent = new Intent(this,AddNewNote.class);
        startActivity(intent);
    }
}