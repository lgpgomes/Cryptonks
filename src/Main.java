import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void exibirUsuarios(Usuario manoel, Usuario neymar, Usuario gerson) {
        System.out.printf("1 - MANOEL (Saldo R$ %.2f)\n", manoel.getCarteira().getSaldo());
        System.out.printf("2 - NEYMAR (Saldo R$ %.2f)\n", neymar.getCarteira().getSaldo());
        System.out.printf("3 - GERSON (Saldo R$ %.2f)\n", gerson.getCarteira().getSaldo());
    }

    public static void exibirMenuPrincipal(Usuario manoel, Usuario neymar, Usuario gerson) {
        System.out.println("\n[*] BEM VINDO A CRYPTONKS, SELECIONE UM USUARIO PARA PROSEGUIR");
        exibirUsuarios(manoel, neymar, gerson);
        System.out.println("4 - SAIR");
    }

    public static void exibirMenuAcoes(String nomeUsuarioSelecionado) {
        System.out.printf("\n[*] %s, SELECIONE UMA ACAO\n",nomeUsuarioSelecionado.toUpperCase());
        System.out.println("1 - TRANSFERIR SALDO");
        System.out.println("2 - COMPRAR MOEDA");
        System.out.println("3 - VENDER MOEDA");
        System.out.println("4 - CONSULTAR TRANSACAO");
        System.out.println("5 - CONSULTAR TRANSFERENCIA");
    }

    public static void exibirMenuMoedas() {
        System.out.println("\n[*] SELECIONE UM MOEDA");
        System.out.println("1 - BITCOIN");
        System.out.println("2 - ETHEREUM");
        System.out.println("3 - SOLANA");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
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

            manoel.getCarteira().adicionarSaldo(10000.00);
            neymar.getCarteira().adicionarSaldo(1000000.00);
            gerson.getCarteira().adicionarSaldo(100000.00);

            boolean running = true;

            while (running) {
                exibirMenuPrincipal(manoel, neymar, gerson);
                int usuarioOpcao = scanner.nextInt();

                Usuario usuarioSelecionado = switch (usuarioOpcao) {
                    case 1 -> manoel;
                    case 2 -> neymar;
                    case 3 -> gerson;
                    case 4 -> {
                        System.out.println("Encerrando programa.");
                        running = false;
                        yield null;
                    }
                    default -> {
                        System.out.println("Opcao invalida.");
                        yield null;
                    }
                };

                if (usuarioSelecionado == null) continue;

                exibirMenuAcoes(usuarioSelecionado.getNome());

                int acaoOpcao = scanner.nextInt();

                switch (acaoOpcao) {
                    case 1 -> {

                        Usuario usuarioDestino = null;

                        while (usuarioDestino == null) {
                            System.out.println("[*] SELECIONE PARA QUEM TRANSFERIR SALDO.");
                            exibirUsuarios(manoel, neymar, gerson);

                            int usuarioDestinoOpcao = scanner.nextInt();

                            usuarioDestino = switch (usuarioDestinoOpcao) {
                                case 1 -> manoel;
                                case 2 -> neymar;
                                case 3 -> gerson;
                                default -> {
                                    System.out.println("Opcao invalida.");
                                    yield null;
                                }
                            };
                        }

                        System.out.println("[*] DIGITE A QUANTIDADE DE SALDO A SER TRANSFERIDA.");
                        double valorTransferencia = scanner.nextDouble();
                        usuarioSelecionado.getCarteira().transferirSaldo(usuarioDestino, valorTransferencia);
                    }
                    case 2 -> {
                        exibirMenuMoedas();

                        Moeda moedaSelecionada = null;

                        while (moedaSelecionada == null) {
                            int moedaOpcao = scanner.nextInt();
                            moedaSelecionada = switch (moedaOpcao) {
                                case 1 -> btc;
                                case 2 -> eth;
                                case 3 -> sol;
                                default -> {
                                    System.out.println("Opcao invalida.");
                                    yield null;
                                }
                            };
                        }

                        System.out.println("[*] DIGITE A QUANTIDADE DE MOEDA A SER C0MPRADA.");
                        double quantidadeMoeda = scanner.nextDouble();
                        usuarioSelecionado.getCarteira().comprarMoeda(moedaSelecionada, quantidadeMoeda);
                    }
                    case 3 -> {
                        exibirMenuMoedas();

                        Moeda moedaSelecionada = null;

                        while (moedaSelecionada == null) {
                            int moedaOpcao = scanner.nextInt();
                            moedaSelecionada = switch (moedaOpcao) {
                                case 1 -> btc;
                                case 2 -> eth;
                                case 3 -> sol;
                                default -> {
                                    System.out.println("Opcao invalida.");
                                    yield null;
                                }
                            };
                        }

                        System.out.println("[*] DIGITE A QUANTIDADE DE MOEDA A SER VENDIDA.");
                        double quantidadeMoeda = scanner.nextDouble();
                        usuarioSelecionado.getCarteira().venderMoeda(moedaSelecionada, quantidadeMoeda);
                    }

                    case 4 -> {
                        System.out.println("[*] DIGITE O ID DA TRANSACAO A SER CONSULTADA.");
                        int idTransacao = scanner.nextInt();
                        usuarioSelecionado.getCarteira().consultarTransacao(idTransacao);
                    }

                    case 5 -> {
                        System.out.println("[*] DIGITE O ID DA TRANSFERENCIA A SER CONSULTADA.");
                        int idTransferencia = scanner.nextInt();
                        usuarioSelecionado.getCarteira().consultarTransferencia(idTransferencia);
                    }

                }
                ;
            }
        }
        catch (InputMismatchException e) {
            System.err.println("[ERRO] Valor digitado invalido.");
        }

        catch (Exception e) {
            System.err.println("[ERRO] Erro inesperado.");
        }

        finally {
            scanner.close();
        }
    }
}