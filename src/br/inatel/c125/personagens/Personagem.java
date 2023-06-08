package br.inatel.c125.personagens;

public abstract class Personagem {
    protected final String nome;
    protected final int altura, peso;
    protected int vida;

    public Personagem(String nome, int altura, int peso) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.vida = 100;
    }

    public int getVida() {
        return vida;
    }

    public void andar() {
        System.out.println(this.nome + ": Andando...");
    }

    public void correr() {
        System.out.println(this.nome + ": Correndo...");
    }

    public void pular() {
        System.out.println(this.nome + ": Pulando...");
    }
}