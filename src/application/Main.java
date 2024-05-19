package application;

import model.entities.*;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {


    //adiciona um novo secretario à lista de funcionários
    public void adicionarSecretario(String nome, YearMonth data, List<Funcionario> funcionarios) {
        funcionarios.add(new Secretario(nome, data));
    }
    //adiciona um novo vendedor à lista de funcionários
    public void adicionarVendedor(String nome, YearMonth data, List<Funcionario> funcionarios){
        funcionarios.add(new Vendedor(nome, data));
    }
    //adiciona um novo gerente à lista de funcionários
    public void adicionarGerente(String nome, YearMonth data, List<Funcionario> funcionarios){
        funcionarios.add(new Gerente(nome, data));
    }

    //Adiciona uma nova venda com base no nome do funcionário
    public void adicionarVenda(String nomeVendedor, double valor, YearMonth data, List<Funcionario> funcionarios){
        Venda venda = new Venda(nomeVendedor,valor, data);
        for ( Funcionario funcionario : funcionarios){
            if (funcionario instanceof Vendedor && funcionario.getNome().equals(venda.getNomeVendedor())){
                ((Vendedor) funcionario).addVenda(venda);
                break;
            }
        }
    }

    //lista todos funcionarios e seus lucros totais em um mês específico
    public void listarTodosFuncionarios(YearMonth data, List<Funcionario> funcionarios){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("MM/yyyy");
        System.out.println();
        System.out.println("Lista de funcionario com valor total pago em " + data.format(dataFormatada ) + ":") ;

        for(Funcionario funcionario : funcionarios){
            if (funcionario instanceof Secretario && data.isAfter(funcionario.getDataContratacao())){
                System.out.println("Nome: " + funcionario.getNome() + ", " + "Cargo: Secretário(a), " + "Valor Total pago: R$"+  funcionario.salarioMesComBeneficios(data));
            }else if(funcionario instanceof Vendedor && data.isAfter(funcionario.getDataContratacao())){
                System.out.println("Nome: " + funcionario.getNome()+ ", " + "Cargo: Vendedor(a), " + "Valor Total pago: R$" + funcionario.salarioMesComBeneficios(data));
            }else if(funcionario instanceof Gerente  && data.isAfter(funcionario.getDataContratacao())){
                System.out.println("Nome: " + funcionario.getNome()+ ", " + "Cargo: Gerente, " + "Valor Total pago: R$" + funcionario.salarioMes(data));
            }
        }
    }

    //lista todos os funcionario e apênas seus salarios do mês sem benefícios
    public void listarFuncionarioSalarioMes(YearMonth data, List<Funcionario> funcionarios){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("MM/yyyy");
        System.out.println();
        System.out.println("Lista de funcionario e salário pago em " + data.format(dataFormatada ) + ":") ;

        for(Funcionario funcionario : funcionarios){
            if (funcionario instanceof Secretario && data.isAfter(funcionario.getDataContratacao())){
                System.out.println("Nome: " + funcionario.getNome() + ", " + "Cargo: Secretário(a), Salário do mês: R$"+ funcionario.salarioMes(data));
            }else if(funcionario instanceof Vendedor && data.isAfter(funcionario.getDataContratacao())){
                System.out.println("Nome: " + funcionario.getNome()+ ", " + "Cargo: Vendedor(a), Salário do mês: R$" + funcionario.salarioMes(data));
            }else if(funcionario instanceof Gerente  && data.isAfter(funcionario.getDataContratacao())){
                System.out.println("Nome: " + funcionario.getNome()+ ", " + "Cargo: Gerente, Salário do mês: R$" + funcionario.salarioMes(data));
            }
        }
    }

    //lista todos os funcionários que recebem benefícios e seus valores em um mês específico.
    public void listarFuncionioComBeneficio(YearMonth data, List<Funcionario> funcionarios ){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("MM/yyyy");
        System.out.println();
        System.out.println("Lista de funcionario e benefício pago em " + data.format(dataFormatada ) + ":") ;

        for(Funcionario funcionario : funcionarios){
            if (funcionario instanceof Secretario && data.isAfter(funcionario.getDataContratacao())){
                System.out.println("Nome: " + funcionario.getNome() + ", " + "Cargo: Secretário(a), Valor em benefício: R$"+  funcionario.totalEmBeneficios(data));
            }else if(funcionario instanceof Vendedor && data.isAfter(funcionario.getDataContratacao())){
                System.out.println("Nome: " + funcionario.getNome()+ ", " + "Cargo: Vendedor(a), Valor em benefício: R$" + funcionario.totalEmBeneficios(data));
            }
        }
    }

    //printa na tela o funcionário com maior retorno salarial do mês.
    public void funcionarioComMaiorRetorno(YearMonth data, List<Funcionario> funcionarios){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("MM/yyyy");

        Funcionario maiorRetornoFuncionario = null;
        double maiorRetorno = 0.0;

        for(Funcionario funcionario : funcionarios){
            double retornoAtual = 0.0;
            if (data.isAfter(funcionario.getDataContratacao())){
                if(funcionario instanceof Secretario || funcionario instanceof Vendedor ){
                    retornoAtual = funcionario.salarioMes(data);
                }else if(funcionario instanceof Gerente){
                    retornoAtual = funcionario.salarioMes(data);
                }
            }

            if (maiorRetornoFuncionario == null || retornoAtual > maiorRetorno){
                maiorRetornoFuncionario = funcionario;
                maiorRetorno = retornoAtual;
            }
        }
        if (maiorRetornoFuncionario != null && maiorRetorno > 0.0){
            System.out.println("Funcionario com maior retorno em " + data.format(dataFormatada ) + ":");
            if (maiorRetornoFuncionario instanceof Secretario){
                System.out.println("Nome: " + maiorRetornoFuncionario.getNome() + ", " + "Cargo: Secretário(a), Retorno: R$" + maiorRetorno);
            }
            if (maiorRetornoFuncionario instanceof Vendedor) {
                System.out.println("Nome: " + maiorRetornoFuncionario.getNome() + ", " + "Cargo: Vendedor(a), Retorno: R$" + maiorRetorno);
            }
            if (maiorRetornoFuncionario instanceof Gerente) {
                System.out.println("Nome: " + maiorRetornoFuncionario.getNome() + ", " + "Cargo: Gerente, Retorno: R$" + maiorRetorno);
            }
        }else{
            System.out.println("nem um registro encontrado.");
        }
    }

    //printa o funcionario que teve o maior retorno em beneficíos do mês.
    public void funcionarioMaiorRetornoEmBeneficio(YearMonth data, List<Funcionario> funcionarios) {

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("MM/yyyy");

        Funcionario maiorBeneficioFuncionario = null;
        double maiorBeneficio = 0.0;

        for (Funcionario funcionario : funcionarios) {
            double beneficioAtual = 0.0;

            if (data.isAfter(funcionario.getDataContratacao())) {
                if (funcionario instanceof Secretario || funcionario instanceof Vendedor) {
                    beneficioAtual = funcionario.totalEmBeneficios(data);
                }

                if (maiorBeneficioFuncionario == null || beneficioAtual > maiorBeneficio) {
                    maiorBeneficioFuncionario = funcionario;
                    maiorBeneficio = beneficioAtual;
                }
            }
        }

        if (maiorBeneficioFuncionario != null && maiorBeneficio > 0.0) {
            System.out.println("Funcionario com maior retorno em benefício em " + data.format(dataFormatada) + ":");
            if (maiorBeneficioFuncionario instanceof Secretario) {
                System.out.println("Nome: " + maiorBeneficioFuncionario.getNome() + ", " + "Cargo: Secretário(a), Benefício: R$" + maiorBeneficio);
            }
            if (maiorBeneficioFuncionario instanceof Vendedor) {
                System.out.println("Nome: " + maiorBeneficioFuncionario.getNome() + ", " + "Cargo: Vendedor(a), Benefício: R$" + maiorBeneficio);
            }
        } else {
            System.out.println("Nenhum registro encontrado.");
        }
    }

    //printa na tela o vendedor que obteve o maior valor em vendas.
    public void vendedorComMaiorVenda(YearMonth data, List<Funcionario> funcionarios){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("MM/yyyy");

        Funcionario maiorVendaFuncionario = null;

        double maiorVenda = 0.0;
        for (Funcionario funcionario : funcionarios){
            double vendaAtual;

            if(funcionario instanceof Vendedor && data.isAfter(funcionario.getDataContratacao())){
                vendaAtual = funcionario.totalEmBeneficios(data);

                if(maiorVendaFuncionario == null || vendaAtual > maiorVenda){
                    maiorVendaFuncionario = funcionario;
                    maiorVenda = vendaAtual;
                }
            }
        }
        if(maiorVendaFuncionario != null && maiorVenda > 0.0){
            System.out.println("Vendedor com maior venda em " + data.format(dataFormatada) + ":");
            System.out.println("Nome: " + maiorVendaFuncionario.getNome() + ", " + "Cargo: Vendedor(a), Total em vendas: R$" + ((Vendedor)maiorVendaFuncionario).totalEmVendas(data)+ ", total em Benefício: R$" + maiorVenda);
        }else{
            System.out.println("Nenhum registro encontrado.");
        }
    }

    public static void main(String[] args){

        List<Funcionario> funcionarios = new ArrayList<>();

        Main empresa = new Main();

        int op = 0;
        int mes;
        int ano;

        empresa.adicionarSecretario("Jorge Carvalho", YearMonth.of(2018, 1), funcionarios);
        empresa.adicionarSecretario("Maria Souza", YearMonth.of(2015, 12), funcionarios);
        empresa.adicionarVendedor("Ana Silva", YearMonth.of(2021, 12), funcionarios);
        empresa.adicionarVendedor("João Mendes", YearMonth.of(2021, 12), funcionarios);
        empresa.adicionarGerente("Juliana Alves", YearMonth.of(2017, 7), funcionarios);
        empresa.adicionarGerente("Bento Albino", YearMonth.of(2014, 3), funcionarios);

        empresa.adicionarVenda("Ana Silva", 5200.00, YearMonth.of(2021, 12), funcionarios);
        empresa.adicionarVenda("Ana Silva", 4000.00, YearMonth.of(2022, 1), funcionarios);
        empresa.adicionarVenda("Ana Silva", 4200.00, YearMonth.of(2022, 2), funcionarios);
        empresa.adicionarVenda("Ana Silva", 5850.00, YearMonth.of(2022, 3), funcionarios);
        empresa.adicionarVenda("Ana Silva", 7000.00, YearMonth.of(2022, 4), funcionarios);

        empresa.adicionarVenda("João Mendes", 3400.00, YearMonth.of(2021,12), funcionarios);
        empresa.adicionarVenda("João Mendes", 7700.00, YearMonth.of(2022,1), funcionarios);
        empresa.adicionarVenda("João Mendes", 5000.00, YearMonth.of(2022,2), funcionarios);
        empresa.adicionarVenda("João Mendes", 5900.00, YearMonth.of(2022,3), funcionarios);
        empresa.adicionarVenda("João Mendes", 6500.00, YearMonth.of(2022,4), funcionarios);



        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("MENU DE OPÇÕES:");
            System.out.println("1 - Exibir valor total(Salárío e Benefício) dos funcionario de um mês e ano selecionado.");
            System.out.println("2 - Exibir apenas o salários dos fundionarios de um mês e ano selecionado");
            System.out.println("3 - Exibir lista de funcionarios que recebem benefícios");
            System.out.println("4 - Exibir o funciorario que recebeu o maior valor em um mês e ano selecionado.");
            System.out.println("5 - Exibir o funciorario com beneficios que recebeu o maior valor em um mês e ano selecionado.");
            System.out.println("6 - Exibir o vendedor que mais vendeu em um mês e ano selecionado.");
            System.out.println("7 - Sair.");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            try{
                op = sc.nextInt();
                System.out.println();
            }catch (InputMismatchException e){
                System.out.println("Erro de Input!");
                sc.next();
            }

            switch (op){
                case 1:
                    try{
                        System.out.print("Digite o ano desejado: ");
                        ano = sc.nextInt();
                        System.out.print("Digite o mês desejado(de 1 a 12): ");
                        mes = sc.nextInt();
                        empresa.listarTodosFuncionarios(YearMonth.of(ano,mes), funcionarios);
                        System.out.println();
                    }catch (InputMismatchException e){
                        System.out.println();
                        System.out.println("Input invalido!");
                    }catch (Exception e){
                        System.out.println("Algo não esta certo tente novamento!");
                        sc.next();
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Digite o ano desejado: ");
                        ano = sc.nextInt();
                        System.out.print("Digite o mês desejado: ");
                        mes = sc.nextInt();
                        empresa.listarFuncionarioSalarioMes(YearMonth.of(ano,mes),funcionarios);
                        System.out.println();
                    }catch (InputMismatchException e){
                        System.out.println();
                        System.out.println("Input invalido!");
                    }catch (Exception e){
                        System.out.println("Algo não esta certo tente novamento!");
                        sc.next();
                    }
                    break;

                case 3:
                    try{
                        System.out.print("Digite o ano desejado: ");
                        ano = sc.nextInt();
                        System.out.print("Digite o mês desejado: ");
                        mes = sc.nextInt();
                        empresa.listarFuncionioComBeneficio(YearMonth.of(ano,mes), funcionarios);
                        System.out.println();
                    }catch (InputMismatchException e){
                        System.out.println();
                        System.out.println("Input invalido!");

                    }catch (Exception e){
                        System.out.println("Algo não esta certo tente novamento!");
                        sc.next();
                    }
                    break;
                case 4:
                    try{
                        System.out.print("Digite o ano desejado: ");
                        ano = sc.nextInt();
                        System.out.print("Digite o mês desejado: ");
                        mes = sc.nextInt();
                        empresa.funcionarioComMaiorRetorno(YearMonth.of(ano,mes), funcionarios);
                        System.out.println();
                    }catch (InputMismatchException e){
                        System.out.println();
                        System.out.println("Input invalido!");
                    }catch (Exception e){
                        System.out.println("Algo não esta certo tente novamento!");
                        sc.next();
                    }
                    break;
                case 5:
                    try{
                        System.out.print("Digite o ano desejado: ");
                        ano = sc.nextInt();
                        System.out.print("Digite o mês desejado: ");
                        mes = sc.nextInt();
                        empresa.funcionarioMaiorRetornoEmBeneficio(YearMonth.of(ano,mes), funcionarios);
                        System.out.println();
                    }catch (InputMismatchException e){
                        System.out.println();
                        System.out.println("Input invalido!");
                    }catch (Exception e){
                        System.out.println("Algo não esta certo tente novamento!");
                        sc.next();
                    }
                    break;
                case 6:
                    try{
                        System.out.print("Digite o ano desejado: ");
                        ano = sc.nextInt();
                        System.out.print("Digite o mês desejado: ");
                        mes = sc.nextInt();
                        empresa.vendedorComMaiorVenda(YearMonth.of(ano,mes), funcionarios);
                        System.out.println();
                    }catch (InputMismatchException e){
                        System.out.println();
                        System.out.println("Input invalido!");
                    }catch (Exception e){
                        System.out.println("Algo não esta certo tente novamento!");
                        sc.next();
                    }
                    break;
                case 7:
                    System.out.println("Obrigado por utilizar nosso sistema!");
                    System.out.println("Encerrando sessão...");
                    break;
                default:
                    System.out.println();
                    System.out.println("opção invalida tente outra.");
                    System.out.println();
            }

        }while(op != 7);

        sc.close();
    }
}
