package com.example.meow;

public class DadosAnimal {
    private String IDAnimal;
    private String NomeAnimal;
    private String RacaAnimal;
    private String Sexo;
    private String Porte;
    private String Idade;
    private String Temperamento;
    private String Saude;
    private String Doencas;
    private String IDDono;
    private String Endereco;

    public DadosAnimal(String IDAnimal, String nomeAnimal, String racaAnimal, String sexo, String porte, String idade, String temperamento, String saude, String doencas, String IDDono,String endereco) {
        this.IDAnimal = IDAnimal;
        NomeAnimal = nomeAnimal;
        RacaAnimal = racaAnimal;
        Sexo = sexo;
        Porte = porte;
        Idade = idade;
        Endereco = endereco;
        Temperamento = temperamento;
        Saude = saude;
        Doencas = doencas;
        this.IDDono = IDDono;
    }

    public String getIDAnimal() {
        return IDAnimal;
    }

    public String getNomeAnimal() {
        return NomeAnimal;
    }

    public String getRacaAnimal() {
        return RacaAnimal;
    }

    public String getSexo() {
        return Sexo;
    }

    public String getPorte() {
        return Porte;
    }

    public String getIdade() {
        return Idade;
    }

    public String getTemperamento() {
        return Temperamento;
    }

    public String getSaude() {
        return Saude;
    }

    public String getDoencas() {
        return Doencas;
    }

    public String getIDDono() {
        return IDDono;
    }

    public String getEndereco() { return Endereco; }
}