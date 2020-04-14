package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {

private Button dodajS;
private Button informacje;
private Button quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //przycisk do informacji
        final Button przyciskInformacje = findViewById(R.id.informacje);
        przyciskInformacje.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openInformation();
            }

        }
        );

        //przycisk do dodawania slowek
        final Button przyciskAdd = findViewById(R.id.dodajS);
        przyciskAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAdd();
            }

        }
        );

        //przycisk do quizu
        final Button przyciskQuiz = findViewById(R.id.quiz);
        przyciskQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openQuiz();
            }

        }
        );
    }


    private void openInformation(){
        Intent intentInformation = new Intent(this, information.class);
        startActivity(intentInformation);
    }

    private void openAdd(){
        Intent intentAdd = new Intent(this, add_word.class);
        startActivity(intentAdd);
    }

    private void openQuiz(){
        Intent intentQuiz = new Intent(this, quiz.class);
        startActivity(intentQuiz);
    }
}
