package br.inatel.c125.classes;

public abstract class Personagem {
    protected String nome;
    protected int altura, peso, vida;

    public Personagem(String nome, int altura, int peso, int vida) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void correr() {
        System.out.println(this.nome + ": Correndo...");
    }

    public void andar() {
        System.out.println(this.nome + ": Andando...");
    }

}