package br.inatel.robotbulls;

import br.inatel.robotbulls.classes.Lutador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        int dificuldade;

        do {
            System.out.println("\nEscolha a dificuldade:");
            System.out.println(" - Fácil   [1]");
            System.out.println(" - Médio   [2]");
            System.out.println(" - Difícil [3]");

            System.out.print("\nEscolha: ");
            dificuldade = teclado.nextInt();

        } while ((dificuldade > 3) || (dificuldade < 1));


        Path arquivo = Paths.get("src/br/inatel/robotbulls/arquivos/dificuldades.txt");

        try {
            List<String> lista = Files.readAllLines(arquivo);
            String[] a = lista.get(dificuldade - 1).split(",");

            Lutador.setReducaoEstamina(Integer.parseInt(a[1]));
            Lutador.setReducaoVida(Integer.parseInt(a[2]));

            System.out.println("\nDificuldade definida para " + a[0]);

        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }

    }
}