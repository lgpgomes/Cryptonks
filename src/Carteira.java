import java.util.List;
import java.util.ArrayList;

public class Carteira {
    private static int contadorId = 0;
    private int id;
    private Usuario usuario;
    private List<Investimento> investimentos;
    private List<Transferencia> transferencias;
    private double saldoEmReal;
    private final double taxaTransacao;

    public Carteira( Usuario usuario) {
        this.id = contadorId++;
        this.usuario = usuario;
        this.investimentos = new ArrayList<>();
        this.transferencias = new ArrayList<>();
        this.saldoEmReal = 0;
        this.taxaTransacao = 0.02;
    }


    public void adicionarSaldo (double valor) {
        this.saldoEmReal += valor;
        System.out.printf("R$ %.2f foi adicionado na conta de %s \n", valor, this.usuario.getNome());
    }

    public void sacarSaldo (double valor, String contaBancaria) {
        if (valor <= this.saldoEmReal) {
            this.saldoEmReal -= valor;
            System.out.printf("O valor de R$ %.2f foi enviado para a sua conta %s \n", saldoEmReal, contaBancaria);
        }
        else {
            System.out.printf("%s, você não tem saldo suficiente para realizar este saque. \n", usuario.getNome());
        }
    }

    public void comprarMoeda(Moeda moeda, double quantidadeMoeda) {
        System.out.print("\nRealizando compra de moeda...\n");
        double valorTaxa = (quantidadeMoeda*moeda.cotacaoParaReal) * taxaTransacao;
        double valorLiquido = (quantidadeMoeda*moeda.cotacaoParaReal) ;

        Investimento investimento = consultarInvestimentos(moeda);

        if (investimento == null) {
            investimento = new Investimento(moeda, this);
            this.investimentos.add(investimento);
        }

        Transacao novaTransacao = new Transacao(
                this,
                investimento,
                investimento.getMoeda(),
                TipoOperacao.COMPRA,
                valorLiquido+valorTaxa,
                valorLiquido,
                valorTaxa,
                quantidadeMoeda,
                Status.PENDENTE,
                "",
                ""
        );

        investimento.getTransacoes().add(novaTransacao);
        investimento.validarTransacao(quantidadeMoeda, TipoOperacao.COMPRA, novaTransacao, valorLiquido, valorTaxa);
    }

    public void venderMoeda(Moeda moeda, double quantidadeMoeda) {
        System.out.print("\nRealizando venda de moeda...\n");

        double valorTaxa = (quantidadeMoeda * moeda.cotacaoParaReal) * taxaTransacao;
        double valorLiquido = (quantidadeMoeda * moeda.cotacaoParaReal) - valorTaxa;

        Investimento investimento = consultarInvestimentos(moeda);

        if (investimento == null) {
            System.out.print("Você não possui esta moeda. \n");
            return;
        }

        Transacao novaTransacao = new Transacao(
                this,
                investimento,
                investimento.getMoeda(),
                TipoOperacao.VENDA,
                valorLiquido+valorTaxa,
                valorLiquido,
                valorTaxa,
                quantidadeMoeda,
                Status.PENDENTE,
                "",
                ""
        );

        investimento.getTransacoes().add(novaTransacao);
        investimento.validarTransacao(quantidadeMoeda, TipoOperacao.VENDA, novaTransacao, valorLiquido, valorTaxa);
    }

    public void transferirSaldo(Usuario usuarioDestinatario, double valorTransferencia) {
        System.out.print("\nRealizando transferência...\n");
        Transferencia novaTransferencia = new Transferencia(
                this,
                usuarioDestinatario.getCarteira(),
                valorTransferencia,
                Status.PENDENTE,
                "",
                ""
        );

        this.transferencias.add(novaTransferencia);
        usuarioDestinatario.getCarteira().getTransferencias().add(novaTransferencia);
        novaTransferencia.validarTransferencia(usuarioDestinatario.getCarteira(), valorTransferencia);
    }

    public void consultarTransacao (int id) {
        for (Investimento investimento : this.investimentos) {
            for (Transacao transacao : investimento.getTransacoes()) {
                if (transacao.getId() == id) {
                    transacao.exibir();
                    return;
                }
            }
        }
        System.out.printf("\nNão há transação com o id %d para %s.\n", id, this.getNomeUsuario());
    }

    public void consultarTransferencia (int id) {
        for (Transferencia transferencia : this.transferencias) {
            if (transferencia.getId() == id) {
                transferencia.exibir();
                return;
            }
        }
        System.out.printf("\nNão há transferência com o id %d para %s.\n", id, this.getNomeUsuario());
    }

    private Investimento consultarInvestimentos (Moeda moeda) {
        for (Investimento investimento : this.investimentos) {
            if (investimento.getMoeda().getId() == moeda.getId()) {
                return investimento;
            }
        }
        return null;
    }

    public boolean removerSaldo(double valor) {
        if (valor <= this.saldoEmReal) {
            this.saldoEmReal -= valor;
            System.out.printf("R$ %.2f foi removido da conta de %s \n", valor, this.usuario.getNome());
            return true;
        }
        return false;
    }

    //getters
    public List<Transferencia> getTransferencias () {
        return this.transferencias;
    }

    public double getSaldo() {
        return saldoEmReal;
    }

    public String getNomeUsuario(){
        return usuario.getNome();
    }


}
