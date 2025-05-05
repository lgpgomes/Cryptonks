import java.util.Scanner;

public class Main {

    public static void exibirMenuPrincipal() {
        System.out.println("Selecione um usuário para prosseguir.");
        System.out.println("1 - Manoel");
        System.out.println("2 - Neymar");
        System.out.println("3 - Gerson");
        System.out.println("4 - Sair");
    }

    public static void exibirMenuAcoes() {
        System.out.println("Agora selecione uma acao.");
        System.out.println("1 - Transferir saldo");
        System.out.println("2 - Comprar moeda");
        System.out.println("3 - Vender moeda");
        System.out.println("4 - Consultar transacao");
        System.out.println("5 - Consultar transferencia");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Usuario manoel = new PessoaFisica(
                "manoel@gmail.com",
                "",
                "Brasil",
                "Bahia",
                "Xique-xique",
                "Centro",
                "Rua A",
                "10",
                "",
                "masculino",
                19,
                "Manoel",
                "Souza"
        );
        Usuario neymar = new PessoaFisica(
                "neymar@gmail.com",
                "",
                "Brasil",
                "São Paulo",
                "Santos",
                "Centro",
                "Rua B",
                "11",
                "",
                "masculino",
                19,
                "Neymar",
                "Souza"
        );
        Usuario gerson = new PessoaFisica(
                "gerson@gmail.com",
                "",
                "Brasil",
                "Rio de Janeiro",
                "Rio de Janeiro",
                "Centro",
                "Rua c",
                "12",
                "",
                "masculino",
                19,
                "Gerson",
                "Souza"
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

        Moeda sol = new Moeda(
                "Solana",
                "SOL",
                10000.00,
                200.00
        );
        neymar.getCarteira().adicionarSaldo(10000.00);
        int opcao = 0;


        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();
            Usuario usuarioSelecionado = switch (opcao) {
                case 1 -> manoel;
                case 2 -> neymar;
                case 3 -> gerson;
                default -> null;
            };

            System.out.println("Usuario "+usuarioSelecionado.getNome()+" selecionado.");

            exibirMenuAcoes();
            int acao  = scanner.nextInt();

            switch (acao) {
                case 1:
                    System.out.println("Selecione para quem transferir.");
                    exibirMenuPrincipal();
                    int usuarioDestinoInt = scanner.nextInt();

                    Usuario usuarioDestino = switch (usuarioDestinoInt) {
                        case 1 -> manoel;
                        case 2 -> neymar;
                        case 3 -> gerson;
                        default -> null;
                    };

                    System.out.println("Digite o valor da transferencia.");
                    double valorTranferencia = scanner.nextDouble();
                    usuarioSelecionado.getCarteira().transferirSaldo(usuarioDestino, valorTranferencia);

                case 2:
                    System.out.println("Qual a moeda voce quer comprar");


            }


        } while (opcao != 4);






        //é adicionado R$ 10.000,00 reais na carteira de neymar
        neymar.getCarteira().adicionarSaldo(10000.00);

        //neymar transfere R$ 5.000,00 reais para gerson
        neymar.getCarteira().transferirSaldo(gerson, 5000.00);

        //gerson compra 1 ethereum (01 eth custa R$ 2000,00)
        gerson.getCarteira().comprarMoeda(eth, 1);

        //gerson consulta a transacao que acabou de fazer
        gerson.getCarteira().consultarTransacao(0);

        //manoel tenta consultar uma transferencia na qual ele nao esta envolvido (recebe msg de erro)
        manoel.getCarteira().consultarTransferencia(0);

        //gerson saca R$ 900,00
        gerson.getCarteira().sacarSaldo(900,"Banco XYZ");

        //gerson transfere R$ 1000,00 para manoel
        gerson.getCarteira().transferirSaldo(manoel, 1000.00);

        //gerson vende etg
        gerson.getCarteira().venderMoeda(eth, 0.5);

    }
}