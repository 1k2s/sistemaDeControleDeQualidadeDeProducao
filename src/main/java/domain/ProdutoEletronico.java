package domain;

import java.util.Random;

public class ProdutoEletronico extends Produto {

    private int tensaoNominal; //especificado pelo fabricante
    private double tensaoReal; // tensão em que o dispositivo esta operando
    private boolean ligando;

    Random random = new Random();

    public ProdutoEletronico(int codigo, String nome, String lote, int tensaoNominal) {
        super(codigo, nome, lote);
        this.tensaoNominal = tensaoNominal;


        //Setando atributos random
        this.tensaoReal = this.tensaoNominal == 110.0
            ? (Math.round(103.0 + (Math.random() * (120.0 - 103.0)))) // 110
            : (Math.round(210.0 + (Math.random() * (210.0 - 230.0))));// 220

        this.ligando = random.nextBoolean();
    };

    public ProdutoEletronico() {};



    /*Sobrescrevendo métodos abstratos da classe super*/
    @Override
    public String getDetalhes() {
      return
        "-- Detalhes do produto --\n" +
        "Codigo: " + this.getCodigo() + "\n"+
        "Nome: " + this.getNome() + "\n";
    };

    @Override
    public String inspecionar() {

        String retornoInspecao = "";

        /*Resultado inspecionar*/
        if ( testeTensao().equals("Aprovado") && testeFuncionalidade().equals("Aprovado") ) {retornoInspecao = testeTensao(); this.setAprovadoTeste(true);}
        else if ( !testeTensao().equals("Aprovado") && !testeFuncionalidade().equals("Aprovado") ) {retornoInspecao = "Reprovado. Todos os testes foram reprovados";}
        else if ( testeTensao().equals("Aprovado") && !testeFuncionalidade().equals("Aprovado") ) {retornoInspecao = "Reprovado. " + testeFuncionalidade();}
        else if ( !testeTensao().equals("Aprovado") && testeFuncionalidade().equals("Aprovado") ) {retornoInspecao = "Reprovado. " + testeTensao();}

        return retornoInspecao;
    };


    /*Metodo interno*/
    private String testeTensao() {

        String teste = "";

        double margemAceitavel = this.tensaoNominal == 110 ? 0.05 : 0.10;

        double limiteSuperior = tensaoNominal * (1 + margemAceitavel);
        double limiteInferior = tensaoNominal * (1 - margemAceitavel);

        if (tensaoReal >= limiteInferior && tensaoReal <= limiteSuperior) {
            teste =  "Aprovado";
        } else if (tensaoReal < limiteInferior){
            teste = "Tensão aferida está abaixo do limite minimo";
        } else {
            teste = "Tensão aferida está acima do limite máximo";
        }

        return teste;
    };

    private String testeFuncionalidade() {
        return this.ligando ? "Aprovado" : "Teste de funcionalidade falhou!";
    };


    /*Getters and Setters*/
    public int getTensaoNominal() {return tensaoNominal;}

    public void setTensaoNominal(int tensaoNominal) {this.tensaoNominal = tensaoNominal;}

    public double getTensaoReal() {return tensaoReal;}

    public void setTensaoReal(double tensaoReal) {this.tensaoReal = tensaoReal;}

    public boolean isLigando() {return ligando;}

    public void setLigando(boolean ligando) {this.ligando = ligando;}
}
