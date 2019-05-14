package com.example.meow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class adocaoAnimal extends AppCompatActivity {


     ImageView btn_backButton;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adocao_animal);

        btn_backButton = findViewById(R.id.arrow_back);

        btn_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adocaoAnimal.this,adotar.class);
                startActivity(intent);
            }
        });
     }

}
