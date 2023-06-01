package br.inatel.c125.classes;

public class Lutador extends Personagem {

    private int estamina, forca;
    private int reducaoVidaSignature;

    public Suporte suporte;

    public static int reducaoEstamina, reducaoVida;

    public static void setReducaoVida(int reducao) {
        reducaoVida = reducao;
    }

    public static void setReducaoEstamina(int reducao) {
        reducaoEstamina = reducao;
    }

    public Lutador(String nome, int altura, int peso, int estamina, int forca, int vida, boolean suporte) {

        super(nome, altura, peso, vida);

        this.estamina = estamina;
        this.forca = forca;
        this.reducaoVidaSignature = 20;

        if (suporte)
            this.suporte = new Suporte("Karina", 165, 65, 100);
        else
            this.suporte = null;
    }

    @Override
    public void correr() {

        if (estamina > reducaoEstamina) {
            estamina -= reducaoEstamina;
            System.out.println(this.nome + ": Correndo...");
        } else {
            estamina = 0;
            this.andar();
        }
    }

    public void pular() {
        System.out.println(this.nome + ": Pulando...");
    }

    public void chutar(Personagem personagem) {

        if (personagem.vida > reducaoVida) {
            personagem.vida -= reducaoVida;
            System.out.println(this.nome + ": Chutando...");
        } else
            this.finisher(personagem);
    }

    public void socar(Personagem personagem) {

        if (personagem.vida > reducaoVida) {
            personagem.vida -= reducaoVida;
            System.out.println(this.nome + ": Socando...");
        } else
            this.finisher(personagem);
    }

    public void interagirComPlateia(){
        System.out.println("Estao gostando ???");
    }

    public void provocarInimigo(){
        System.out.println(this.nome + " : Seu frango. COCOH COCOH COCOH !!!");
    }


    public void signature(Personagem personagem) {
        if (personagem.vida > reducaoVidaSignature) {
            personagem.vida -= reducaoVidaSignature;
            System.out.println(this.nome + ": Executando ataque especial...");
        } else
            this.finisher(personagem);
    }

    public void finisher(Personagem personagem) {
        personagem.vida = 0;
    }

    public void comeback(){

        System.out.println(this.nome + ": Fugiu pra tomar um AR");

        estamina += 20;
        vida += 15;
    }

    public void pin(Personagem personagem, Juiz juiz) {

        System.out.println(this.nome + " fazendo pin em " + personagem.nome);
        juiz.fazerContagem();
    }
}