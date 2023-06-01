package br.inatel.c125;

import br.inatel.c125.classes.Juiz;
import br.inatel.c125.classes.Lutador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Definindo dificuldade do jogo

        int dificuldade;
        int continuacao;

        do {


            do {
                System.out.println("\nEscolha a dificuldade:");
                System.out.println(" - Fácil   [1]");
                System.out.println(" - Médio   [2]");
                System.out.println(" - Difícil [3]");

                System.out.print("\nEscolha: ");
                dificuldade = teclado.nextInt();

            } while ((dificuldade > 3) || (dificuldade < 1));

            Path arquivoDificuldade = Paths.get("src/br/inatel/c125/arquivos/dificuldades.txt");

            try {
                List<String> lista = Files.readAllLines(arquivoDificuldade);
                String[] a = lista.get(dificuldade - 1).split(",");

                Lutador.setReducaoEstamina(Integer.parseInt(a[1]));
                Lutador.setReducaoVida(Integer.parseInt(a[2]));

                System.out.println("\nDificuldade definida para " + a[0].toUpperCase());

                Path arquivoLutadores = Paths.get("src/br/inatel/c125/arquivos/lutadores.txt");

                try {
                    List<String> listaJogadores = Files.readAllLines(arquivoLutadores);

                    int escolhaJogador;

                    do {
                        System.out.println("\nEscolha seu lutador:");

                        for (int i = 0; i < listaJogadores.size(); i++) {
                            System.out.println(" - " + listaJogadores.get(i).split(",")[0] + " [" + i + "]");
                        }

                        System.out.print("\nEscolha: ");
                        escolhaJogador = teclado.nextInt();

                    } while ((escolhaJogador >= listaJogadores.size()) || (escolhaJogador < 0));

                    String[] parametros = listaJogadores.get(escolhaJogador).split(",");

                    int altura, peso, estamina, forca, vida;
                    boolean suporte;

                    altura = Integer.parseInt(parametros[1]);
                    peso = Integer.parseInt(parametros[2]);
                    estamina = Integer.parseInt(parametros[3]);
                    forca = Integer.parseInt(parametros[4]);
                    vida = Integer.parseInt(parametros[5]);
                    suporte = Boolean.parseBoolean(parametros[6]);

                    Lutador jogador = new Lutador(parametros[0], altura, peso, estamina, forca, vida, suporte);

                    Lutador inimigo = new Lutador("Zanaré", 175, 85, 65, 65, 100, false);
                    Juiz juiz = new Juiz("Cabessa de Ovo", 185, 80, 50);

                    Random r = new Random();

                    while ((jogador.getVida() > 0) && (inimigo.getVida() > 0)) {

                        int escolha = r.nextInt(0, 14);

                        switch (escolha) {
                            case 0:
                                jogador.correr();
                                jogador.chutar(inimigo);
                                break;

                            case 1:
                                jogador.correr();
                                jogador.pular();
                                jogador.socar(inimigo);
                                break;

                            case 2:
                                jogador.comeback();
                                break;

                            case 3:
                                jogador.signature(inimigo);
                                break;

                            case 4:
                                try {
                                    jogador.suporte.correr();
                                    jogador.suporte.atrapalharJuiz(juiz);
                                } catch (NullPointerException e) {
                                    jogador.provocarInimigo();
                                }
                                break;

                            case 5:
                                try {
                                    jogador.suporte.correr();
                                    jogador.suporte.atrapalharJuiz(juiz);
                                    juiz.expulsarSuporte(jogador.suporte);
                                } catch (NullPointerException e) {
                                    jogador.interagirComPlateia();
                                }
                                break;

                            case 6:
                                jogador.correr();
                                jogador.pular();
                                jogador.chutar(juiz);
                                juiz.desclassificarLutador(jogador);
                                break;

                            case 7:
                                inimigo.correr();
                                inimigo.chutar(jogador);
                                break;

                            case 8:
                                inimigo.correr();
                                inimigo.pular();
                                inimigo.socar(jogador);
                                break;

                            case 9:
                                inimigo.comeback();
                                break;

                            case 10:
                                inimigo.signature(jogador);
                                break;

                            case 11:
                                try {
                                    inimigo.suporte.correr();
                                    inimigo.suporte.atrapalharJuiz(juiz);
                                } catch (NullPointerException e) {
                                    inimigo.provocarInimigo();
                                }
                                break;

                            case 12:
                                try {
                                    inimigo.suporte.correr();
                                    inimigo.suporte.atrapalharJuiz(juiz);
                                    juiz.expulsarSuporte(inimigo.suporte);
                                } catch (NullPointerException e) {
                                    inimigo.interagirComPlateia();
                                }
                                break;

                            case 13:
                                inimigo.correr();
                                inimigo.pular();
                                inimigo.chutar(juiz);
                                juiz.desclassificarLutador(inimigo);
                                break;
                        }

                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println();
                    }

                    if (inimigo.getVida() == -1) {
                        System.out.println("Que pena, você venceu !!");
                    } else if (jogador.getVida() == -1) {
                        System.out.println("Parabéns, você perdeu !!");
                    } else if (jogador.getVida() == 0) {
                        inimigo.pin(jogador, juiz);
                        System.out.println("Parabéns, você perdeu !!");
                    } else {
                        jogador.pin(inimigo, juiz);
                        System.out.println("Que pena, você venceu !!");
                    }

                    System.out.println("\nDeseja Jogar novamente?");
                    System.out.println(" - Não [0]");
                    System.out.println(" - Sim [1]");
                    System.out.print("\nEscolha: ");
                    continuacao = teclado.nextInt();

                } catch (IOException e) {
                    System.out.println("Erro: Não foi possível selecionar jogador. Reinicie o jogo e tente novamente");
                    continuacao = 0;
                }

            } catch (IOException e) {
                System.out.println("Erro: Não foi possível definir dificuldade. Reinicie o jogo e tente novamente");
                continuacao = 0;
            }
        } while (continuacao == 1);

        teclado.close();
    }
}