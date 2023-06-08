package br.inatel.c125.outros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Configuracoes {

    private static int modificadorEstamina, modificadorForca;
    private static int reducaoEstaminaLutador, reducaoFinisherLutador, reducaoSignatureLutador;
    private static int reducaoSignatureSuporte;

    public static int getModificadorEstamina() {
        return modificadorEstamina;
    }

    public static int getModificadorForca() {
        return modificadorForca;
    }

    public static int getReducaoEstaminaLutador() {
        return reducaoEstaminaLutador;
    }

    public static int getReducaoFinisherLutador() {
        return reducaoFinisherLutador;
    }

    public static int getReducaoSignatureLutador() {
        return reducaoSignatureLutador;
    }

    public static int getReducaoSignatureSuporte() {
        return reducaoSignatureSuporte;
    }

    public static void defineConfiguracoes() throws IOException {

        Path arquivoDificuldade = Paths.get("src/br/inatel/c125/arquivos/padroes/configuracoes.txt");
        List<String> configuracoes = Files.readAllLines(arquivoDificuldade);

        reducaoEstaminaLutador = Integer.parseInt(configuracoes.get(0).split("=")[1]);
        reducaoFinisherLutador = Integer.parseInt(configuracoes.get(1).split("=")[1]);
        reducaoSignatureLutador = Integer.parseInt(configuracoes.get(2).split("=")[1]);

        reducaoSignatureSuporte = Integer.parseInt(configuracoes.get(3).split("=")[1]);
    }

    public static void defineDificuldade(Scanner teclado) throws IOException {

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

        modificadorForca = Integer.parseInt(a[2]);
        modificadorEstamina = Integer.parseInt(a[1]);
    }
}
