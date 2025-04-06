public class Moeda {
    private static int contadorId;
    private int id;
    public String nome;
    public String simbolo;
    private double totalDisponivel;
    public double cotacaoParaReal;

    public Moeda ( String nome, String simbolo, double totalDisponivel, double cotacaoParaReal) {
        this.id = contadorId++;
        this.nome = nome;
        this.simbolo = simbolo;
        this.totalDisponivel = totalDisponivel;
        this.cotacaoParaReal = cotacaoParaReal;
    }


    //getters
    public int getId () {
        return this.id;
    }

    public String getNome () {
        return this.nome;
    }

    public String getSimbolo () {
        return this.simbolo;
    }

}
