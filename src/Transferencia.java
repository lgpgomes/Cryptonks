public class Transferencia {
    private static int contadorId = 0;
    private int id;
    private Carteira carteiraRemetente;
    private Carteira carteiraDestinatario;
    private double valorTransferencia;
    private Status status;
    private String data;
    private String hora;


    public Transferencia(Carteira carteiraRemetente, Carteira carteiraDestinatario, double valorTransferencia, Status status, String data, String hora) {
        this.id = contadorId++;
        this.carteiraRemetente = carteiraRemetente;
        this.carteiraDestinatario = carteiraDestinatario;
        this.valorTransferencia = valorTransferencia;
        this.status = status;
        this.data = data;
        this.hora = hora;
    }

    public void validarTransferencia(Carteira carteiraDestinatario, double valorTransferencia) {
        if (this.carteiraRemetente.removerSaldo(valorTransferencia)) {
            this.carteiraDestinatario.adicionarSaldo(valorTransferencia);
            this.status = Status.CONCLUIDA;

            System.out.printf("\n[Resumo] TRANSFERENCIA REALIZADA\n Id: %d\n Usuário: %s\n Destino: %s\n Valor: R$ %.2f\n Saldo restante: R$ %.2f\n",
                    this.id,
                    this.carteiraRemetente.getNomeUsuario(),
                    this.carteiraDestinatario.getNomeUsuario(),
                    valorTransferencia,
                    this.carteiraRemetente.getSaldo()
            );
        }
        else {
            this.status = Status.ERRO;
            System.out.printf("\n[Resumo] TRANSFERENCIA NÃO REALIZADA\n Id: %d\n Motivo: saldo insuficiente\n Usuário: %s\n Destino: %s\n Valor: R$ %.2f\n Saldo restante: R$ %.2f\n",
                    this.id,
                    this.carteiraRemetente.getNomeUsuario(),
                    this.carteiraDestinatario.getNomeUsuario(),
                    valorTransferencia,
                    this.carteiraRemetente.getSaldo()
            );
        }
    }

    public void exibir() {
        System.out.printf("\n[Resumo] DADOS DA TRANSFERENCIA #%d:\n Usuário: %s\n Destino: %s\n Valor: R$ %.2f\n Status: %s\n",
                this.id,
                this.carteiraRemetente.getNomeUsuario(),
                this.carteiraDestinatario.getNomeUsuario(),
                valorTransferencia,
                this.status
        );
    }

    public double getId(){
        return id;
    }

}
