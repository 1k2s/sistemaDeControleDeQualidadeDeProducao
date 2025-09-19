import domain.ProdutoEletronico;

public class TesteProduto {

    public static void main(String[] args) {

        ProdutoEletronico celular = new ProdutoEletronico(123, "Iphone 17", "abc123", 110, 104.5, true );

        System.out.println(celular.inspecionar());
        System.out.println(celular.getDetalhes());
    }
}
