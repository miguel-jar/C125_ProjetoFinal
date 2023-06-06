package br.inatel.c125.classes;

public class Juiz extends Personagem {

    public Juiz(String nome, int altura, int peso) {
        super(nome, altura, peso);
    }

    public void fazerContagem() {
        System.out.println("\n" + this.nome + ": 1... 2... 3... Acabou a luta !!");
    }

    public void expulsarSuporte(Suporte suporte) {
        System.out.println("\n" + this.nome + ": Suporte " + suporte.nome + " expulso!");
        suporte = null;
    }

    public void desclassificarLutador(Lutador lutador) {
        lutador.vida = -1;
        System.out.println("\n" + this.nome + ": Lutador " + lutador.nome + " desclassificado!");
    }

}