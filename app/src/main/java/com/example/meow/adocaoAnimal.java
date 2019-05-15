package com.example.meow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class adocaoAnimal extends AppCompatActivity {


     ImageView btn_backButton;
     Button btn_adotar;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adocao_animal);

        btn_backButton = findViewById(R.id.arrow_back);
        btn_adotar = findViewById(R.id.pretendoAdotar);

        btn_adotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Escrever oq fazer quando clicar no botao de pretendo Adotar */


                String IDinteressado = FirebaseAuth.getInstance().getUid();
            }
        });

        btn_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adocaoAnimal.this,adotar.class);
                startActivity(intent);
            }
        });
     }

}
