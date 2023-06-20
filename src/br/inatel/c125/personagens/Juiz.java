package br.inatel.c125.personagens;

public class Juiz extends Personagem {

    public Juiz(String nome, int altura, int peso) {
        super(nome, altura, peso);
    }

    public void fazerContagem() {
        System.out.println("\n" + this.nome + ": 1... 2... 3... Acabou a luta !!");
    }

    public void fazerContagem(Lutador lutador) {

        System.out.println("\n" + this.nome + ": 1... 2...");

        try {
            lutador.suporte.atrapalhar(this);
            System.out.println("\n" + lutador.nome + ": KickOut !!!");
        } catch (NullPointerException e) {
            System.out.println("\n" + lutador.nome + ": KickOut !!!");
        }
    }

    public void expulsarSuporte(Lutador lutador) {
        System.out.println("\n" + this.nome + ": Suporte " + lutador.suporte.nome + " expulso!");
        lutador.suporte = null;
    }

    public void desclassificarLutador(Lutador lutador) {
        lutador.vida = -1;
        System.out.println("\n" + this.nome + ": Lutador " + lutador.nome + " desclassificado!");
    }

}