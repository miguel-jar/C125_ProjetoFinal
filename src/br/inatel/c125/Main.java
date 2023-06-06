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

    private static void defineConfiguracoes() throws IOException {

        Path arquivoDificuldade = Paths.get("src/br/inatel/c125/arquivos/configuracoes.txt");
        List<String> configuracoes = Files.readAllLines(arquivoDificuldade);

        String a, b, c;

        a = configuracoes.get(0).split("=")[1];
        b = configuracoes.get(1).split("=")[1];
        c = configuracoes.get(2).split("=")[1];

        Lutador.setReducaoEstamina(Integer.parseInt(a));
        Lutador.setReducaoVidaFinisher(Integer.parseInt(b));
        Lutador.setReducaoVidaSignature(Integer.parseInt(c));
    }

    private static void defineDificuldade(Scanner teclado) throws IOException {

        Path arquivoDificuldade = Paths.get("src/br/inatel/c125/arquivos/dificuldades.txt");
        List<String> dificuldades = Files.readAllLines(arquivoDificuldade);

        int dificuldade;

        do {
            System.out.println("\nEscolha a dificuldade:");

            for (int i = 0; i < dificuldades.size(); i++) {
                System.out.println(" [" + i + "] " + dificuldades.get(i).split(",")[0]);
            }

            System.out.print("\nEscolha: ");
            dificuldade = teclado.nextInt();

            if ((dificuldade > 3) || (dificuldade < 1))
                System.out.println("\nEscolha indisponível. Tente Novamente");

        } while ((dificuldade >= dificuldades.size()) || (dificuldade < 0));

        String[] a = dificuldades.get(dificuldade).split(",");

        System.out.println("\nDificuldade definida para " + a[0].toUpperCase());

        Lutador.modificadorForca = Integer.parseInt(a[2]);
        Lutador.modificadorEstamina = Integer.parseInt(a[1]);
    }

    private static String[] escolheLutador(Scanner teclado) throws IOException {

        int escolhaJogador;

        Path arquivoLutadores = Paths.get("src/br/inatel/c125/arquivos/lutadores.txt");
        List<String> lutadores = Files.readAllLines(arquivoLutadores);

        do {
            System.out.println("\nEscolha seu lutador:");

            for (int i = 0; i < lutadores.size(); i++) {
                System.out.println(" [" + i + "] " + lutadores.get(i).split(",")[0]);
            }

            System.out.print("\nEscolha: ");
            escolhaJogador = teclado.nextInt();

            if ((escolhaJogador < 0) || (escolhaJogador >= lutadores.size()))
                System.out.println("\nEscolha indisponível. Tente Novamente");

        } while ((escolhaJogador < 0) || (escolhaJogador >= lutadores.size()));

        String[] parametros = lutadores.get(escolhaJogador).split(",");

        return parametros;
    }

    public static void main(String[] args) {

        try {
            defineConfiguracoes();

            int continuacao;
            Scanner teclado = new Scanner(System.in);

            do {

                try {
                    defineDificuldade(teclado);
                } catch (IOException e) {
                    System.out.println("Erro: Não foi possível definir dificuldade. Reinicie o jogo e tente novamente");
                    break;
                }

                Lutador jogador, inimigo;

                try {
                    String[] parametros = escolheLutador(teclado);

                    String nome;
                    int altura, peso, estamina, forca;
                    boolean suporte;

                    nome = parametros[0];
                    altura = Integer.parseInt(parametros[1]);
                    peso = Integer.parseInt(parametros[2]);
                    estamina = Integer.parseInt(parametros[3]);
                    forca = Integer.parseInt(parametros[4]);
                    suporte = Boolean.parseBoolean(parametros[5]);

                    jogador = new Lutador(nome, altura, peso, estamina - Lutador.modificadorEstamina, forca - Lutador.modificadorForca, suporte);

                    System.out.println("\nLutador " + nome + " selecionado");

                } catch (IOException e) {
                    System.out.println("Erro: Não foi possível selecionar jogador. Reinicie o jogo e tente novamente");
                    break;
                }

                try {

                    String[] parametros = escolheLutador(teclado);

                    String nome;
                    int altura, peso, estamina, forca;
                    boolean suporte;

                    nome = parametros[0];
                    altura = Integer.parseInt(parametros[1]);
                    peso = Integer.parseInt(parametros[2]);
                    estamina = Integer.parseInt(parametros[3]);
                    forca = Integer.parseInt(parametros[4]);
                    suporte = Boolean.parseBoolean(parametros[5]);

                    inimigo = new Lutador(nome, altura, peso, estamina + Lutador.modificadorEstamina, forca + Lutador.modificadorForca, suporte);

                    System.out.println("\nInimigo " + nome + " selecionado");

                } catch (IOException e) {
                    System.out.println("Erro: Não foi possível selecionar inimigo. Reinicie o jogo e tente novamente");
                    break;
                }

                Juiz juiz = new Juiz("Cabessa de Ovo", 185, 80);

                Random r = new Random();

                System.out.println("\nIniciando partida...\n");

                while ((jogador.getVida() > 0) && (inimigo.getVida() > 0)) {

                    int escolha = r.nextInt(0, 34);

                    switch (escolha) {
                        case 0, 1, 2, 28:
                            jogador.correr();
                            jogador.chutar(inimigo);
                            break;

                        case 3, 4, 5, 29:
                            jogador.pular();
                            jogador.socar(inimigo);
                            break;

                        case 6, 26:
                            jogador.comeback();
                            break;

                        case 7, 33:
                            jogador.signature(inimigo);
                            break;

                        case 8, 9:
                            try {
                                jogador.suporte.atrapalharJuiz(juiz);
                            } catch (NullPointerException e) {
                                jogador.provocarInimigo();
                            }
                            break;

                        case 10, 11:
                            try {
                                jogador.suporte.atrapalharJuiz(juiz);
                                juiz.expulsarSuporte(jogador.suporte);
                            } catch (NullPointerException e) {
                                jogador.interagirComPlateia();
                            }
                            break;

                        case 12:
                            jogador.pular();
                            jogador.chutar(juiz);
                            juiz.desclassificarLutador(jogador);
                            break;

                        case 13, 14, 15, 30:
                            inimigo.correr();
                            inimigo.socar(jogador);
                            break;

                        case 16, 17, 18, 31:
                            inimigo.pular();
                            inimigo.chutar(jogador);
                            break;

                        case 19, 27:
                            inimigo.comeback();
                            break;

                        case 20, 32:
                            inimigo.signature(jogador);
                            break;

                        case 21, 22:
                            try {
                                inimigo.suporte.atrapalharJuiz(juiz);
                            } catch (NullPointerException e) {
                                inimigo.provocarInimigo();
                            }
                            break;

                        case 23, 24:
                            try {
                                inimigo.suporte.atrapalharJuiz(juiz);
                                juiz.expulsarSuporte(inimigo.suporte);
                            } catch (NullPointerException e) {
                                inimigo.interagirComPlateia();
                            }
                            break;

                        case 25:
                            inimigo.pular();
                            inimigo.socar(juiz);
                            juiz.desclassificarLutador(inimigo);
                            break;
                    }

                    System.out.println();

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

                if (inimigo.getVida() == -1) {
                    System.out.println("Que pena, você venceu !!");
                } else if (jogador.getVida() == -1) {
                    System.out.println("Parabéns, você perdeu !!");
                } else if (jogador.getVida() == 0) {
                    inimigo.pin(jogador, juiz);
                    System.out.println("\nParabéns, você perdeu !!");
                } else {
                    jogador.pin(inimigo, juiz);
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