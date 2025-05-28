public class Cliente {
    private String name;
    private String cpf;
    private String celular;
    private String email;

    public Cliente(String name, String cpf, String celular, String email) {
        this.nome = name;
        this.cpf = cpf;
        this.celular = celular;
        this.email = email;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validarCPF() {
        return cpf.matches("\\d{11}");
    }

    public boolean validarEmail() {
        return email.contains("@") && email.contains(".");
    }
}
