package com.example.notescardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddNewNote extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDaOfWeek;
    private RadioGroup radioGroup;

    private String newTitle;
    private String newDescription;
    private String newDayOfWeek;
    private int newPriority;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);



        editTextTitle = findViewById(R.id.editTextAddTitle);
        editTextDescription = findViewById(R.id.editTextAddDescription);
        spinnerDaOfWeek = findViewById(R.id.spinnerAddDayOfWeek);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);





        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonHigh:
                        Log.i("radio","high");
                        newPriority=1;
                        break;
                    case R.id.radioButtonMiddle:
                        Log.i("radio","middle");
                        newPriority=2;
                        break;
                    case R.id.radioButtonLow:
                        Log.i("radio","low");
                        newPriority=3;
                        break;
                }

            }
        });


    }

    public void onClickButtonAddPressed(View view) {

        newTitle=editTextTitle.getText().toString();
        newDescription = editTextDescription.getText().toString();
        newDayOfWeek = spinnerDaOfWeek.getSelectedItem().toString();

        Note note = new Note(newTitle,newDescription,newDayOfWeek,newPriority);
        mainViewModel.insertNote(note);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}