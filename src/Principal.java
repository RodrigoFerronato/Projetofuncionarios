import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 Imprimir todos os funcionários com todas suas informações
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        funcionarios.forEach(funcionario -> {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %,.2f, Função: %s%n",
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(formatter),
                    funcionario.getSalario(),
                    funcionario.getFuncao());
        });

        // 3.4 Atualizar os salários com 10% de aumento
        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.valueOf(1.1));
            funcionario.setSalario(novoSalario);
        });

        // 3.5 Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 Imprimir os funcionários, agrupados por função
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(funcionario -> {
                System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %,.2f%n",
                        funcionario.getNome(),
                        funcionario.getDataNascimento().format(formatter),
                        funcionario.getSalario());
            });
        });

        // 3.8 Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("Funcionários que fazem aniversário no mês 10 e 12:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(funcionario -> {
                    System.out.printf("Nome: %s, Data de Nascimento: %s%n",
                            funcionario.getNome(),
                            funcionario.getDataNascimento().format(formatter));
                });

        // 3.9 Imprimir o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = Collections.max(funcionarios, Comparator.comparing(funcionario -> ChronoUnit.YEARS.between(funcionario.getDataNascimento(), LocalDate.now())));
        System.out.printf("Funcionário mais velho: Nome: %s, Idade: %d anos%n",
                funcionarioMaisVelho.getNome(),
                ChronoUnit.YEARS.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()));

        // 3.10 Imprimir a lista de funcionários por ordem alfabética
        System.out.println("Funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(funcionario -> {
                    System.out.printf("Nome: %s, Data de Nascimento: %s, Salário: %,.2f, Função: %s%n",
                            funcionario.getNome(),
                            funcionario.getDataNascimento().format(formatter),
                            funcionario.getSalario(),
                            funcionario.getFuncao());
                });

        // 3.11 Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total dos salários: %,.2f%n", totalSalarios);

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        MathContext mc = new MathContext(4, RoundingMode.HALF_UP); // Precisão de 4 com arredondamento para cima

        funcionarios.forEach(funcionario -> {
                BigDecimal quantidadeSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, mc);
                System.out.printf("Nome: %s, Salários Mínimos: %.2f%n", funcionario.getNome(), quantidadeSalariosMinimos);
});

    }
}
