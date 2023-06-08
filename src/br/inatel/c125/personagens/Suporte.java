package br.inatel.c125.personagens;

import br.inatel.c125.outros.Configuracoes;

public class Suporte extends Personagem implements GolpeEspecial {

    public Suporte(String nome, int altura, int peso) {
        super(nome, altura, peso);
    }

    public void atrapalhar(Personagem personagem) {
        System.out.println(this.nome + ": Atrapalhando " + personagem.nome + "...");
    }

    @Override
    public void signature(Personagem personagem) {

        System.out.println(this.nome + " aplicou um golpe especial em " + personagem.nome);

        if (personagem.vida > Configuracoes.getReducaoSignatureSuporte()) {
            personagem.vida -= Configuracoes.getReducaoSignatureSuporte();
        } else
            personagem.vida = 0;
    }
}