package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddWord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        Button buttonConfirmWord = findViewById(R.id.button_confirm_word);
        buttonConfirmWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu();
            }
        });
    }

    private void goToMenu() {
        Intent intent = new Intent(AddWord.this, MainActivity.class);
        startActivity(intent);
    }
}
