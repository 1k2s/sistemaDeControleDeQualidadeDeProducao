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
                case 4 -> inspecionarProdutos();
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

    // private static void editarProduto(){
    // }

    private static void editarProduto() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Não há produtos cadastrados para editar.");
            return;
        }
    
        System.out.println("\n=== LISTA DE PRODUTOS ===");
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            System.out.printf("%d. %d - %s\n", i + 1, produto.getCodigo(), produto.getNome());
        }
    
        System.out.print("Selecione o número do produto que deseja editar (0 para sair): ");
        String entrada = scan.nextLine().trim();
    
        try {
            int escolha = Integer.parseInt(entrada);
    
            if (escolha == 0) {
                return;
            }
    
            if (escolha < 1 || escolha > listaProdutos.size()) {
                System.out.println("Opção inválida!");
                return;
            }
    
            Produto produtoSelecionado = listaProdutos.get(escolha - 1);
    
            if (produtoSelecionado instanceof Smartphone smartphone) {
                editarSmartphone(smartphone);
            } else {
                System.out.println("Esse tipo de produto ainda não possui edição implementada.");
            }
    
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite apenas números.");
        }
    }

    private static void inspecionarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("\nNão há produtos cadastrados para inspecionar!");
            return;
        }
    
        System.out.println("\n=== INSPEÇÃO DE PRODUTOS ===");
    
        for (Produto produto : listaProdutos) {
            System.out.println("\nProduto: " + produto.getDetalhes());
    
            if (produto instanceof Smartphone smartphone) {
                boolean aprovado = true;
    
                // Critérios de inspeção
                if (smartphone.getTensaoNominal() < 100 || smartphone.getTensaoNominal() > 240) {
                    System.out.println("⚠️ Falha: Tensão fora do padrão (100V - 240V)");
                    aprovado = false;
                }
    
                if (smartphone.getCapacidadeBateriaNominal() < 1000 || smartphone.getCapacidadeBateriaNominal() > 6000) {
                    System.out.println("⚠️ Falha: Capacidade da bateria fora do padrão (1000mAh - 6000mAh)");
                    aprovado = false;
                }
    
                if (aprovado) {
                    System.out.println("✅ Produto APROVADO no teste de qualidade!");
                } else {
                    System.out.println("❌ Produto REPROVADO no teste de qualidade!");
                }
            } else {
                System.out.println("⚠️ Tipo de produto ainda sem critérios de inspeção definidos.");
            }
        }
    
        System.out.println("\nFim da inspeção!\n");
    }
    
    
    private static void editarSmartphone(Smartphone smartphone) {
        boolean sair = false;
    
        while (!sair) {
            System.out.printf(""" 
                
                === EDITAR SMARTPHONE ===
                Produto atual: %s
                
                1. Código (atual: %d)
                2. Nome (atual: %s)
                3. Lote (atual: %s)
                4. Tensão Nominal (atual: %d)
                5. Capacidade Bateria (atual: %d)
                0. Voltar
                
                Resposta: """,
                smartphone.getNome(),
                smartphone.getCodigo(),
                smartphone.getNome(),
                smartphone.getLote(),
                smartphone.getTensaoNominal(),
                smartphone.getCapacidadeBateriaNominal()
            );
    
            String entrada = scan.nextLine().trim();
    
            try {
                int opcao = Integer.parseInt(entrada);
    
                switch (opcao) {
                    case 1 -> smartphone.setCodigo(lerCodigoProduto());
                    case 2 -> smartphone.setNome(lerNomeProduto());
                    case 3 -> smartphone.setLote(lerLoteProduto());
                    case 4 -> smartphone.setTensaoNominal(lerTensaoNominal());
                    case 5 -> smartphone.setCapacidadeBateriaNominal(lerCapacidadeBateriaNominal());
                    case 0 -> sair = true;
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
            }
        }
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

                    };


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
