package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add_word extends AppCompatActivity {

    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_word);


        //przycisk do dodawania slowek
        final Button przyciskAdd_2 = findViewById(R.id.add);
        przyciskAdd_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Słówko zostało dodane.", Toast.LENGTH_SHORT).show();
                openMenu();
            }

        }
        );

    }


    public void openMenu(){
        Intent intentMenu_2 = new Intent(this, menu.class);
        startActivity(intentMenu_2);
    }

}
