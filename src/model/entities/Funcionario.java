package model.entities;

import java.time.YearMonth;

public abstract class Funcionario {


    protected String nome;
    protected YearMonth dataContratacao;

    public Funcionario(){

    }
    public Funcionario(String nome, YearMonth dataContratacao){
        this.nome = nome;
        this.dataContratacao = dataContratacao;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public YearMonth getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(YearMonth dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public double salarioMesComBeneficios(YearMonth data){
        return 0.0;
    }

    public abstract double salarioMes(YearMonth data);

    public double totalEmBeneficios(YearMonth data){
        return 0.0;
    }

}
