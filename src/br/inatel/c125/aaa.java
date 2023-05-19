package br.inatel.c125;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class aaa {
    public static void main(String[] args) {
        Path dificuldades = Paths.get("src/br/inatel/robotbulls/arquivos/dificuldades.txt");
        List<String> lista;

        try {
            lista = Files.readAllLines(dificuldades);

            String[] a = lista.get(0).split(",");

        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }


    }
}
