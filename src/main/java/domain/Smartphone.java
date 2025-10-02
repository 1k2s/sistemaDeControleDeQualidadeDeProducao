package domain;

import java.util.ArrayList;
import java.util.Random;

public class Smartphone extends ProdutoEletronico {

    private int capacidadeBateriaNominal;
    private double capacidadeBateriaReal;
    private boolean conectividade5G;

    Random random = new Random();

    /*Constructors*/
    public Smartphone(int codigo, String nome, String lote,int tensaoNominal, int capacidadeBateriaNominal) {
        super(codigo, nome, lote, tensaoNominal);
        this.capacidadeBateriaNominal = capacidadeBateriaNominal;

        /*this.capacidadeBateriaReal = this.capacidadeBateriaNominal;*/

        //Setando atributos random
        this.capacidadeBateriaReal = Math.round((capacidadeBateriaNominal - 1000) + (Math.random() * ((capacidadeBateriaNominal + 1000) - (capacidadeBateriaNominal - 1000))));
        this.conectividade5G = random.nextBoolean();
    }

    public Smartphone() {};

    @Override
    public String inspecionar() {

        String resultadoInspecao = "";

        /*Armazenando os retornos para não precisar ficar chamando os métodos*/
        String resultadoTensaoEFuncionalidade = super.inspecionar();
        String resultadoBateria = testeCapacidadeBateria();
        String resultadoConectividade = testeConectividade5g();


        //Verificando se todos os retornos são aprovados(condição especial)
        if (resultadoTensaoEFuncionalidade.equals("Aprovado") &&
            resultadoBateria.equals("Aprovado") &&
            resultadoConectividade.equals("Aprovado")) {

            resultadoInspecao = "Inspeção Aprovada!";
            setAprovadoInspecao(true);

        } else {
            // Lista que vai acumular as mensagens de erro
            ArrayList<String> erros = new ArrayList<>();

            // Verificando cada resultado e adicionando a lista de erros
            if (!resultadoTensaoEFuncionalidade.equals("Aprovado")) { erros.add(resultadoTensaoEFuncionalidade); }
            if (!resultadoBateria.equals("Aprovado")) {erros.add(resultadoBateria);}
            if (!resultadoConectividade.equals("Aprovado")) {erros.add(resultadoConectividade);}

            // Construindo as mensagems
            if (erros.size() == 3) {
                resultadoInspecao = "Inspeção Reprovada:\nTodos os testes falharam!";
            } else if (erros.size() == 1) {
                resultadoInspecao = "Inspeção Reprovada:\n" + erros.get(0);
            } else {
                resultadoInspecao = "Inspeção Reprovada:\n" + String.join("\n", erros);
            }
        }


        /*Estrutura com if*/
        /*if ( resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && testeCapacidadeBateria().equals("Aprovado") && testeConectividade5g().equals("Aprovado")) {
            resultadoInspecao = resultadoTesteTensaoEFuncionalidade;
            setAprovadoInspecao(true);
        } else if (!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !testeCapacidadeBateria().equals("Aprovado") && !testeConectividade5g().equals("Aprovado")) {
            resultadoInspecao = "Reprovado. Todos os testes falharam";
        } else if (!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && testeCapacidadeBateria().equals("Aprovado") && testeConectividade5g().equals("Aprovado")) {
            resultadoInspecao = "Reprovado. " + resultadoTesteTensaoEFuncionalidade;
        } else if (resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !testeCapacidadeBateria().equals("Aprovado") && testeConectividade5g().equals("Aprovado")) {
            resultadoInspecao = "Reprovado. " + testeCapacidadeBateria();
        } else if (resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && testeCapacidadeBateria().equals("Aprovado") && !testeConectividade5g().equals("Aprovado")) {
            resultadoInspecao = "Reprovado. " + testeConectividade5g();
        } else if (resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !testeCapacidadeBateria().equals("Aprovado") && !testeConectividade5g().equals("Aprovado")) {
            resultadoInspecao = "Reprovado:\n" + testeCapacidadeBateria() + "\n" + testeConectividade5g();
        } else if (!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !testeCapacidadeBateria().equals("Aprovado") && testeConectividade5g().equals("Aprovado")) {
            resultadoInspecao = "Reprovado:\n" + resultadoTesteTensaoEFuncionalidade + "\n" + testeCapacidadeBateria();
        } else if (!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && testeCapacidadeBateria().equals("Aprovado") && !testeConectividade5g().equals("Aprovado")) {
            resultadoInspecao = "Reprovado:\n" + resultadoTesteTensaoEFuncionalidade + "\n" + testeConectividade5g();
        }*/


        return resultadoInspecao;
    };


    /*Metodos internos*/
    public String testeCapacidadeBateria() {
        String teste = "";

        double margemAceitavel = 0.10;

        double limiteSuperior = this.capacidadeBateriaNominal * (1 + margemAceitavel);
        double limiteInferior = this.capacidadeBateriaNominal * (1 - margemAceitavel);

        if (this.capacidadeBateriaReal >= limiteInferior && this.capacidadeBateriaReal <= limiteSuperior) {
            teste =  "Aprovado";
        } else if (this.capacidadeBateriaReal < limiteInferior){
            teste = "Capacidade aferida está abaixo do limite mínimo";
        } else {
            teste = "Capacidade aferida está acima do limite máximo";
        }

        return teste;
    }
    public String testeConectividade5g(){
        return this.conectividade5G ? "Aprovado" : "Teste de conectividade falhou";
    };

    public String getDetalhesInspecao() {
      return "Valores Inspeção:" + "\n" +
              "Tensão Nominal: " + getTensaoNominal() + "\n" +
              "Tensão Real: " + getTensaoReal() + "\n" +
              "Funcional: " + isLigando() + "\n" +
              "Capacidade Bateria Nominal: " + getCapacidadeBateriaNominal() + "\n" +
              "Capacidade Bateria Real: " + getCapacidadeBateriaReal() + "\n" +
              "Conectividade 5G: " + isConectividade5G() + "\n";
    };




    /*Getters and Setters*/
    public int getCapacidadeBateriaNominal() {return capacidadeBateriaNominal;}
    public void setCapacidadeBateriaNominal(int capacidadeBateriaNominal) {this.capacidadeBateriaNominal = capacidadeBateriaNominal;}
    public double getCapacidadeBateriaReal() {return capacidadeBateriaReal;}
    public void setCapacidadeBateriaReal(int capacidadeBateriaReal) {this.capacidadeBateriaReal = capacidadeBateriaReal;}
    public boolean isConectividade5G() {return conectividade5G;}
    public void setConectividade5G(boolean conectividade5G) {this.conectividade5G = conectividade5G;}
}
