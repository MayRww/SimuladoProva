package br.edu.up.controles;

import br.edu.up.dol.CSVManager;
import br.edu.up.modelos.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class CombinaDados {

    public static void combinar() {
        String pessoasPath = "src/br/edu/up/dol/Pessoas.csv";
        String enderecosPath = "src/br/edu/up/dol/Enderecos.csv";
        String resultadoPath = "src/br/edu/up/dol/PessoasComEndereco.csv";

        List<String[]> pessoasData = CSVManager.readCSV(pessoasPath);
        List<String[]> enderecosData = CSVManager.readCSV(enderecosPath);

        List<Pessoa> pessoasComEndereco = new ArrayList<>();

        // Iterar sobre as pessoas
        for (String[] pessoa : pessoasData) {
            int codigoPessoa = Integer.parseInt(pessoa[0]);
            String nomePessoa = pessoa[1];

            // Lista para armazenar múltiplos endereços da mesma pessoa
            List<String[]> enderecosDaPessoa = new ArrayList<>();

            // Encontrar todos os endereços correspondentes à pessoa
            for (String[] endereco : enderecosData) {
                int codigoEndereco = Integer.parseInt(endereco[2]);
                if (codigoEndereco == codigoPessoa) {
                    enderecosDaPessoa.add(endereco);
                }
            }

            // Criar objetos Pessoa com os dados combinados
            for (String[] endereco : enderecosDaPessoa) {
                String rua = endereco[0];
                String cidade = endereco[1];
                Pessoa pessoaComEndereco = new Pessoa(codigoPessoa, nomePessoa, rua, cidade);
                pessoasComEndereco.add(pessoaComEndereco);
            }
        }

        // Escrever os dados combinados no arquivo CSV
        List<String> linhasCSV = new ArrayList<>();
        linhasCSV.add("codigo;nome;rua;cidade");
        for (Pessoa pessoa : pessoasComEndereco) {
            linhasCSV.add(pessoa.getCodigo() + ";" + pessoa.getNome() + ";" +
                          (pessoa.getRua() != null ? pessoa.getRua() : "null") + ";" +
                          (pessoa.getCidade() != null ? pessoa.getCidade() : "null"));
        }

        CSVManager.writeCSV(resultadoPath, linhasCSV);
        System.out.println("Dados combinados e salvos em PessoasComEndereco.csv");
    }
}
