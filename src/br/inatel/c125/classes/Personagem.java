package br.inatel.c125.classes;

public abstract class Personagem {
    protected String nome;
    protected int altura, peso, vida;

    public Personagem(String nome, int altura, int peso) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.vida = 100;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}