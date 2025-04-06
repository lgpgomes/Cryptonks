public class Usuario {
    //atributos da classe Usuario
    private static int contadorId = 0;
    private int id;
    private Carteira carteira;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String pais;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private Boolean isLogged;
    private Preferencias preferencias;

    //construtor da classe Usuario
    public Usuario(
            String nome,
            String email,
            String senha,
            String cpf,
            String pais,
            String estado,
            String cidade,
            String bairro,
            String rua,
            String numero,
            Boolean isLogged
    ) {
        this.id = contadorId++;
        this.carteira = new Carteira( this);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.isLogged = isLogged;
        this.preferencias = new Preferencias("light", "pt-BR", true);
    }

    public void editarUsuario (String novoNome) {
        System.out.printf("Usuario %s editado. Novo nome: %s. \n",this.nome, novoNome);
        this.nome = novoNome;
    }

    //getters
    public String getNome() {
        return nome;
    }
    public Carteira getCarteira() {
        return carteira;
    }
}
