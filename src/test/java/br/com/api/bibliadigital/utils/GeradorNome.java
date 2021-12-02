package br.com.api.bibliadigital.utils;

import java.util.concurrent.ThreadLocalRandom;

public class GeradorNome {

    public static String gerarNome() {
        ThreadLocalRandom gerador = ThreadLocalRandom.current();

        int tamanhoNome = gerador.nextInt(3, 10);
        int tamanhoSobrenome = gerador.nextInt(3, 10);
        char primeiraLetraNome = (char) gerador.nextInt(65, 90);

        StringBuilder nome = new StringBuilder().append(primeiraLetraNome);
        StringBuilder sobreNome = new StringBuilder().append(primeiraLetraNome);

        for (int i = 1; i < tamanhoNome; i++) {
            char letra = (char) gerador.nextInt(97, 122);
            nome.append(letra);
        }

        for (int i = 1; i < tamanhoSobrenome; i++) {
            char letra = (char) gerador.nextInt(97, 122);
            sobreNome.append(letra);
        }
        return nome + "_" + sobreNome;
    }
}
