package br.inatel.c125.classes;

public class Lutador extends Personagem {

  private int estamina, forca;
  public Suporte suporte;

  public static int reducaoEstamina, reducaoVida;

  public static void setReducaoVida(int reducao){
    reducaoVida = reducao;
  }

  public static void setReducaoEstamina(int reducao){
    reducaoEstamina = reducao;
  }

  public Lutador(String nome, int altura, int peso, int estamina, int forca, int vida, boolean suporte) {

    super(nome, altura, peso, vida);

    this.estamina = estamina;
    this.forca = forca;

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
    }

    else {
      estamina = 0;
      this.andar();
    }
  }

  public void chutar(Personagem personagem) {

    if (personagem.vida > reducaoVida) {
      personagem.vida -= reducaoVida;
      System.out.println(this.nome + ": Chutando...");
    }

    else
      this.finisher(personagem);
  }

  public void socar(Personagem personagem) {

    if (personagem.vida > reducaoVida) {
      personagem.vida -= reducaoVida;
      System.out.println(this.nome + ": Socando...");
    }

    else
      this.finisher(personagem);
  }

  public void pin(Juiz juiz){
    juiz.fazerContagem();
  }

  public void signature(Personagem personagem){

  }
  public void finisher(Personagem personagem){
    personagem.vida = 0;

  }

}