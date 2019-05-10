package com.example.meow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cadastroAnimal extends AppCompatActivity {

    /* Declaração para abrir galeria */
    private ImageView imagem;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSION_REQUEST = 2;

    /* Declaração para cadastro no database */
    DatabaseReference databaseReference;
    private EditText nomeAnimal;
    private RadioButton cachorro, gato;
    private RadioButton macho, femea;
    private RadioButton pequeno, medio, grande;
    private RadioButton filhote, adulto, idoso;
    private Button cadastrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroanimal);

        /* Funcoes para galeria */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        }


        imagem = (ImageView) findViewById(R.id.imageView10);
        final Button galeria = (Button) findViewById(R.id.btnImagem);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galeria.setVisibility(View.INVISIBLE);
                startActivityForResult(intent, GALERIA_IMAGENS);
            }
        });

        /* Funcoes para cadastrar no banco */
        databaseReference = FirebaseDatabase.getInstance().getReference("animais");
        nomeAnimal = findViewById(R.id.editText);
        cachorro = findViewById(R.id.rBcachorro);
        gato = findViewById(R.id.rBgato);
        macho = findViewById(R.id.rBmacho);
        femea = findViewById(R.id.rBfemea);
        pequeno = findViewById(R.id.rBpequeno);
        medio = findViewById(R.id.rBmedio);
        grande = findViewById(R.id.rBgrande);
        filhote = findViewById(R.id.rBfilhote);
        adulto = findViewById(R.id.rBadulto);
        idoso = findViewById(R.id.rBidoso);
        cadastrar = findViewById(R.id.PROCURAR);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAnimal();
            }
        });
    }

    protected void addAnimal() {
        String nome = nomeAnimal.getText().toString();
        String especie, sexo, porte, idade;

        /* ta errado ver depois */
        if(cachorro.isChecked())
            especie = "Cachorro";
        else {
            especie = "Gato";
        }
        if(macho.isChecked())
            sexo = "Macho";
        else
            sexo = "Femea";
        if(pequeno.isChecked())
            porte = "Pequeno";
        else {
            if (medio.isChecked())
                porte = "Médio";
            else
                porte = "Grande";
        }
        if(filhote.isChecked())
            idade = "Filhote";
        else {
            if(adulto.isChecked())
                idade = "Adulto";
            else
                idade = "Idoso";
        }



        if(!TextUtils.isEmpty(nome)) {
            String id = databaseReference.push().getKey();

            DadosAnimal animais = new DadosAnimal(id, nome, especie, sexo, porte, idade);
            databaseReference.child(id).setValue(animais);

            nomeAnimal.setText("");
            cachorro.setChecked(false);
            gato.setChecked(false);
            macho.setChecked(false);
            femea.setChecked(false);
            pequeno.setChecked(false);
            medio.setChecked(false);
            grande.setChecked(false);
            filhote.setChecked(false);
            adulto.setChecked(false);
            idoso.setChecked(false);
        } else {
            Toast.makeText(cadastroAnimal.this, "Digite o nome do animal", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS) {
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap imagemGaleria = (BitmapFactory.decodeFile(picturePath));
            imagem.setImageBitmap(imagemGaleria);
        }
    }

    // faz o tratamento da resposta de permissao do usuario
    @Override
    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }


}
