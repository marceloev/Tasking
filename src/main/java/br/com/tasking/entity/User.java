package br.com.tasking.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity(name = "User")
@NamedQueries({
        @NamedQuery(name = "User.findByLogin",
                query = "SELECT U FROM User U WHERE U.login = :P_LOGIN")
})
@Table(name = "TUSER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TUSER")
    @Column(name = "CODUSU")
    private int codigo;

    @NotNull(message = "Ativo do usuário deve ser informado")
    @Column(name = "ATIVO", nullable = false)
    private Character ativo = 'S';

    @Length(max = 20, message = "Login do usuário deve conter até 20 dígitos")
    @NotNull(message = "Login do usuário deve ser informado")
    @Column(name = "LOGIN", length = 20, nullable = false, unique = true)
    private String login;

    @Length(max = 80, message = "Senha do usuário deve conter até 80 dígitos")
    @NotNull(message = "Senha do usuário deve ser informado")
    @Column(name = "SENHA", length = 80, nullable = false)
    private String senha;

    @Length(max = 20, message = "Nome do usuário deve conter até 25 dígitos")
    @NotNull(message = "Nome do usuário deve ser informado")
    @Column(name = "NOME", length = 25, nullable = false)
    private String nome;

    @Length(max = 60, message = "Sobrenome do usuário deve conter até 60 dígitos")
    @Column(name = "SOBRENOME", length = 60)
    private String sobrenome;

    @Email(message = "E-mail do usuário está incorreto")
    @Length(max = 60, message = "E-mail do usuário deve conter até 60 dígitos")
    @NotNull(message = "E-mail do usuário deve ser informado")
    @Column(name = "EMAIL", length = 60, nullable = false)
    private String email;

    @NotNull(message = "Telefone do usuário deve ser informado")
    @Column(name = "TELEFONE", length = 11, nullable = false)
    private String telefone;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "User{" +
                "codigo=" + codigo +
                ", ativo=" + ativo +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
