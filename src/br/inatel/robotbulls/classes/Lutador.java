package br.inatel.robotbulls.classes;

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

    this.suporte = null;
  }

  @Override
  public void correr() {

    if (estamina > reducaoEstamina) {
      estamina -= reducaoEstamina;
      System.out.println("Correndo...");
    }

    else
      this.andar();
  }

  public void chutar(Personagem personagem) {

    if (personagem.vida > reducaoVida) {
      personagem.vida -= reducaoVida;
      System.out.println("Chutando...");
    }

    else
      this.finisher(personagem);
  }

  public void socar(Personagem personagem) {

    if (personagem.vida > reducaoVida) {
      personagem.vida -= reducaoVida;
      System.out.println("Socando...");
    }

    else
      this.finisher(personagem);
  }

  public void pin(Juiz juiz){
    juiz.fazerContagem();
  }

  public void signature(Personagem personagem){}
  public void finisher(Personagem personagem){}

}