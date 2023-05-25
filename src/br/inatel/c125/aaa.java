package br.inatel.c125;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class aaa {
    public static void main(String[] args) {

        List <Integer> lista = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i< 100; i++) {
            int escolha = r.nextInt(0, 2);
            System.out.println(escolha);
        }


    }
}
