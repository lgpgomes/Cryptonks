public class Preferencias {
    private String tema;
    private String idioma;
    private boolean receberNotificacoes;

    public Preferencias(String tema, String idioma, boolean receberNotificacoes) {
        this.tema = tema;
        this.idioma = idioma;
        this.receberNotificacoes = receberNotificacoes;
    }

    public void mudarTema(String tema) {
        this.tema = tema;
    }

    public void mudarIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void mudarReceberNotificacoes(boolean receberNotificacoes) {
        this.receberNotificacoes = receberNotificacoes;
    }
}
