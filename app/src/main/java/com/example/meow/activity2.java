package com.example.meow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity2 extends AppCompatActivity {

    Button adotarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        adotarButton = findViewById(R.id.adotarButton);
        adotarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity2.this, com.example.meow.adotar.class);
                startActivity(intent);
            }
        });

    }
}
