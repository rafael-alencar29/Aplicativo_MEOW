package com.example.meow;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class cadastroAnimal extends AppCompatActivity {

    /* Declaração para abrir galeria */
    private ImageView imagem;
    private Uri selectedImage;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSION_REQUEST = 2;

    /* Declaração para cadastro no database */
    DatabaseReference databaseReference;
     EditText nomeAnimal, doenca;
    private RadioButton cachorro, gato;
    private RadioButton macho, femea;
    private RadioButton pequeno, medio, grande;
    private RadioButton filhote, adulto, idoso;
    private CheckBox brincalhao, timido, calmo, guarda, amoroso, preguicoso;
    private CheckBox vacinado, vermifugado, castrado, doente;
    private Button cadastrar;

    /* Declaração para salvar imagem no banco */
    private StorageReference storageReference;

    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroanimal);

        /* Funcoes para galeria */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
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
        brincalhao = findViewById(R.id.cbBrincalhao);
        timido = findViewById(R.id.cbTimido);
        calmo = findViewById(R.id.cbCalmo);
        guarda = findViewById(R.id.cbGuarda);
        amoroso = findViewById(R.id.cbAmoroso);
        preguicoso = findViewById(R.id.cbPreguicoso);
        vacinado = findViewById(R.id.cbVacinado);
        vermifugado = findViewById(R.id.cbVermifugado);
        castrado = findViewById(R.id.cbCastrado);
        doente = findViewById(R.id.cbDoente);
        doenca = findViewById(R.id.text_id);


        /* Funcoes para salvar imagem no banco */
        storageReference = FirebaseStorage.getInstance().getReference();
        pd = new ProgressDialog(this);
        pd.setMessage("Uploading ... ");


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
        String doencas = doenca.getText().toString();
        String especie, sexo, porte, idade, temperamento, saude;

        /* ta errado ver depois */
        if (cachorro.isChecked())
            especie = "Cachorro";
        else {
            especie = "Gato";
        }
        if (macho.isChecked())
            sexo = "Macho";
        else
            sexo = "Femea";
        if (pequeno.isChecked())
            porte = "Pequeno";
        else {
            if (medio.isChecked())
                porte = "Médio";
            else
                porte = "Grande";
        }
        if (filhote.isChecked())
            idade = "Filhote";
        else {
            if (adulto.isChecked())
                idade = "Adulto";
            else
                idade = "Idoso";
        }
        temperamento = Temperamentos();
        saude = Saude();





        if (!TextUtils.isEmpty(nome)) {
            String id = databaseReference.push().getKey();
            String Donoid = FirebaseAuth.getInstance().getUid();

            DadosAnimal animais = new DadosAnimal(id, nome, especie, sexo, porte, idade, temperamento, saude, doencas, Donoid);
            databaseReference.child(id).setValue(animais);

            /* Salvar imagem no storage */
            if (selectedImage != null) {
                pd.show();
                StorageReference childRef = storageReference.child(nome + ".jpg");

                // uploading the image
                UploadTask uploadTask = childRef.putFile(selectedImage);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(cadastroAnimal.this, "Upload successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(cadastroAnimal.this, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(cadastroAnimal.this, "Select an image", Toast.LENGTH_SHORT).show();
            }

            /* Zera tudo */
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
            brincalhao.setChecked(false);
            timido.setChecked(false);
            calmo.setChecked(false);
            guarda.setChecked(false);
            amoroso.setChecked(false);
            preguicoso.setChecked(false);
            vacinado.setChecked(false);
            vermifugado.setChecked(false);
            castrado.setChecked(false);
            doente.setChecked(false);
            doenca.setText("");
        } else {
            Toast.makeText(cadastroAnimal.this, "Digite o nome do animal", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALERIA_IMAGENS) {
            selectedImage = data.getData();
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

    // Função para pegar temperamentos e retornar uma string
    private String Temperamentos() {
        String temp = "";

        if (brincalhao.isChecked())
            temp += "Brincalhão";
        if (timido.isChecked()) {
            if (temp.length() == 0)
                temp += "Tímido";
            else
                temp += ", Tímido";
        }
        if (calmo.isChecked()) {
            if (temp.length() == 0)
                temp += "Calmo";
            else
                temp += ", Calmo";
        }
        if (guarda.isChecked()) {
            if (temp.length() == 0)
                temp += "Guarda";
            else
                temp += ", Guarda";
        }
        if (amoroso.isChecked()) {
            if (temp.length() == 0)
                temp += "Amoroso";
            else
                temp += ", Amoroso";
        }
        if (preguicoso.isChecked()) {
            if (temp.length() == 0)
                temp += "Preguiçoso";
            else
                temp += ", Preguiçoso";
        }

        return temp;
    }

    // Funcao para pegar saudo e retornar em string
    private String Saude() {
        String saudeAnimal = "";

        if (vacinado.isChecked()) {
            if (saudeAnimal.length() == 0)
                saudeAnimal += "Vacinado";
            else
                saudeAnimal += ", Vacinado";
        }
        if (vermifugado.isChecked()) {
            if (saudeAnimal.length() == 0)
                saudeAnimal += "Vermifugado";
            else
                saudeAnimal += "Vermifugado";
        }
        if (castrado.isChecked()) {
            if (saudeAnimal.length() == 0)
                saudeAnimal += "Castrado";
            else
                saudeAnimal += ", Castrado";
        }
        if (doente.isChecked()) {
            if (saudeAnimal.length() == 0)
                saudeAnimal += "Doente";
            else
                saudeAnimal += ", Doente";
        }
        return saudeAnimal;
    }


}
