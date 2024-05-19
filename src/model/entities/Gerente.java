package model.entities;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class Gerente extends Funcionario{
    private static final Double SALARIO = 20000.00;
    private static final Double AUMENTO_ANO = 3000.00;


    public Gerente(){
        super();
    }

    public Gerente(String nome, YearMonth dataContratacao) {
        super(nome, dataContratacao);
    }

    @Override
    public double salarioMes(YearMonth data) {
        long anosTrabalhados = ChronoUnit.YEARS.between(dataContratacao, data);

        return SALARIO + (AUMENTO_ANO * anosTrabalhados);
    }

}
