package br.inatel.c125;

import br.inatel.c125.classes.Lutador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Definindo dificuldade do jogo

        int dificuldade;

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

            System.out.println("\nDificuldade definida para " + a[0]);

            Path arquivoLutadores = Paths.get("src/br/inatel/c125/arquivos/lutadores.txt");

            try {
                List<String> listaJogadores = Files.readAllLines(arquivoLutadores);

                int escolhaJogador;

                do {
                    System.out.println("\nEscolha seu lutador:");

                    for (int i = 0; i < listaJogadores.size(); i++) {
                        System.out.println(" - " + listaJogadores.get(i).split(",")[0] + " [" + (i+1) + "]");
                    }

                    System.out.print("\nEscolha: ");
                    escolhaJogador = teclado.nextInt();

                } while ((escolhaJogador > listaJogadores.size()) || (escolhaJogador < 1));

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

                while((jogador.getVida() > 0) && (inimigo.getVida() > 0)){

                    Random r = new Random();
                    int escolha = r.nextInt(0, 2);

                }

            } catch (IOException e) {
                System.out.println("Erro: Não foi possível selecionar jogador. Reinicie o jogo e tente novamente");
            }

        } catch (IOException e) {
            System.out.println("Erro: Não foi possível definir dificuldade. Reinicie o jogo e tente novamente");
        }

        teclado.close();
    }
}