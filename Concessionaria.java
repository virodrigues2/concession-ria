import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concessionaria {
    private List<Veiculo> veiculos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();

    // ✅ Gerador automático de ID para veículos
    private int proximoIdVeiculo = 3; // 1 e 2 já usados pelos veículos iniciais

    private int gerarIdVeiculo() {
        return proximoIdVeiculo++;
    }

    public void adicionarVeiculo(Veiculo v) { veiculos.add(v); }
    public void adicionarCliente(Cliente c) { clientes.add(c); }

    // ✅ 3. Método unificado para exibir veículos
    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Não há veículos cadastrados.");
            return;
        }
        System.out.println("=== Veículos em estoque ===");
        for (Veiculo v : veiculos) {
            System.out.println(v.toString());
        }
    }

    // ✅ 2. Listar clientes
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
            return;
        }
        System.out.println("=== Clientes cadastrados ===");
        for (Cliente c : clientes) {
            System.out.println(c.toString());
        }
    }

    public Veiculo buscarVeiculoPorId(int id) throws VeiculoNaoEncontradoException {
        return veiculos.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElseThrow(() -> new VeiculoNaoEncontradoException("Veículo com ID " + id + " não encontrado."));
    }

    public Cliente buscarClientePorId(int id) throws ClienteNaoEncontradoException {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com ID " + id + " não encontrado."));
    }

    // ✅ 1. Método para cadastrar veículo com ID automático (usado no menu)
    public Veiculo cadastrarVeiculo(String tipo, String marca, String modelo, int ano,
                                    double precoCompra, double precoVenda, int portasOuCc, boolean automatico) {

        int id = gerarIdVeiculo(); // <-- ID automático
        Tamanho tamanho;

        // Como você escolheu tamanho automático:
        if (tipo.equalsIgnoreCase("carro")) {
            tamanho = Tamanho.MEDIO;
            Veiculo v = new Carro(id, marca, modelo, ano, precoCompra, precoVenda, portasOuCc, automatico, tamanho);
            veiculos.add(v);
            return v;
        } else { // moto
            tamanho = Tamanho.PEQUENO;
            Veiculo v = new Moto(id, marca, modelo, ano, precoCompra, precoVenda, portasOuCc, tamanho);
            veiculos.add(v);
            return v;
        }
    }

    // ✅ Sugestão por biotipo (via ID do cliente)
    public List<Veiculo> sugerirVeiculosParaCliente(int idCliente) throws ClienteNaoEncontradoException {
        Cliente cliente = buscarClientePorId(idCliente);

        List<Veiculo> sugestoes = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (combinaBiotipoTamanho(cliente.getBiotipo(), v.getTamanho())) {
                sugestoes.add(v);
            }
        }
        return sugestoes;
    }

    private boolean combinaBiotipoTamanho(Biotipo b, Tamanho t) {
        switch (b) {
            case PEQUENO: return t == Tamanho.PEQUENO;
            case MEDIO:   return t == Tamanho.MEDIO || t == Tamanho.PEQUENO;
            case GRANDE:  return t == Tamanho.GRANDE || t == Tamanho.MEDIO;
            default:      return false;
        }
    }

    // ✅ Venda com validações e exceções
    public void venderVeiculo(int idVeiculo, int idCliente, double precoVenda)
            throws VeiculoNaoEncontradoException, ClienteNaoEncontradoException, VendaInvalidaException {

        Veiculo v = buscarVeiculoPorId(idVeiculo);
        Cliente c = buscarClientePorId(idCliente);

        if (precoVenda <= 0) {
            throw new VendaInvalidaException("Preço de venda inválido.");
        }

        if (precoVenda < v.getPrecoCompra() * 0.5) {
            throw new VendaInvalidaException("Preço de venda muito baixo — revisão necessária.");
        }

        Venda venda = new Venda(v, c, LocalDate.now(), precoVenda);
        vendas.add(venda);

        veiculos.remove(v); // baixa no estoque

        System.out.println("✅ Venda registrada com sucesso!");
    }

    // ✅ 5. Relatório de vendas com lucro/prejuízo
    public void relatorioVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }
        System.out.println("=== Relatório de Vendas ===");
        for (Venda venda : vendas) {
            double lucro = venda.getLucro();
            String situacao = lucro >= 0 ? "LUCRO" : "PREJUÍZO";
            System.out.println(venda.toString()
                    + String.format(" | Lucro: R$%.2f (%s)", lucro, situacao));
        }
    }
}
