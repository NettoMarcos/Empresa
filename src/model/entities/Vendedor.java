package model.entities;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Funcionario{

    public static final Double SALARIO = 12000.00;
    public static final Double AUMENTO_ANO = 1800.00;
    private static final Double BENEFICIO_VALOR_VENDIDO = 0.3;

    private List<Venda> vendas = new ArrayList<>();

    public Vendedor(){
        super();
    }

    public Vendedor(String nome, YearMonth dataContratacao) {
        super(nome, dataContratacao);
    }

    public void addVenda(Venda venda){
        vendas.add(venda);
    }
    public void removeVenda(Venda venda){
        vendas.remove(venda);
    }

    public double salarioMes(YearMonth data) {
        long anosTrabalhados = ChronoUnit.YEARS.between(dataContratacao, data);

        return SALARIO + (AUMENTO_ANO * anosTrabalhados);
    }

    public double salarioMesComBeneficios(YearMonth data){
        long anosTrabalhados = ChronoUnit.YEARS.between(dataContratacao, data);
        double valorTotalVendasMes = 0;
        int mesDesejado = data.getMonthValue();

        for (Venda venda : vendas){
            int mesVenda = venda.getData().getMonthValue();
            if (mesDesejado == mesVenda){
                valorTotalVendasMes += venda.getValor();
            }
        }
        return SALARIO + (AUMENTO_ANO * anosTrabalhados) + (valorTotalVendasMes * BENEFICIO_VALOR_VENDIDO);
    }

    public double totalEmBeneficios(YearMonth data){
        double valorTotalVendasMes = 0;
        int mesDesejado = data.getMonthValue();

        for (Venda venda : vendas){
            int mesVenda = venda.getData().getMonthValue();
            if (mesDesejado == mesVenda){
                valorTotalVendasMes += venda.getValor();
            }
        }
        return  valorTotalVendasMes * BENEFICIO_VALOR_VENDIDO;
    }
    public double totalEmVendas(YearMonth data){
        double valorTotalVendasMes = 0;
        int mesDesejado = data.getMonthValue();

        for (Venda venda : vendas){
            int mesVenda = venda.getData().getMonthValue();
            if (mesDesejado == mesVenda){
                valorTotalVendasMes += venda.getValor();
            }
        }
        return  valorTotalVendasMes;
    }
}
