import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Concessionaria concessionaria = new Concessionaria();
        Scanner sc = new Scanner(System.in);

        // ✅ Dados iniciais apenas para teste (já cadastrados)
        Cliente c1 = new Cliente(1, "Vitoria", 22, Biotipo.MEDIO);
        Cliente c2 = new Cliente(2, "Carlos", 35, Biotipo.GRANDE);
        concessionaria.adicionarCliente(c1);
        concessionaria.adicionarCliente(c2);

        Veiculo v1 = new Carro(1, "Honda", "Civic", 2020, 60000.0, 70000.0, 5, false, Tamanho.MEDIO);
        Veiculo v2 = new Moto(2, "Kawasaki", "Ninja", 2022, 40000.0, 45000.0, 650, Tamanho.PEQUENO);
        concessionaria.adicionarVeiculo(v1);
        concessionaria.adicionarVeiculo(v2);

        int opcao;
        do {
            System.out.println("\n===== MENU CONCESSIONÁRIA =====");
            System.out.println("1 - Listar Veículos");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Realizar Venda");
            System.out.println("4 - Relatório de Vendas (com lucro/prejuízo)");
            System.out.println("5 - Cadastrar Novo Veículo");
            System.out.println("6 - Sugerir Veículo por Biotipo do Cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            while (!sc.hasNextInt()) { // evita crash com caracteres
                sc.next();
                System.out.print("Digite um número válido: ");
            }
            opcao = sc.nextInt();
            sc.nextLine(); // limpa quebra de linha

            try {
                switch (opcao) {
                    case 1 -> concessionaria.listarVeiculos();
                    case 2 -> concessionaria.listarClientes();
                    case 3 -> {
                        System.out.print("ID do veículo: ");
                        int idV = sc.nextInt();
                        System.out.print("ID do cliente: ");
                        int idC = sc.nextInt();
                        System.out.print("Preço de venda: ");
                        double preco = sc.nextDouble();
                        concessionaria.venderVeiculo(idV, idC, preco);
                    }
                    case 4 -> concessionaria.relatorioVendas();

                    case 5 -> cadastrarVeiculo(sc, concessionaria);

                    case 6 -> {
                        System.out.print("ID do cliente: ");
                        int idCli = sc.nextInt();
                        List<Veiculo> sugestoes = concessionaria.sugerirVeiculosParaCliente(idCli);

                        if (sugestoes.isEmpty()) {
                            System.out.println("Nenhuma sugestão para este cliente.");
                        } else {
                            System.out.println("Veículos recomendados:");
                            for (Veiculo v : sugestoes) {
                                System.out.println(" - " + v);
                            }
                        }
                    }

                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("⚠ Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        sc.close();
    }

    // ✅ Cadastro interativo (ID e tamanho automáticos)
    private static void cadastrarVeiculo(Scanner sc, Concessionaria c) {
        System.out.println("\n== Tipo de veículo ==");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        System.out.print("Escolha: ");

        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Ano: ");
        int ano = sc.nextInt();
        System.out.print("Preço de compra: ");
        double precoCompra = sc.nextDouble();
        System.out.print("Preço de venda: ");
        double precoVenda = sc.nextDouble();

        Veiculo novo;
        if (tipo == 1) {
            System.out.print("Número de portas: ");
            int portas = sc.nextInt();
            System.out.print("Automático (true/false): ");
            boolean automatico = sc.nextBoolean();
            novo = c.cadastrarVeiculo("carro", marca, modelo, ano, precoCompra, precoVenda, portas, automatico);
        } else {
            System.out.print("Cilindradas: ");
            int cilindradas = sc.nextInt();
            novo = c.cadastrarVeiculo("moto", marca, modelo, ano, precoCompra, precoVenda, cilindradas, false);
        }

        System.out.println("✅ Veículo cadastrado com sucesso: " + novo);
    }
}
