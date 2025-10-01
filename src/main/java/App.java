import domain.ProdutoEletronico;

import java.util.Scanner;

public class App {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        double valorAleatorio = 104.0 + (Math.random() * (116.0 - 104.0));

        System.out.println(valorAleatorio);

        boolean sair = false;

        while(!sair) {

            int opcao = lerOpcaoMenu();

            switch(opcao) {
                case 1 -> System.out.println("Cadastrar Produtos");
                case 2 -> System.out.println("Visualizar Produtos");
                case 3 -> System.out.println("Editar Produtos");
                case 4 -> System.out.println("Inspecionar Produtos");
                case 0 -> sair = true;
                default -> {
                    System.out.println();
                    System.out.println("Opção Inválida!");
                }
            }
        }

        scan.close();
        System.out.println("Sistema Encerrado!");
    }


    private static int lerOpcaoMenu() {

        int opcao = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("""  
                
                === SISTEMA DE FUNCIONÁRIOS ===
                
                1. Cadastrar Produtos
                2. Visualizar Produtos
                3. Editar Produtos
                4. Inspecionar Produtos
                0. Sair
                
                Resposta:""");

            try {
                String entrada = scan.nextLine().trim();
                opcao = Integer.parseInt(entrada);
                entradaValida = true;

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Por favor, digite apenas números.\n");
            };
        };

        return opcao;
    }

    private static int cadastrarProduto(){

        boolean sair = false;

        while(!sair) {

            int opcao = -1;
            int tipoProduto = lerProduto();

            switch(tipoProduto) {
                case 1 -> {
                    String nome = lerNomeProduto();
                }

            }
        }

        return 0;
    };

    private static int lerProduto() {
        int opcao = -1;
        boolean entradaValida = false;

        while(!entradaValida) {
            System.out.println("""
                Selecione o produto:
                
                1. SmartPhone
                2. Alimento Perecível
                
                Resposta:""");
        };

        try {
            String entrada = scan.nextLine().trim();
            opcao = Integer.parseInt(entrada);
            entradaValida = true;
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Por favor, digite apenas números.\n");
        };

        return opcao;
    }

    private static String lerNomeProduto() {

        String nome = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Digite o nome do Produto: ");
            nome = scan.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println(" O nome não pode estar vazio.");
            } else if (nome.length() < 2) {
                System.out.println(" O nome deve ter pelo menos 2 caracteres.");
            } else if (!nome.matches("")) {
                System.out.println(" O nome não deve conter pontuação");
            } else {
                entradaValida = true;
            }
        }

        return nome;
    }
}
