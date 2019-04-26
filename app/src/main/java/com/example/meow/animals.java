package com.example.meow;

import android.widget.ImageView;

public class animals {

    private String nome,sexo,porte,endereco,idade;
    private ImageView dogPicture;

    public animals(String nome, String sexo, String porte, String endereco, String idade) {
        this.nome = nome;
        this.sexo = sexo;
        this.porte = porte;
        this.endereco = endereco;
        this.idade = idade;
    }

    public void setDogPicture(ImageView dogPicture) {
        this.dogPicture = dogPicture;
    }

    public ImageView getDogPicture() {
        return dogPicture;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}
