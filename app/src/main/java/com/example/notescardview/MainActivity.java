package com.example.notescardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final ArrayList<Note> notes = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        if(notes.isEmpty()) {
            notes.add(new Note("первое название", "первое описание", "Понедельник", 2));
            notes.add(new Note("второе название", "второе описание", "вторник", 1));
            notes.add(new Note("третье название", "третье описание", "среда", 3));
        }
//        notes.add(new Note("четвертое название","четвертое описание", "четверг",2));
//        notes.add(new Note("первое название","первое описание", "Понедельник",2));
//        notes.add(new Note("второе название","второе описание", "вторник",1));
//        notes.add(new Note("третье название","третье описание", "среда",3));
//        notes.add(new Note("четвертое название","четвертое описание", "четверг",2));
//        notes.add(new Note("первое название","первое описание", "Понедельник",2));
//        notes.add(new Note("второе название","второе описание", "вторник",1));
//        notes.add(new Note("третье название","третье описание", "среда",3));
//        notes.add(new Note("четвертое название","четвертое описание", "четверг",2));

        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);






    }

    public void onClickButtonPressed(View view) {
        Intent intent = new Intent(this,AddNewNote.class);
        startActivity(intent);
    }
}