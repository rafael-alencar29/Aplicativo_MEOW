package com.example.meow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class activity2 extends AppCompatActivity {

    Button cadastroButton;
    Button adotarButton;
    TextView textologin;

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
        cadastroButton = findViewById(R.id.CADASTRAR);
        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity2.this, cadastroAnimal.class);
                startActivity(intent);
            }
        });

        textologin = findViewById(R.id.login);
        textologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity2.this, com.example.meow.login.class);
                startActivity(intent);
            }
        });
    }

}
