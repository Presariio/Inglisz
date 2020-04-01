package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button zatwierdz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //przycisk do menu
        final Button przyciskZatwierdz = findViewById(R.id.zatwierdz);
        przyciskZatwierdz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "zatwierdzone", Toast.LENGTH_SHORT).show();
                openMenu();
            }

        }
        );

    }
    public void openMenu(){
        Intent intentMenu = new Intent(this, menu.class);
        startActivity(intentMenu);
    }

}
