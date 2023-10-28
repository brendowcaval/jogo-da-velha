package jogo.jogodavelha;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JogoDaVelha {

    boolean jogoEmAndamento = true;
    boolean jogadorUm = true, jogadorDois = false;
    int jogadorUmPontos = 0, jogadorDoisPontos = 0;
    String[][] campo = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

    // exibindo o campo de jogo da velha no console
    public void exibindoJogo() throws InterruptedException {

        while (jogoEmAndamento) {
            clearScreen();
            System.out.println("Jogador 1" + "    vs    " + "Jogador 2");
            System.out.println(jogadorUmPontos + "        " + jogadorDoisPontos);
            for (int l = 0; l < 3; l++) {
                for (int c = 0; c < 3; c++) {
                    System.out.print("|" + campo[l][c]);
                }
                System.out.print("|\n");
            }
            selecionandoCampo(jogadorUm, jogadorDois);
        }
    }

    // jogadores vão selecionar as posições no campo para jogar
    public void selecionandoCampo(boolean vezDoJogadorUm, boolean vezDoJogadorDois) throws InterruptedException {

        try {
            Scanner entrada = new Scanner(System.in);
            int l, c;
            String vezDoJogador = vezDoJogadorUm != true ? "Jogador 2" : "Jogador 1";

            System.out.println(vezDoJogador + ", sua vez (informe linha e coluna) : ");
            l = entrada.nextInt();
            c = entrada.nextInt();

            clearScreen();
            if (campo[l][c] != " ") {
                System.out.println("Não é possivel selecionar esse campo.");
                Thread.sleep(3000);
            } else {
                if (vezDoJogadorUm) {
                    campo[l][c] = "X";
                    jogadorUm = false;
                    jogadorDois = true;
                } else {
                    campo[l][c] = "O";
                    jogadorUm = true;
                    jogadorDois = false;
                }
            }

            verificandoVitoriaOuEmpate();
        } catch (InputMismatchException e) {
            System.out.println("Somente é válido inserir valores entre 0 e 2.");
            Thread.sleep(3000);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Somente é válido inserir valores entre 0 e 2.");
            Thread.sleep(3000);
        }

    }

    public void verificandoVitoriaOuEmpate() {
        int i = 0;

        while (i < 3) {
            // verificando sequencia por cada linha da matriz
            if ((campo[i][0] != " ") && (campo[i][0] == campo[i][1]) && (campo[i][0] == campo[i][2])) {
                exibindoVencedorOuEmpate(campo[i][0]);

            }
            i++;
        }
        i--;
        while (-1 < i) {
            // verificando sequencia por cada coluna da matriz
            if ((campo[0][i] != " ") && (campo[0][i] == campo[1][i]) && (campo[0][i] == campo[2][i])) {
                exibindoVencedorOuEmpate(campo[0][i]);

            }
            i--;
        }
        i++;

        // verificando sequencia na diagonal a direita
        if ((campo[0][0] != " ") && (campo[0][0] == campo[1][1]) && (campo[0][0] == campo[2][2])) {
            exibindoVencedorOuEmpate(campo[0][0]);

        }
        // verificando sequencia na diagonal a esquerda
        if ((campo[0][2] != " ") && (campo[0][2] == campo[1][1]) && (campo[0][2] == campo[2][0])) {
            exibindoVencedorOuEmpate(campo[0][2]);

        }

        // verificando se deu empate
        boolean verificandoEmpate = true;
        int z = 0;
        while (verificandoEmpate) {
            if ((campo[0][z] != " ") && (campo[1][z] != " ") && (campo[2][z] != " ")) {
                z++;
                if ((campo[0][z] != " ") && (campo[1][z] != " ") && (campo[2][z] != " ")) {
                    z++;
                    if ((campo[0][z] != " ") && (campo[1][z] != " ") && (campo[2][z] != " ")) {
                        z++;
                        verificandoEmpate = false;
                        exibindoVencedorOuEmpate("empate");

                    } else {
                        verificandoEmpate = false;
                        z = 0;
                    }
                } else {
                    verificandoEmpate = false;
                    z = 0;
                }

            } else {
                verificandoEmpate = false;
            }
        }

    }

    public void exibindoVencedorOuEmpate(String mensagem) {

        try {
            clearScreen();
            switch (mensagem) {
                case "X":
                    System.out.println("Jogador 1 ganhou!");
                    break;
                case "O":
                    System.out.println("Jogador 2 ganhou!");
                    break;
                case "empate":
                    System.out.println("Deu empate!");
                    break;
            }

            for (int l = 0; l < 3; l++) {
                for (int c = 0; c < 3; c++) {
                    System.out.print("|" + campo[l][c]);
                }
                System.out.print("|\n");
            }
            Thread.sleep(2000);
            clearScreen();
            // determinando o campo todo vazio após empate ou vitória
            for (int l = 0; l < 3; l++) {
                campo[l][0] = " ";
                campo[l][1] = " ";
                campo[l][2] = " ";
            }

            if (mensagem == "X") {
                jogadorUmPontos++;
            } else if (mensagem == "O") {
                jogadorDoisPontos++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // método para limpar console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
