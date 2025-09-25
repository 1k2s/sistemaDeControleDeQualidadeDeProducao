package domain;

public class ProdutoEletronico extends Produto {

    private int tensaoNominal; //especificado pelo fabricante
    private double tensaoReal; // tensão em que o dispositivo esta operando
    private boolean ligando;

    public ProdutoEletronico(int codigo, String nome, String lote, int tensaoNominal, Double tensaoReal, boolean ligando) {
        super(codigo, nome, lote);
        this.tensaoNominal = tensaoNominal;
        this.tensaoReal = tensaoReal;
        this.ligando = ligando;
    }

    public ProdutoEletronico() {}



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
        String retornoInspeção = "";
        int tensaoNominal = this.tensaoNominal;
        double tensaoReal = this.tensaoReal;

        double margemAceitavel110= 0.05;
        double margemAceitavel220= 0.10;


        /*Resultado tensão*/
        String resultadoTesteTensao = switch (tensaoNominal) {
            case 110 -> testeTensao(tensaoNominal, tensaoReal, margemAceitavel110);
            case 220 -> testeTensao(tensaoNominal, tensaoReal, margemAceitavel220);
            default -> "Falha na leitura da tensão nominal";
        };


        /*Resultado inspecionar*/
        if (resultadoTesteTensao.equals("Aprovado") && this.ligando) {retornoInspeção = "Resultado Inspeção: Aprovado"; this.setAprovadoTeste(true);}
        else if (resultadoTesteTensao.equals("Aprovado") && !this.ligando) {retornoInspeção = "Resultado Inspeção: Reprovado. Teste de funcionalidade falhou!";}
        else if (!resultadoTesteTensao.equals("Aprovado") && !this.ligando) {retornoInspeção = "Resultado Inspeção: Reprovado. Todos os testes foram reprovados";}
        else if (!resultadoTesteTensao.equals("Aprovado") && this.ligando){retornoInspeção = "Resultado Inspeção: " + resultadoTesteTensao;}

        return retornoInspeção;
    };


    /*Metodo interno*/
    private String testeTensao(int tensaoNominal, double tensaoReal, double margemAceitavel) {

        String teste = "";

        double limiteSuperior = tensaoNominal * (1 + margemAceitavel);
        double limiteInferior = tensaoNominal * (1 - margemAceitavel);

        if (tensaoReal >= limiteInferior && tensaoReal <= limiteSuperior) {
            teste =  "Aprovado";
        } else if (tensaoReal < limiteInferior){
            teste = "Reprovado. Tensão aferida está abaixo do limite minimo";
        } else {
            teste = "Reprovado. Tensão aferida está acima do limite máximo";
        }

        return teste;
    };



    /*Getters and Setters*/
    public int getTensaoNominal() {return tensaoNominal;}

    public void setTensaoNominal(int tensaoNominal) {this.tensaoNominal = tensaoNominal;}

    public double getTensaoReal() {return tensaoReal;}

    public void setTensaoReal(double tensaoReal) {this.tensaoReal = tensaoReal;}

    public boolean isLigando() {return ligando;}

    public void setLigando(boolean ligando) {this.ligando = ligando;}
}
