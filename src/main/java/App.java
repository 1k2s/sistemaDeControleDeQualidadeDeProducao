import domain.ProdutoEletronico;

import java.util.Scanner;

public class App {

    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

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
            }
        }

        return opcao;
    }

    private static int lerTipoProduto() {
        int opcao = -1;
        boolean entradaValida = false;

        while(!entradaValida) {
            System.out.println("""
                Selecione o tipo do produto:
                
                1. Produto Eletrônico
                2. Produto Alimentício
                
                Resposta:""");
        }

        try {
            String entrada = scan.nextLine().trim();
            opcao = Integer.parseInt(entrada);
            entradaValida = true;
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Por favor, digite apenas números.\n");
        }

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
