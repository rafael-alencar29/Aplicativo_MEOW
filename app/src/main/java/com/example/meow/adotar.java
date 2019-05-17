package com.example.meow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adotar extends AppCompatActivity implements animalAdapter.OnItemListener {

    private static final String TAG = "ViewDataBase";
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuhlistener;


    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference myRef;

    private ListView mListView;
    private String userID;


    ImageView imageDog;
    ArrayList<animals> animal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adotar);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        mFireBaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFireBaseDatabase.getReference();
        userID = user.getUid();


        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        animal = new ArrayList<animals>();
        animal.add(new animals("Charlie", "MACHO","PEQUENO", "BRASÍLIA - DF","ADULTO"));
        animal.add(new animals("Dog", "Macho", "Pequeno", "Brasília-DF",
                "Filhote"));
        animal.add(new animals("Daisy", "FÊMEA", "MÉDIO", "Charlottesville",
                    "ADULTO"));
        animal.add(new animals("Lisa", "FÊMEA", "MÉDIO", "BRASÍLIA - DF",
                "ADULTO"));
        animal.add(new animals("Daisy 2", "FÊMEA", "MÉDIO", "Charlottesville",
                "ADULTO"));



        myAdapter = new animalAdapter(this,animal,this);
        recyclerView.setAdapter(myAdapter);

    }

    private void showData(DataSnapshot dataSnapshot) {
        /*

            String id = (String) dataSnapshot.getKey();
        for (DataSnapshot ds: dataSnapshot.getChildren()){
            Animal ani = new Animal();



            ani.setDoencas(ds.child(id).getValue(Animal.class).getDoencas());
            ani.setEndereco(ds.child(id).getValue(Animal.class).getEndereco());
            ani.setIdade(ds.child(id).getValue(Animal.class).getIdade());
            ani.setIdanimal(ds.child(id).getValue(Animal.class).getIdanimal());
            ani.setIddono(ds.child(id).getValue(Animal.class).getIddono());
            ani.setNomeAnimal(ds.child(id).getValue(Animal.class).getNomeAnimal());
            ani.setPorte(ds.child(id).getValue(Animal.class).getPorte());
            ani.setRacaAnimal(ds.child(id).getValue(Animal.class).getRacaAnimal());
            ani.setSaude(ds.child(id).getValue(Animal.class).getSaude());
            ani.setSexo(ds.child(id).getValue(Animal.class).getSexo());
            ani.setTemperamento(ds.child(id).getValue(Animal.class).getTemperamento());


            animal = new ArrayList<animals>();
            ArrayList<String> array = new ArrayList<>();
            array.add(ani.getNomeAnimal());
            array.add(ani.getSexo());
            array.add(ani.getPorte());
            array.add(ani.getEndereco());
            array.add(ani.getIdade());


            animal.add(new animals(ani.getNomeAnimal(),ani.getSexo(),ani.getPorte(),ani.getEndereco(),ani.getIdade()));
            myAdapter = new animalAdapter(this,animal,this);
            recyclerView.setAdapter(myAdapter);

        }
        */
    }

    @Override
    public void onItemClick(int position) {
        animal.get(position);

        if( position == 0){
            Intent intent = new Intent(this, adocaoAnimal.class);
            startActivity(intent);

        }else{
            Intent intent = new Intent(this, teste.class);
            startActivity(intent);
        }


    }


}
