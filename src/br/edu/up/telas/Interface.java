package br.edu.up.telas;

import br.edu.up.controles.CombinaDados;
import br.edu.up.dol.CSVManager;
import br.edu.up.modelos.Pessoa;

import java.util.List;
import java.util.Scanner;

public class Interface {

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("Menu:");
            System.out.println("1. Combinar dados");
            System.out.println("2. Imprimir dados combinados");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    CombinaDados.combinar();
                    break;
                case 2:
                    imprimirDadosCombinados();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static void imprimirDadosCombinados() {
        // Implementação para imprimir os dados combinados do arquivo
        String resultadoPath = "src/br/edu/up/dol/PessoasComEndereco.csv";
        List<String[]> dadosCombinados = CSVManager.readCSV(resultadoPath);

        if (dadosCombinados.isEmpty()) {
            System.out.println("Não há dados combinados para imprimir.");
        } else {
            System.out.println("Dados combinados:");
            for (String[] linha : dadosCombinados) {
                System.out.println(linha[0] + ";" + linha[1] + ";" + linha[2] + ";" + linha[3]);
            }
        }
    }
}
