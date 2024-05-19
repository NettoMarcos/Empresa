package model.entities;

import java.time.YearMonth;

public class Venda {
    private String nomeVendedor;
    private Double valor;
    private YearMonth data;



    public Venda(){

    }
    public Venda(String nomeVendedor, Double valor, YearMonth data) {
        this.nomeVendedor = nomeVendedor;
        this.valor = valor;
        this.data = data;

    }

    public YearMonth getData() {
        return data;
    }

    public void setData(YearMonth data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }
}
