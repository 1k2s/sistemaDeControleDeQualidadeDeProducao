package domain;

import java.time.LocalDate;

public class ProdutoPerecivel extends ProdutoAlimenticio {

    private double temperatura;
    private boolean armazenamento;

    public ProdutoPerecivel(int codigo, String nome, String lote, LocalDate dataValidade, boolean embalagemIntegra,
                             double temperatura, boolean armazenamento) {
        super(codigo, nome, lote, dataValidade, embalagemIntegra);
        this.temperatura = temperatura;
        this.armazenamento = armazenamento;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(boolean armazenamento) {
        this.armazenamento = armazenamento;
    }

    @Override
    public String inspecionar() {
        // Primeiro chama inspeção básica da classe mãe (validade + embalagem)
        String resultadoBasico = super.inspecionar();

        // Definindo temperatura nominal e margem aceitável
        double tempNominal = 5.0;
        double margemAceitavel = 2.0;

        // Teste de temperatura
        String resultadoTesteTemp = testeTemperatura(temperatura, tempNominal, margemAceitavel);

        // Resultado final
        String resultado;
        if (resultadoTesteTemp.equals("Aprovado") && armazenamento && resultadoBasico.equals("APROVADO")) {
            resultado = "Resultado Inspeção: Aprovado";
            this.setAprovadoInspecao(true);
        } else if (!armazenamento) {
            resultado = "Resultado Inspeção: Reprovado. Armazenamento incorreto.";
            this.setAprovadoInspecao(false);
        } else if (!resultadoTesteTemp.equals("Aprovado")) {
            resultado = "Resultado Inspeção: " + resultadoTesteTemp;
            this.setAprovadoInspecao(false);
        } else {
            resultado = "Resultado Inspeção: Reprovado. " + resultadoBasico;
            this.setAprovadoInspecao(false);
        }

        return resultado;
    }

    public String testeTemperatura(double temperatura, double tempNominal, double margemAceitavel) {
        if (temperatura >= (tempNominal - margemAceitavel) && temperatura <= (tempNominal + margemAceitavel)) {
            return "Aprovado";
        } else {
            return "Reprovado. Temperatura fora da faixa.";
        }
    }

    @Override
    public void setAprovadoInspecao(boolean aprovado) {
        super.setAprovadoInspecao(aprovado);
    }
}
