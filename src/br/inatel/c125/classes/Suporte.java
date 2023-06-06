package br.inatel.c125.classes;

public class Suporte extends Personagem {

    public Suporte(String nome, int altura, int peso) {
        super(nome, altura, peso);
    }

    public void atrapalharJuiz(Juiz juiz) {
        System.out.println(this.nome + ": Atrapalhando juiz...");
    }

}