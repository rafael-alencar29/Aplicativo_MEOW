package com.example.meow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity2 extends AppCompatActivity {

    Button cadastroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        cadastroButton = findViewById(R.id.cadastroButton);
        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity2.this, cadastroAnimal.class);
                startActivity(intent);
            }
        });

    }
}
