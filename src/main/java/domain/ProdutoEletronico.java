package domain;

public class ProdutoEletronico extends Produto {

    private Double tensao;
    private boolean ligando;

    public ProdutoEletronico(Long codigo, String nome, String lote, boolean aprovadoTeste, Double tensao, boolean ligando) {
        super(codigo, nome, lote, aprovadoTeste);
        this.tensao = tensao;
        this.ligando = ligando;
    }

    public ProdutoEletronico() {}




    /*Sobrescrevendo métodos abstratos da classe super*/
    @Override
    public String inspecionar(Long codigoProduto) {


      return null;
    };

    @Override
    public String getDetalhes(Long codigoProduto) {
      return null;
    };


    /*Getters and Setters*/
    public Double getTensao() {
        return tensao;
    }

    public void setTensao(Double tensao) {
        this.tensao = tensao;
    }

    public boolean isLigando() {
        return ligando;
    }

    public void setLigando(boolean ligando) {
        this.ligando = ligando;
    }
}
