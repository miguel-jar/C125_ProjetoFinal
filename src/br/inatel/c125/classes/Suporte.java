package br.inatel.c125.classes;

public class Suporte extends Personagem {

    public Suporte(String nome, int altura, int peso, int vida) {
        super(nome, altura, peso, vida);
    }

    public void atrapalharJuiz(Juiz juiz){
        System.out.println(this.nome + ": Atrapalhando juiz...");
    }

}