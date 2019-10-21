package com.softplan.staff.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author momo
 */
@Data
@Document(collection = "player")
public class Person extends AbstractModel {


    @NotEmpty
    @NotNull(message = "Nome é um campo obrigatoria")
    private String nome;

    private String sexo;

    @Pattern(regexp = ".+@.+\\..+", message = "Deve fornecer um email com formato valido")
    private String email;

    @NotNull(message = "Data de Nacimento é um campo obrigatoria")
    private String dataNacimento;

    private String naturalidade;
    private String nacionalidade;

    @NotNull(message = "CPF é um campo obrigatoria")
    @Pattern(regexp = "^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$", message = "Deve fornecer um CPF com formato valido")
    private String CPF;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(String dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }


}
