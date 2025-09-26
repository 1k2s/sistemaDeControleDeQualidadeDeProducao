package domain;

import java.time.LocalDate;

public class ProdutoAlimenticio extends Produto {

    private LocalDate dataValidade;
    private boolean embalagemIntegra;

    public ProdutoAlimenticio(int codigo, String nome, String lote,
                              LocalDate dataValidade, boolean embalagemIntegra) {
        super(codigo, nome, lote);
        this.dataValidade = dataValidade;
        this.embalagemIntegra = embalagemIntegra;
    }

    public LocalDate getDataValidade() { return dataValidade; }
    public boolean isEmbalagemIntegra() { return embalagemIntegra; }

    @Override
    public String inspecionar() {
        boolean dentroValidade = LocalDate.now().isBefore(dataValidade);
        boolean aprovado = embalagemIntegra && dentroValidade;
        setAprovadoTeste(aprovado);
        return aprovado ? "APROVADO" : "REPROVADO";
    }

    //%d → número inteiro (getCodigo()).
    // %s → string (getNome(), getLote(), dataValidade).
    // %b → valor booleano (embalagemIntegra).
    @Override
    public String getDetalhes() {
        return String.format(
            "Produto Alimentício [código=%d, nome=%s, lote=%s, validade=%s, embalagemIntegra=%b]",
            getCodigo(), getNome(), getLote(), dataValidade, embalagemIntegra
        );
    }

    public void aprovarOuReprovar() {
        System.out.println("Produto Alimentício " + getNome() + " " + inspecionar());
    }
    
}
