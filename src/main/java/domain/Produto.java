package domain;

public abstract class Produto {

    private int codigo;
    private String nome;
    private String lote;
    private boolean aprovadoInspecao;


    /*Constructor*/
    public Produto(int codigo, String nome, String lote) {
        this.codigo = codigo;
        this.nome = nome;
        this.lote = lote;
    }

    public Produto() {}


    /*Métodos abstratos*/
    public abstract String getDetalhes();
    public abstract String inspecionar();


    /*Getters and Setters*/
    public int getCodigo() {return codigo;}

    public void setCodigo(int codigo) {this.codigo = codigo;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getLote() {return lote;}

    public void setLote(String lote) {this.lote = lote;}

    public boolean isAprovado() {return aprovadoInspecao;}

    public void setAprovadoInspecao(boolean aprovadoInspecao) {
        this.aprovadoInspecao = aprovadoInspecao;
    }
}
