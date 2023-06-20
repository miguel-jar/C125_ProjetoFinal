package br.inatel.c125;

import br.inatel.c125.outros.Configuracoes;
import br.inatel.c125.personagens.Juiz;
import br.inatel.c125.personagens.Lutador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        try {
            Configuracoes.defineConfiguracoes();

            int continuacao;
            Scanner teclado = new Scanner(System.in);

            do {

                Lutador jogador, inimigo;

                System.out.println("\n...................................... Seleção de Personagens ......................................");

                try {

                    String[] parametros = Configuracoes.escolheLutador(teclado);

                    String nome;
                    int altura, peso, estamina, forca;
                    boolean suporte;

                    nome = parametros[0];
                    altura = Integer.parseInt(parametros[1]);
                    peso = Integer.parseInt(parametros[2]);
                    estamina = Integer.parseInt(parametros[3]);
                    forca = Integer.parseInt(parametros[4]);
                    suporte = Boolean.parseBoolean(parametros[5]);

                    jogador = new Lutador(nome, altura, peso, estamina - Configuracoes.getModificadorEstamina(), forca - Configuracoes.getModificadorForca(), suporte);

                    System.out.println("\nLutador " + nome + " selecionado");

                } catch (IOException e) {
                    System.out.println("Erro: Não foi possível selecionar jogador. Reinicie o jogo e tente novamente");
                    break;
                }

                try {

                    String[] parametros = Configuracoes.escolheLutador(teclado);

                    String nome;
                    int altura, peso, estamina, forca;
                    boolean suporte;

                    nome = parametros[0];
                    altura = Integer.parseInt(parametros[1]);
                    peso = Integer.parseInt(parametros[2]);
                    estamina = Integer.parseInt(parametros[3]);
                    forca = Integer.parseInt(parametros[4]);
                    suporte = Boolean.parseBoolean(parametros[5]);

                    inimigo = new Lutador(nome, altura, peso, estamina + Configuracoes.getModificadorEstamina(), forca + Configuracoes.getModificadorForca(), suporte);

                    System.out.println("\nInimigo " + nome + " selecionado\n");

                } catch (IOException e) {
                    System.out.println("Erro: Não foi possível selecionar inimigo. Reinicie o jogo e tente novamente");
                    break;
                }

                System.out.println("...................................... Seleção de Dificuldade ......................................");

                try {
                    Configuracoes.defineDificuldade(teclado);
                } catch (IOException e) {
                    System.out.println("Erro: Não foi possível definir dificuldade. Reinicie o jogo e tente novamente");
                    break;
                }

                Juiz juiz;

                try {
                    Path arquivoSuporte = Paths.get("src/br/inatel/c125/arquivos/padroes/juiz_padrao.txt");

                    String[] parametros = Files.readAllLines(arquivoSuporte).get(0).split(",");

                    String name;
                    int hight, weight;

                    name = parametros[0];
                    hight = Integer.parseInt(parametros[1]);
                    weight = Integer.parseInt(parametros[2]);

                    juiz = new Juiz(name, hight, weight);

                } catch (IOException e) {
                    System.out.println("Erro: Não foi possível criar juiz. Reinicie o jogo e tente novamente");
                    break;
                }

                Random r = new Random();

                System.out.println("\nIniciando partida...\n");

                System.out.println("............................................ Simulação .............................................\n");

                while ((jogador.getVida() > 0) && (inimigo.getVida() > 0)) {

                    int escolha = r.nextInt(0, 54);

                    switch (escolha) {
                        case 0, 1, 2, 3, 4, 5, 6, 7 -> {
                            jogador.correr();
                            jogador.chutar(inimigo);
                        }

                        case 8, 9, 10, 11, 12, 13, 14, 15 -> {
                            jogador.pular();
                            jogador.socar(inimigo);
                        }

                        case 16, 17, 18 -> {
                            jogador.signature(inimigo);
                            jogador.fazerPin(inimigo, juiz);
                        }

                        case 19 -> {
                            jogador.finisher(inimigo);
                            jogador.fazerPin(inimigo, juiz);
                        }

                        case 20 -> {
                            jogador.pular();
                            jogador.chutar(juiz);
                            juiz.desclassificarLutador(jogador);
                        }

                        case 21, 22 -> jogador.comeback();

                        case 23, 24 -> {
                            try {
                                jogador.getSuporte().atrapalhar(inimigo);
                            } catch (NullPointerException e) {
                                jogador.provocarInimigo();
                            }
                        }

                        case 25, 26 -> {
                            try {
                                jogador.getSuporte().signature(inimigo);
                                juiz.expulsarSuporte(jogador);
                            } catch (NullPointerException e) {
                                jogador.interagirComPlateia();
                            }
                        }

                        case 27, 28, 29, 30, 31, 32, 33, 34 -> {
                            inimigo.correr();
                            inimigo.socar(jogador);
                        }

                        case 35, 36, 37, 38, 39, 40, 41, 42 -> {
                            inimigo.pular();
                            inimigo.chutar(jogador);
                        }

                        case 43, 44, 45 -> {
                            inimigo.signature(jogador);
                            inimigo.fazerPin(jogador, juiz);
                        }

                        case 46 -> {
                            inimigo.finisher(jogador);
                            inimigo.fazerPin(jogador, juiz);
                        }

                        case 47 -> {
                            inimigo.pular();
                            inimigo.socar(juiz);
                            juiz.desclassificarLutador(inimigo);
                        }

                        case 48, 49 -> inimigo.comeback();

                        case 50, 51 -> {
                            try {
                                inimigo.getSuporte().atrapalhar(jogador);
                            } catch (NullPointerException e) {
                                inimigo.provocarInimigo();
                            }
                        }

                        case 52, 53 -> {
                            try {
                                inimigo.getSuporte().signature(jogador);
                                juiz.expulsarSuporte(inimigo);
                            } catch (NullPointerException e) {
                                inimigo.interagirComPlateia();
                            }
                        }
                    }

                    System.out.println();

                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

                if (inimigo.getVida() == -1) {
                    System.out.println("Que pena, você venceu !!");
                } else if (jogador.getVida() == -1) {
                    System.out.println("Parabéns, você perdeu !!");
                } else if (jogador.getVida() == 0) {
                    inimigo.fazerPin(jogador, juiz);
                    System.out.println("\nParabéns, você perdeu !!");
                } else {
                    jogador.fazerPin(inimigo, juiz);
                    System.out.println("\nQue pena, você venceu !!");
                }


                do {
                    System.out.println("\nDeseja Jogar novamente?");
                    System.out.println(" [0] Não ");
                    System.out.println(" [1] Sim ");
                    System.out.print("\nEscolha: ");
                    continuacao = teclado.nextInt();
                } while ((continuacao > 1) || (continuacao < 0));

            } while (continuacao == 1);

            teclado.close();

        } catch (IOException e) {
            System.out.println("Não foi possível inicializar o jogo. Tente Novamente");
        }
    }
}