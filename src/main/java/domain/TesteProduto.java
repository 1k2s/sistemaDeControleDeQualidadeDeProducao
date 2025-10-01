package domain;

public class TesteProduto {
    public static void main(String[] args) {

        Smartphone iphone = new Smartphone(1,"iphoneX", "123", 110, 3500);


        ProdutoEletronico produtoEletronico = new ProdutoEletronico(123, "teste", "123456", 110);


        System.out.println(produtoEletronico.getTensaoReal() + " - " + produtoEletronico.isLigando());
        System.out.println(produtoEletronico.inspecionar());
    }
}
