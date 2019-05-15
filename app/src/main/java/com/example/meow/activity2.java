package com.example.meow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class activity2 extends AppCompatActivity {

    Button cadastroButton;
    Button adotarButton;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);


        adotarButton = findViewById(R.id.adotarButton);
        adotarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Verifica se o usuario estah logado, caso nao estaja uma tela
                 *   cadastro de pessoa eh aberto
                 *   onde o login podera ser feito*/
                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                    Intent intent = new Intent(activity2.this, com.example.meow.adotar.class);
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(activity2.this, cadastroPessoa.class);
                    startActivity(intent);
                }

            }
        });

        cadastroButton = findViewById(R.id.CADASTRAR);
        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /* Verifica se o usuario estah logado, caso nao estaja uma tela
                *   cadastro de pessoa eh aberto
                *   onde o login podera ser feito*/
                if(FirebaseAuth.getInstance().getCurrentUser() != null) {

                    Intent intent = new Intent(activity2.this, cadastroAnimal.class);
                    startActivity(intent);

                }else {
                   Intent intent = new Intent(activity2.this, cadastroPessoa.class);
                   startActivity(intent);
                }
            }
        });

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity2.this, com.example.meow.login.class);
                startActivity(intent);
            }
        });
    }

}
