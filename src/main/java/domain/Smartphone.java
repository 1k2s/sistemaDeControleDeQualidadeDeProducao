package domain;

public class Smartphone extends ProdutoEletronico {

    private int capacidadeBateriaNominal;
    private int capacidadeBateriaReal;
    private boolean conectividade5G;

    /*Constructors*/
    public Smartphone(int codigo, String nome, String lote, int tensaoNominal, Double tensaoReal, boolean ligando, int capacidadeBateriaNominal, int capacidadeBateriaReal, boolean conectividade5G) {
        super(codigo, nome, lote, tensaoNominal, tensaoReal, ligando);
        this.capacidadeBateriaNominal = capacidadeBateriaNominal;
        this.capacidadeBateriaReal = capacidadeBateriaReal;
        this.conectividade5G = conectividade5G;
    }

    public Smartphone() {};

    @Override
    public String inspecionar() {

        String resultadoInspecao = "";
        double margemAceitavel = 0.10;

        /*Teste de tensão e funcionalidade herdado da classe pai*/
        String resultadoTesteTensaoEFuncionalidade = super.inspecionar();

        /*Teste da bateria e conectividade5g*/
        String resultadoTesteCapacidadeBateria = testeCapacidadeBateria(this.capacidadeBateriaNominal, this.capacidadeBateriaReal, margemAceitavel);


        if(resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && resultadoTesteCapacidadeBateria.equals("Aprovado") && this.conectividade5G) {resultadoInspecao = "Inspeção Aprovada!";}
        else if(!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && resultadoTesteCapacidadeBateria.equals("Aprovado") && this.conectividade5G) {resultadoInspecao = "Inspeção Reprovada. " + resultadoTesteTensaoEFuncionalidade;}
        else if(resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !resultadoTesteCapacidadeBateria.equals("Aprovado") && this.conectividade5G) {resultadoInspecao = "Inspeção Reprovada. " + resultadoTesteCapacidadeBateria;}
        else if(!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !resultadoTesteCapacidadeBateria.equals("Aprovado") && !this.conectividade5G) {resultadoInspecao = "Inspeção Reprovada. Todos os testes foram reprovados";}
        else if(resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && resultadoTesteCapacidadeBateria.equals("Aprovado") && !this.conectividade5G) {resultadoInspecao = "Inspeção Reprovada. Teste de conectividade falhou!";}
        else if(!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && resultadoTesteCapacidadeBateria.equals("Aprovado") && !this.conectividade5G) {resultadoInspecao = "Resultado Inspeção: Reprovado. Teste de conectividade falhou." + resultadoTesteTensaoEFuncionalidade;}
        else if(resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !resultadoTesteCapacidadeBateria.equals("Aprovado") && !this.conectividade5G) {resultadoInspecao = "Resultado Inspeção: Reprovado. Teste de conectividade falhou." + resultadoTesteCapacidadeBateria;}
        else if(!resultadoTesteTensaoEFuncionalidade.equals("Aprovado") && !resultadoTesteCapacidadeBateria.equals("Aprovado") && this.conectividade5G) {resultadoInspecao = "Resultado Inspeção: Reprovado. " + resultadoTesteCapacidadeBateria + ". " + resultadoTesteTensaoEFuncionalidade;}

        return resultadoInspecao;
    }


    /*Método interno*/
    public String testeCapacidadeBateria(int capacidadeBateriaNominal, int capacidadeBateriaReal, double margemAceitavel) {
        String teste = "";

        double limiteSuperior = capacidadeBateriaNominal * (1 + margemAceitavel);
        double limiteInferior = capacidadeBateriaNominal * (1 - margemAceitavel);

        if (capacidadeBateriaReal >= limiteInferior && capacidadeBateriaReal <= limiteSuperior) {
            teste =  "Aprovado";
        } else if (capacidadeBateriaReal < limiteInferior){
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
