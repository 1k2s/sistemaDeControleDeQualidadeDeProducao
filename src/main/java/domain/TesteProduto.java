package domain;

public class TesteProduto {
    public static void main(String[] args) {

        Smartphone iphone = new Smartphone(1,"iphoneX", "123", 110, 3500, 3500, true);

        System.out.println(iphone.getTensaoReal() + " - " + iphone.isLigando());
        System.out.println(iphone.inspecionar());
    }
}
