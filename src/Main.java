public class Main {
    public static void main(String[] args) {
        Usuario manoel = new Usuario(
                "Manoel",
                "manoel@gmail.com",
                "",
                "123.456.789-10",
                "Brasil",
                "Bahia",
                "Xique-xique",
                "Centro",
                "Rua A",
                "10",
                true
        );

        Usuario neymar = new Usuario(
                "Neymar",
                "neymar@gmail.com",
                "",
                "999.999.999-99",
                "Brasil",
                "São Paulo",
                "Santos",
                "Centro",
                "Rua B",
                "11",
                true
        );

        Usuario gerson = new Usuario(
                "Gerson",
                "gerson@gmail.com",
                "",
                "000.000.000-00",
                "Brasil",
                "Rio de Janeiro",
                "Rio de Janeiro",
                "Centro",
                "Rua c",
                "12",
                true
        );

        Moeda btc = new Moeda(
                "Bitcoin",
                "BTC",
                10000.00,
                500000.00
        );

        Moeda eth = new Moeda(
                "Ethereum",
                "ETH",
                10000.00,
                2000.00

        );

        //é adicionado R$ 10.000,00 reais na carteira de neymar
        neymar.getCarteira().adicionarSaldo(10000.00);


        //simulacao de uso do sistema

        //neymar transfere R$ 5.000,00 reais para gerson
        neymar.getCarteira().transferirSaldo(gerson, 5000.00);

        //gerson compra 1 ethereum (01 eth custa R$ 2000,00)
        gerson.getCarteira().comprarMoeda(eth, 1);

        //gerson consulta a transacao que acabou de fazer
        gerson.getCarteira().consultarTransacao(0);

    }
}