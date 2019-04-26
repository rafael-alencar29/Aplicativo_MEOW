package com.example.meow;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

public class adotar extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<animals> animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adotar);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        animal = new ArrayList<animals>();
        animal.add(new animals("Charlie", "MACHO","PEQUENO", "BRASÍLIA - DF","ADULTO"));
        animal.add(new animals("Daisy", "FÊMEA", "MÉDIO", "CHARLOTTESVILLE - VA",
                "ADULTO"));
        animal.add(new animals("Lisa", "FÊMEA", "MÉDIO", "BRASÍLIA - DF",
                "ADULTO"));

        myAdapter = new animalAdapter(this,animal);
        recyclerView.setAdapter(myAdapter);

    }
}
