package usuario;

public class Administrador extends Usuario {
    private String login;
    private String senha;

    public Administrador(String nome, String email, String login, String senha) {
        super(nome, email);
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean checkLogin(String login, String senha) {
        return (login.equals(getLogin()) && senha.equals(getSenha())); 
    }
}
