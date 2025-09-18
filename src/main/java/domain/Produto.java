package domain;

public abstract class Produto {

    private Long codigo;
    private String nome;
    private String lote;
    private boolean aprovadoTeste;


    /*Constructor*/
    public Produto(Long codigo, String nome, String lote, boolean aprovadoTeste) {
        this.codigo = codigo;
        this.nome = nome;
        this.lote = lote;
        this.aprovadoTeste = aprovadoTeste;
    }

    public Produto() {}


    /*Métodos abstratos*/
    public abstract String getDetalhes(Long codigoProduto);
    public abstract String inspecionar(Long codigoProduto);


    /*Getters and Setters*/
    public Long getCodigo() {return codigo;}

    public void setCodigo(Long codigo) {this.codigo = codigo;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getLote() {return lote;}

    public void setLote(String lote) {this.lote = lote;}

    public boolean isAprovado() {return aprovadoTeste;}

}
