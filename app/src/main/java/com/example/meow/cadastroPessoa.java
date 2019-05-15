package com.example.meow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cadastroPessoa extends AppCompatActivity {
    Button btn_go_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        btn_go_login = findViewById(R.id.go_login);

        btn_go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( cadastroPessoa.this,login.class);
                startActivity(intent);
            }
        });
    }
}
