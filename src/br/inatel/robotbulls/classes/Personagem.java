package br.inatel.robotbulls.classes;

public abstract class Personagem {
    protected String nome;
    protected int altura, peso, vida;

    public Personagem(String nome, int altura, int peso, int vida) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.vida = vida;
    }

    public void correr() {
        System.out.println("Correndo...");
    }

    public void andar() {
        System.out.println("Andando...");
    }

}