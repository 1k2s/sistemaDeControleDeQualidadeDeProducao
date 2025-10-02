import domain.Produto;
import domain.Smartphone;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static Scanner scan = new Scanner(System.in);

    private static ArrayList<Produto> listaProdutos = new ArrayList<>();


    public static void main(String[] args) {

        Smartphone iphone = new Smartphone(1,"iphoneX", "123", 110, 3500);
        Smartphone iphone2 = new Smartphone(2,"iphoneXX", "123DSDSD", 220, 3500);

        listaProdutos.add(iphone);
        listaProdutos.add(iphone2);

        boolean sair = false;

        while(!sair) {

            int opcao = lerOpcaoMenu();

            switch(opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> visualizarProduto();
                case 3 -> editarProduto();
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
                
                === CONTROLE DE QUALIDADE ===
                
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
    };


    /*Metodos de Navegação*/
    private static void cadastrarProduto(){

        boolean sair = false;

        while(!sair) {

            int opcao = -1;

            int tipoProduto = lerTipoProduto();

            switch(tipoProduto) {
                case 1 -> {
                    int codigo = lerCodigoProduto();
                    String nome = lerNomeProduto();
                    String lote = lerLoteProduto();
                    int tensaoNominal = lerTensaoNominal();
                    int capacidadeNominal = lerCapacidadeBateriaNominal();

                    try {
                        Smartphone newSmartphone = new Smartphone(codigo, nome, lote, tensaoNominal, capacidadeNominal);

                        listaProdutos.add(newSmartphone);

                        System.out.println();
                        System.out.println("=== PRODUTO CADASTRADO COM SUCESSO! ===");

                        sair = true;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 0 -> sair = true;
                default -> {
                    System.out.println();
                    System.out.println("Opção Inválida!");
                }

            }
        }
    };

    private static void visualizarProduto(){

        int opcao = -1;
        boolean sair = false;

        while(!sair) {
            System.out.println();
            System.out.println("=== PRODUTOS CADASTRADOS: ===");

            for (Produto produto : listaProdutos) {
                System.out.println(produto.getDetalhes() + "\n");
            }

            System.out.print("""
                    0. Sair da Visualização!
                    Resposta:""");
            String entrada = scan.nextLine().trim();

            try {
                opcao = Integer.parseInt(entrada);

                if (opcao == 0) {
                    sair = true;
                } else {
                    System.out.println();
                    System.out.println("### OPÇÃO INVÁLIDA ###");
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("### OPÇÃO INVÁLIDA ###");
            }

        }
    };

    private static void editarProduto(){




    }








    /*Metodos de Leitura*/
    private static int lerTipoProduto() {
        int opcao = -1;
        boolean entradaValida = false;

        while(!entradaValida) {
            System.out.print("""
                
                Selecione o produto:
                
                1. SmartPhone
                2. Alimento Perecível
                
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
    };

    private static String lerNomeProduto() {

        String nome = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println();
            System.out.print("Digite o nome do Produto: ");
            nome = scan.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println(" O nome não pode estar vazio.");
            } else if (nome.length() < 2) {
                System.out.println(" O nome deve ter pelo menos 2 caracteres.");
            } else if (nome.matches("[\\p{Punct}]+")) {
                System.out.println(" O nome não deve conter pontuação");
            } else {
                entradaValida = true;
            }
        }

        return nome;
    };

    private static String lerLoteProduto() {

        String lote = "";
        boolean entradaValida = false;

        while (!entradaValida) {

            System.out.println();
            System.out.print("Digite o lote do Produto: ");
            lote = scan.nextLine().trim();

            if (lote.isEmpty()) {
                System.out.println(" O lote não pode estar vazio.");
            } else if (lote.length() < 2) {
                System.out.println(" O lote deve ter pelo menos 2 caracteres.");
            } else if (lote.matches("[\\p{Punct}]+")) {
                System.out.println(" O lote não deve conter pontuação");
            } else {
                entradaValida = true;
            }
        }

        return lote;
    };

    private static int lerCodigoProduto() {

        int codigo = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println();
            System.out.print("Digite o código do Produto:");

            try {
                String entrada = scan.nextLine().trim();//trim() remove espaços nas extremidades
                codigo = Integer.parseInt(entrada);

                if (codigo >= 1) {
                    entradaValida = true;
                } else {
                    System.out.println();
                    System.out.println("#### Por favor, digite apenas números positivos! ####");
                }

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("#### Por favor, digite apenas números inteiros. ####");
            };
        }

        return codigo;
    };

    private static int lerTensaoNominal() {

        int tensaoNominal = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println();
            System.out.print("Digite a Tensão Nominal do Produto:");

            try {
                String entrada = scan.nextLine().trim();//trim() remove espaços nas extremidades
                tensaoNominal = Integer.parseInt(entrada);

                if (tensaoNominal >= 1) {
                    entradaValida = true;
                } else {
                    System.out.println();
                    System.out.println("#### Por favor, digite apenas números positivos! ####");
                }

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("#### Por favor, digite apenas números inteiros. ####");
            };
        }

        return tensaoNominal;
    };

    private static int lerCapacidadeBateriaNominal() {

        int capacidadeNominal = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println();
            System.out.print("Digite a Capacidade da Bateria Nominal do Produto:");

            try {
                String entrada = scan.nextLine().trim();//trim() remove espaços nas extremidades
                capacidadeNominal = Integer.parseInt(entrada);

                if (capacidadeNominal >= 1) {
                    entradaValida = true;
                } else {
                    System.out.println();
                    System.out.println("#### Por favor, digite apenas números positivos! ####");
                }

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("#### Por favor, digite apenas números inteiros. ####");
            };
        }

        return capacidadeNominal;
    };

    private static int lerProdutoEditar() {

        int opcao = -1;
        boolean sair = false;

        System.out.println("=== SELECIONE O PRODUTO ==");

        while(!sair) {

            Produto produto = null;

            /*Printando os produtos*/
            for (int i = 0; i < listaProdutos.size(); i++) {

                int numeroProduto = 1;
                produto = listaProdutos.get(i);

                System.out.printf("%d. %d - %s \n", numeroProduto, produto.getCodigo(), produto.getNome());
            }

            System.out.print("Resposta: ");
            String entrada = scan.nextLine().trim();

            try {
                opcao = Integer.parseInt(entrada);

                if (produto instanceof Smartphone) {
                    System.out.println("""
                            === SELECIONE A INFORMAÇÃO QUE DESEJA ALTERAR ===
                            
                            1. Código
                            2. Nome
                            3. Lote
                            4. Tensão Nominal
                            5. Capacidade Bateria Nominal
                            
                            """
                    );
                    entrada = scan.nextLine().trim();

                    try {

                    }


                    switch ()
                } else {
                    System.out.println("O produto Selecionado é do tipo Alimentício");
                }
            } catch (NumberFormatException e) {
                System.out.println("### Por favor. Digite apenas números inteiros ###");
            }
        }
    };


}
