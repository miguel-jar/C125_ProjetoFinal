package br.inatel.c125.classes;

public class Juiz extends Personagem {

    public Juiz(String nome, int altura, int peso, int vida) {
        super(nome, altura, peso, vida);
    }

    public void fazerContagem(){
        System.out.println("1... 2... 3... Acabou a luta !!");
    }

    public void expulsarSuporte(Suporte suporte){
      
    }

    public void desclassificarLutador(Lutador lutador)
    {
    
    }

}