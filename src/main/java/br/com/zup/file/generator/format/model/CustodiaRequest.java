package br.com.zup.file.generator.format.model;

import br.com.zup.file.generator.format.annotation.AttributeFile;

import java.time.LocalDate;

public class CustodiaRequest {

    @AttributeFile(columnName = "ID")
    private Long id;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @AttributeFile(columnName = "DATA SOLICITAÇÃO")
    private LocalDate dataSolicitacao;

    @AttributeFile(columnName = "DATA EVENTO")
    private LocalDate dataEvento;

    @AttributeFile(columnName = "TIPO")
    private String tipo;

    @AttributeFile(columnName = "NOME")
    private String nome;

    @AttributeFile(columnName = "CPF")
    private String cpf;

    @AttributeFile(columnName = "STATUS")
    private String status;

    @AttributeFile(columnName = "VISUALIZAR")
    private boolean vizualisar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isVizualisar() {
        return vizualisar;
    }

    public void setVizualisar(boolean vizualisar) {
        this.vizualisar = vizualisar;
    }
}
