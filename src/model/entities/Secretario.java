package model.entities;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class Secretario extends Funcionario{
    private static final Double SALARIO = 7000.00;
    private static final Double AUMENTO_ANO = 1000.00;
    private static final double BENEFICIO_SOBRE_SALARIO = 0.2;

    public Secretario(){
        super();
    }

    public Secretario(String nome, YearMonth dataContratacao) {
        super(nome, dataContratacao);
    }

    public double salarioMes(YearMonth data) {
        long anosTrabalhados = ChronoUnit.YEARS.between(dataContratacao, data);

        return SALARIO + (AUMENTO_ANO * anosTrabalhados);
    }
    public double salarioMesComBeneficios(YearMonth data) {
        long anosTrabalhados = ChronoUnit.YEARS.between(dataContratacao, data);

        return SALARIO + (AUMENTO_ANO * anosTrabalhados) + (SALARIO * BENEFICIO_SOBRE_SALARIO) ;
    }
    public double totalEmBeneficios(YearMonth data){
        long anosTrabalhaods = ChronoUnit.YEARS.between(dataContratacao, data);

        return (AUMENTO_ANO * anosTrabalhaods + SALARIO   ) * BENEFICIO_SOBRE_SALARIO;
    }
}