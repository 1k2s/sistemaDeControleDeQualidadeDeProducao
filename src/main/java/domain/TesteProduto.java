package domain;

public class TesteProduto {
    public static void main(String[] args) {

        Smartphone iphone = new Smartphone(1,"iphoneX", "123", 110, 3500);

        System.out.println(iphone.getDetalhes());
        System.out.println();
        System.out.println(iphone.getDetalhesInspecao());
        System.out.println();
        System.out.println(iphone.inspecionar());
    }
}
