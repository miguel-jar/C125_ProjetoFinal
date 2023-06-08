package br.inatel.c125.personagens;

import br.inatel.c125.outros.Configuracoes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lutador extends Personagem implements GolpeEspecial {

    private int estamina;
    private final int forca;
    protected Suporte suporte;

    public Suporte getSuporte() {
        return suporte;
    }

    public Lutador(String nome, int altura, int peso, int estamina, int forca, boolean suporte) {

        super(nome, altura, peso);

        this.estamina = estamina;
        this.forca = forca;

        if (suporte) {
            try {
                defineSuporte();
            } catch (IOException e) {
                System.out.println("Não foi possível definir o suporte.");
                this.suporte = null;
            }

        } else
            this.suporte = null;
    }

    private void defineSuporte() throws IOException {

        Path arquivoSuporte = Paths.get("src/br/inatel/c125/arquivos/padroes/suporte_padrao.txt");

        String[] parametros = Files.readAllLines(arquivoSuporte).get(0).split(",");

        String name;
        int hight, weight;

        name = parametros[0];
        hight = Integer.parseInt(parametros[1]);
        weight = Integer.parseInt(parametros[2]);

        this.suporte = new Suporte(name, hight, weight);
    }

    @Override
    public void correr() {

        if (estamina > Configuracoes.getReducaoEstaminaLutador()) {
            estamina -= Configuracoes.getReducaoEstaminaLutador();
            System.out.println(this.nome + ": Correndo...");
        } else {
            estamina = 0;
            this.andar();
        }
    }

    public void chutar(Personagem personagem) {

        if (personagem.vida > forca) {
            personagem.vida -= forca;

            if (personagem instanceof Juiz)
                System.out.println(this.nome + ": Chutando Juiz...");
            else
                System.out.println(this.nome + ": Chutando...");
        } else
            this.finisher(personagem);
    }

    public void socar(Personagem personagem) {

        if (personagem.vida > forca) {
            personagem.vida -= forca;
            System.out.println(this.nome + ": Socando...");
        } else
            this.finisher(personagem);
    }

    public void interagirComPlateia() {
        System.out.println(this.nome + ": Estao gostando ???");
    }

    public void provocarInimigo() {
        System.out.println(this.nome + ": COCOH COCOH COCOH !!!");
    }

    public void finisher(Personagem personagem) {

        System.out.println(this.nome + ": Finalizando " + personagem.nome);

        if (personagem.vida > Configuracoes.getReducaoFinisherLutador()) {
            personagem.vida -= Configuracoes.getReducaoFinisherLutador();
        } else
            personagem.vida = 0;
    }

    @Override
    public void signature(Personagem personagem) {
        if (personagem.vida > Configuracoes.getReducaoSignatureLutador()) {
            personagem.vida -= Configuracoes.getReducaoSignatureLutador();
            System.out.println(this.nome + ": Executando ataque especial...");
        } else
            this.finisher(personagem);
    }

    public void comeback() {

        System.out.println(this.nome + " fugiu para tomar um ar");

        estamina += 20;
        vida += 15;

        if (vida > 100) {
            vida = 100;
        }

        if (estamina > 100) {
            estamina = 100;
        }
    }

    public void fazerPin(Lutador lutador, Juiz juiz) {

        System.out.println(this.nome + " fazendo pin em " + lutador.nome);

        if (lutador.vida > 0)
            juiz.fazerContagem(lutador);
        else
            juiz.fazerContagem();
    }
}