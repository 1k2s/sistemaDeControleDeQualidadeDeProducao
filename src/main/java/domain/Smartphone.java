package domain;

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

        //Setando atributos random
        this.capacidadeBateriaReal = (capacidadeBateriaNominal - 1000) + (Math.random() * (capacidadeBateriaNominal + 1000) - (capacidadeBateriaNominal - 1000));
        this.conectividade5G = random.nextBoolean();
    }

    public Smartphone() {};
s
    @Override
    public String inspecionar() {

        String resultadoInspecao = "";

        /*Teste de tensão e funcionalidade herdado da classe pai*/
        String resultadoTesteTensaoEFuncionalidade = super.inspecionar();



        if(resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && resultadoTesteCapacidadeBateria.equals("Aprovado") && this.conectividade5G) {resultadoInspecao = "Inspeção Aprovada!";}

        else if(!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && resultadoTesteCapacidadeBateria.equals("Aprovado") && this.conectividade5G) {
            resultadoInspecao = "Inspeção Reprovada. " + resultadoTesteTensaoEFuncionalidade;
        }

        else if(resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !resultadoTesteCapacidadeBateria.equals("Aprovado") && this.conectividade5G) {resultadoInspecao = "Inspeção Reprovada. " + resultadoTesteCapacidadeBateria;}
        else if(!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !resultadoTesteCapacidadeBateria.equals("Aprovado") && !this.conectividade5G) {resultadoInspecao = "Inspeção Reprovada. Todos os testes foram reprovados";}
        else if(resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && resultadoTesteCapacidadeBateria.equals("Aprovado") && !this.conectividade5G) {resultadoInspecao = "Inspeção Reprovada. Teste de conectividade falhou!";}
        else if(!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && resultadoTesteCapacidadeBateria.equals("Aprovado") && !this.conectividade5G) {resultadoInspecao = "Resultado Inspeção: Reprovado. Teste de conectividade falhou." + resultadoTesteTensaoEFuncionalidade;}
        else if(resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !resultadoTesteCapacidadeBateria.equals("Aprovado") && !this.conectividade5G) {resultadoInspecao = "Resultado Inspeção: Reprovado. Teste de conectividade falhou." + resultadoTesteCapacidadeBateria;}
        else if(!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !resultadoTesteCapacidadeBateria.equals("Aprovado") && this.conectividade5G) {resultadoInspecao = "Resultado Inspeção: Reprovado. " + resultadoTesteCapacidadeBateria + ". " + resultadoTesteTensaoEFuncionalidade;}

        return resultadoInspecao;
    }


    /*Método interno*/
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




    /*Getters and Setters*/
    public int getCapacidadeBateriaNominal() {return capacidadeBateriaNominal;}
    public void setCapacidadeBateriaNominal(int capacidadeBateriaNominal) {this.capacidadeBateriaNominal = capacidadeBateriaNominal;}
    public double getCapacidadeBateriaReal() {return capacidadeBateriaReal;}
    public void setCapacidadeBateriaReal(int capacidadeBateriaReal) {this.capacidadeBateriaReal = capacidadeBateriaReal;}
    public boolean isConectividade5G() {return conectividade5G;}
    public void setConectividade5G(boolean conectividade5G) {this.conectividade5G = conectividade5G;}
}
